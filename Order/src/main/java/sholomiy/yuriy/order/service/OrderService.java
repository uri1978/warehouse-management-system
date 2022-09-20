package sholomiy.yuriy.order.service;

import java.util.Collection;
import java.util.List;

import sholomiy.yuriy.order.dto.ProductDTO;
import sholomiy.yuriy.order.entity.Order;

public interface OrderService {

	Collection<Order> getAllOrders();

	Order getOrderById(Long id);

	Order createOrderByProductDTOs(List<ProductDTO> productsDTO);

}
