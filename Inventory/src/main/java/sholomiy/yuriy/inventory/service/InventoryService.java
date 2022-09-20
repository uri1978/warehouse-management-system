package sholomiy.yuriy.inventory.service;

import java.util.Collection;

import sholomiy.yuriy.inventory.dto.ProductDTO;
import sholomiy.yuriy.inventory.entity.Inventory;

public interface InventoryService {

	Collection<Inventory> getInventory();

	Collection<ProductDTO> getInventoryByProductsDTO(Collection<ProductDTO> productDTOs);
	
	Collection<ProductDTO> addInventoryByProductsDTO(Collection<ProductDTO> productDTOs);

}
