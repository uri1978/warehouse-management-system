package sholomiy.yuriy.order.service;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sholomiy.yuriy.order.clients.CatalogueClient;
import sholomiy.yuriy.order.clients.InventoryClient;
import sholomiy.yuriy.order.dto.ProductDTO;
import sholomiy.yuriy.order.entity.Catalogue;
import sholomiy.yuriy.order.entity.Order;
import sholomiy.yuriy.order.entity.Product;
import sholomiy.yuriy.order.entity.ProductOfOrder;
import sholomiy.yuriy.order.exceptions.CatalogueNotFoundException;
import sholomiy.yuriy.order.exceptions.FillOrderException;
import sholomiy.yuriy.order.exceptions.InventoryNotFoundException;
import sholomiy.yuriy.order.exceptions.OrderNotFoundException;
import sholomiy.yuriy.order.exceptions.ProductsEmptyException;
import sholomiy.yuriy.order.exceptions.ProductsNotFoundException;
import sholomiy.yuriy.order.repository.OrderRepository;
import sholomiy.yuriy.order.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class MySQLOrderService implements OrderService {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;

	private final InventoryClient inventoryClient;
	private final CatalogueClient catalogueClient;

	@Override
	public Collection<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id.toString()));
	}

	@Override
	public Order createOrderByProductDTOs(List<ProductDTO> productDTOs) {

		if (productDTOs.isEmpty())
			throw new ProductsEmptyException();

		List<Long> ids = productDTOs.stream().map(t -> t.getId()).collect(Collectors.toList());

		// Products
		List<Product> products = productRepository.findAllById(ids);
		if (products.isEmpty())
			throw new ProductsNotFoundException(ids.toString());

		// Catalogue
		List<Catalogue> catalogues = catalogueClient.getCatalogue(ids);
		if (catalogues.isEmpty())
			throw new CatalogueNotFoundException(ids.toString());

		// Inventory
		List<ProductDTO> inventorys = inventoryClient.updateInventory(productDTOs);
		if (inventorys.isEmpty())
			throw new InventoryNotFoundException(ids.toString());

		// Creating and filling new order
		Order order = new Order();
		order.setCreateDate(Instant.now());

		// If the order is not completed, we will return the inventory
		try {
			fillProductOfOrder(order, productDTOs, products, catalogues, inventorys);			
		} catch (RuntimeException e) {
			inventoryClient.returnInventory(inventorys);
			throw new FillOrderException(e.getMessage());
		}

		return orderRepository.save(order);

	}

	public void addProductOfOrder(Order order, Product product, int quantity, float price) {

		ProductOfOrder po = new ProductOfOrder(order, product, quantity, price);
		
		List<ProductOfOrder> productsOfOrder = order.getProductsOfOrder();
		productsOfOrder.add(po);
		order.setProductsOfOrder(productsOfOrder);
		
		order.setTotalPrice(order.getTotalPrice() + po.getSum());

	}

	public void fillProductOfOrder(Order order, List<ProductDTO> productDTOs, List<Product> products,
			List<Catalogue> catalogues, List<ProductDTO> inventorys) throws RuntimeException {

		StringBuilder message = new StringBuilder();

		for (ProductDTO productDTO : productDTOs) {

			// Product
			Product product = products.stream().filter(t -> t.getId().equals(productDTO.getId())).findFirst().orElse(null);
			if (product == null) {
				message.append("Not found product ID: " + productDTO.getId() + ")\n");
				continue;
			}

			// Inventory
			ProductDTO inventory = inventorys.stream().filter(t -> t.getId().equals(product.getId())).findFirst()
					.orElse(null);
			if (inventory == null) {
				message.append("Not found product in inventory by product id: " + product.getId() + " (" + product.getName() + ")\n");
				continue;
			} else if (inventory.getQuantity() <= 0) {
				message.append("Inventory is null by product id: " + product.getId() + " (" + product.getName() + ")\n");
				continue;
			}

			// Catalogue
			Catalogue catalogue = catalogues.stream().filter(t -> t.getProduct().equals(product)).findFirst().orElse(null);
			if (catalogue == null) {
				message.append("Not found product in catalogue by id: " + product.getId() + " (" + product.getName() + ")\n");
				continue;
			} else if (catalogue.getPrice() <= 0) {
				message.append("Price of product in catalogue is null, id: " + product.getId() + " (" + product.getName() + ")\n");
				continue;
			}

			addProductOfOrder(order, product, inventory.getQuantity(), catalogue.getPrice());

		}

		if (order.getProductsOfOrder().isEmpty())
			throw new RuntimeException(message.toString().trim());

	}

}
