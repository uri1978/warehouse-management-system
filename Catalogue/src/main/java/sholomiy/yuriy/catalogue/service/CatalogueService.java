package sholomiy.yuriy.catalogue.service;

import java.util.Collection;

import sholomiy.yuriy.catalogue.entity.Catalogue;

public interface CatalogueService {

	Collection<Catalogue> getCatalogue();

	Collection<Catalogue> getCatalogueById(Collection<Long> ids);

	Collection<Catalogue> getCatalogueByIDs(Collection<Long> productIDs);

}
