package sholomiy.yuriy.inventory.controllers;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sholomiy.yuriy.inventory.dto.ProductDTO;
import sholomiy.yuriy.inventory.entity.Inventory;
import sholomiy.yuriy.inventory.service.InventoryService;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

	private final InventoryService inventoryService;

	@GetMapping
	public ResponseEntity<Collection<Inventory>> getCatalogue() {
		final Collection<Inventory> inventory = inventoryService.getInventory();
		return inventory == null || inventory.isEmpty() ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(inventory);
	}

	@PutMapping
	public ResponseEntity<Collection<ProductDTO>> putInventoryByProducts(
			@RequestBody Collection<ProductDTO> productDTOs) {
		
		final Collection<ProductDTO> inventory = inventoryService.getInventoryByProductsDTO(productDTOs);
		return inventory == null || inventory.isEmpty() ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(inventory);
		
	}

	@PutMapping("/add")
	public ResponseEntity<Collection<ProductDTO>> addInventoryByProducts(
			@RequestBody Collection<ProductDTO> productDTOs) {
		
		final Collection<ProductDTO> inventory = inventoryService.addInventoryByProductsDTO(productDTOs);
		return inventory == null || inventory.isEmpty() ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(inventory);
		
	}
	
}
