package sholomiy.yuriy.order.clients;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import sholomiy.yuriy.order.entity.Catalogue;

@Component
@RequiredArgsConstructor
public class CatalogueClient {

	private final String endpoint = "/api/v1/catalogue";
	private final WebClient client;
	
	@Value("${catalogue.service-host}")
	private String catalogueServer;

	public List<Catalogue> getCatalogue(List<Long> ids) {

		List<Catalogue> catalogue = client.get()
				.uri(catalogueServer + endpoint, uriBuider -> uriBuider.queryParam("ids", ids).build())
				.retrieve()
				.bodyToFlux(Catalogue.class)
				.toStream()
				.collect(Collectors.toList());
		return catalogue;

	}

}