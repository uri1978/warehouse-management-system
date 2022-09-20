package sholomiy.yuriy.catalogue.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sholomiy.yuriy.catalogue.entity.Catalogue;
import sholomiy.yuriy.catalogue.repository.CatalogueRepository;

@Service
@RequiredArgsConstructor
public class MySQLCatalogueService implements CatalogueService {

	private final CatalogueRepository catalogueRepository;

	@Override
	public Collection<Catalogue> getCatalogueById(Collection<Long> ids) {
		return catalogueRepository.findAllById(ids);
	}

	@Override
	public Collection<Catalogue> getCatalogue() {
		return catalogueRepository.findAll();
	}

	@Override
	public Collection<Catalogue> getCatalogueByIDs(Collection<Long> productIDs) {
		return catalogueRepository.findAllByProductIdIn(productIDs);
	}

}
