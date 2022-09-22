package sholomiy.yuriy.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import sholomiy.yuriy.order.entity.Order;
import sholomiy.yuriy.order.repository.OrderRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class OrderApplicationTests {

	@Autowired
	private WebTestClient client;
	
	@Autowired
	private OrderRepository	repository;
	
	private Order order;
	
	@BeforeEach
	void setUp() {
		order = repository.save(new Order(1L, null, 1000L, Instant.now()));
	}
	
	@AfterEach
	void tearDown() {
		repository.delete(order);
	}
	
	@Test
	void getAllOrders() {

		assertThat(client.get()
			.uri("/api/v1/order")
			.exchange()
			.expectStatus().isOk()
			.expectBody(Iterable.class)
			.returnResult()
			.getResponseBody()
			.iterator()
			.hasNext()).isTrue();
		
	}

	@Test
	void getOrderById() {

		assertThat(client.get()
			.uri("/api/v1/order/" + order.getId())
			.exchange()
			.expectStatus().isOk()
			.expectBody(Order.class)).isNotNull();
		
	}
	
}
