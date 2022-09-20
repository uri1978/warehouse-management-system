package sholomiy.yuriy.order.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sholomiy.yuriy.order.dto.ProductDTO;
import sholomiy.yuriy.order.entity.Order;
import sholomiy.yuriy.order.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping
	public ResponseEntity<Collection<Order>> getAllOrders() {
		final Collection<Order> orders = orderService.getAllOrders();
		return orders == null || orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orders);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.getOrderById(id));
	}

	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody List<ProductDTO> productsDTO) {
		return ResponseEntity.ok(orderService.createOrderByProductDTOs(productsDTO));
	}

}
