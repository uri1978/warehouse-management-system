package sholomiy.yuriy.catalogue.controllers;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sholomiy.yuriy.catalogue.entity.Catalogue;
import sholomiy.yuriy.catalogue.service.CatalogueService;

@RestController
@RequestMapping("/api/v1/catalogue")
@RequiredArgsConstructor
public class CatalogueController {

	private final CatalogueService catalogueService;

	@GetMapping(path = "/all")
	public ResponseEntity<Collection<Catalogue>> getCatalogue() {
		final Collection<Catalogue> catalogue = catalogueService.getCatalogue();
		return catalogue == null || catalogue.isEmpty() ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(catalogue);
	}

	@GetMapping
	public ResponseEntity<Collection<Catalogue>> getCatalogueByProducts(@RequestParam Collection<Long> ids) {
		final Collection<Catalogue> catalogue = catalogueService.getCatalogueByIDs(ids);
		return catalogue == null || catalogue.isEmpty() ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(catalogue);
	}

}
