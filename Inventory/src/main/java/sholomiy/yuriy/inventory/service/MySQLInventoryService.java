package sholomiy.yuriy.inventory.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sholomiy.yuriy.inventory.dto.ProductDTO;
import sholomiy.yuriy.inventory.entity.Inventory;
import sholomiy.yuriy.inventory.repository.InventoryRepository;

@Service
@RequiredArgsConstructor
public class MySQLInventoryService implements InventoryService {

	private final InventoryRepository inventoryRepository;

	@Override
	public Collection<Inventory> getInventory() {
		return inventoryRepository.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<ProductDTO> getInventoryByProductsDTO(Collection<ProductDTO> productDTOs) {

		List<ProductDTO> result = new ArrayList<>();
		List<Inventory> inventorys = inventoryRepository
				.findAllByProductIdIn(productDTOs.stream().map(t -> t.getId()).collect(Collectors.toList()));
		
		for (Inventory inventory : inventorys) {

			ProductDTO productDTO = productDTOs.stream().filter(t -> t.getId().equals(inventory.getProduct().getId()))
					.findFirst().get();
			int quantity = Math.min(productDTO.getQuantity(), inventory.getQuantity());
			inventory.setQuantity(inventory.getQuantity() - quantity);
			inventoryRepository.save(inventory);
			result.add(new ProductDTO(productDTO.getId(), quantity));

		}

		return result;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<ProductDTO> addInventoryByProductsDTO(Collection<ProductDTO> productDTOs) {
		
		List<ProductDTO> result = new ArrayList<>();
		List<Inventory> inventorys = inventoryRepository
				.findAllByProductIdIn(productDTOs.stream().map(t -> t.getId()).collect(Collectors.toList()));
		
		for (Inventory inventory : inventorys) {

			ProductDTO productDTO = productDTOs.stream()
					.filter(t -> t.getId().equals(inventory.getProduct().getId()))
					.findFirst().get();
			inventory.setQuantity(inventory.getQuantity() + productDTO.getQuantity());
			inventoryRepository.save(inventory);
			result.add(new ProductDTO(productDTO.getId(), inventory.getQuantity()));

		}

		return result;
		
	}

}
