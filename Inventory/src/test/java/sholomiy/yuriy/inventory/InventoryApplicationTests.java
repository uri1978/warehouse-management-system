package sholomiy.yuriy.inventory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import sholomiy.yuriy.inventory.dto.ProductDTO;
import sholomiy.yuriy.inventory.entity.Inventory;
import sholomiy.yuriy.inventory.entity.Product;
import sholomiy.yuriy.inventory.repository.InventoryRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class InventoryApplicationTests {

	@Autowired
	private InventoryRepository repository;
	
	@Autowired
	private WebTestClient client;
	
	private Inventory inventory;
	
	@BeforeEach
	void setUp() {
		inventory = repository.save(new Inventory(10L, null, 100));
	}

	@AfterEach
	void tearDown() {
		repository.delete(inventory);
	}

	@Test
	void getInventory() {

		assertThat(client.get()
			.uri("/api/v1/inventory")
			.exchange()
			.expectStatus().isOk()
			.expectBody(Iterable.class)
			.returnResult()
			.getResponseBody()
			.iterator()
			.hasNext()).isTrue();
		
	}
	
	@Test
	void putInventory() {

		repository.save(new Inventory(1L, new Product(1L, "Test1"), 160));
		repository.save(new Inventory(2L, new Product(2L, "Test2"), 0));
		
		Collection<ProductDTO> productDTOs = List.of(new ProductDTO(1L, 5), new ProductDTO(2L, 2));
		
		assertThat(client.put()
				.uri("/api/v1/inventory")
				.bodyValue(productDTOs)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Collection.class)
				.returnResult()
				.getResponseBody().size() == 2).isTrue();
		
	}

	@Test
	void addInventoryByProductsInventory() {

		Collection<ProductDTO> productDTOs = List.of(new ProductDTO(1L, 160), new ProductDTO(2L, 0));
		
		assertThat(client.put()
				.uri("/api/v1/inventory")
				.bodyValue(productDTOs)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Collection.class)
				.returnResult()
				.getResponseBody().size() == 2).isTrue();
		
	}

}
