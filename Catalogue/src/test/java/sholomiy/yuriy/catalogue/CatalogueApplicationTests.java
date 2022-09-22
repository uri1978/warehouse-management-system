package sholomiy.yuriy.catalogue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CatalogueApplicationTests {

	@Autowired WebTestClient client;
	
	@Test
	void getCatalogue() {

		assert client.get()
			.uri("/api/v1/catalogue/all")
			.exchange()
			.expectStatus().isOk()
			.expectBody(Iterable.class)
			.returnResult()
			.getResponseBody()
			.iterator()
			.hasNext();
		
	}
	
	@Test
	void getCatalogueByProductsCatalogue() {

		//Query parameters
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
		ids.add(2L);
		
		assert client.get()
		.uri(uriBuider -> uriBuider.path("/api/v1/catalogue").queryParam("ids", ids).build())
		.exchange()
		.expectStatus().isOk()
		.expectBody(Iterable.class)
		.returnResult()
		.getResponseBody()
		.iterator()
		.hasNext();
		
	}

}
