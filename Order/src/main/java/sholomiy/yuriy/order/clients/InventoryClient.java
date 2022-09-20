package sholomiy.yuriy.order.clients;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import sholomiy.yuriy.order.dto.ProductDTO;

@Component
@RequiredArgsConstructor
public class InventoryClient {

	private final String endpoint = "/api/v1/inventory";
	private final String endpointAdd = "/api/v1/inventory/add";
	private final WebClient client;

	@Value("${inventory.service-host}")
	private String inventoryServer;

	public List<ProductDTO> updateInventory(Collection<ProductDTO> productDTOs) {

		List<ProductDTO> inventory = client.put()
				.uri(inventoryServer + endpoint)
				.bodyValue(productDTOs)
				.retrieve()
				.bodyToFlux(ProductDTO.class)
				.toStream()
				.collect(Collectors.toList());
		return inventory;

	}

	public List<ProductDTO> returnInventory(Collection<ProductDTO> productDTOs) {

		List<ProductDTO> inventory = client.put()
				.uri(inventoryServer + endpointAdd)
				.bodyValue(productDTOs)
				.retrieve()
				.bodyToFlux(ProductDTO.class)
				.toStream()
				.collect(Collectors.toList());
		return inventory;

	}
	
}