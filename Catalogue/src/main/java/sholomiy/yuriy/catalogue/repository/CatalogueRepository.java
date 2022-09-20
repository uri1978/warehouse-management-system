package sholomiy.yuriy.catalogue.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sholomiy.yuriy.catalogue.entity.Catalogue;

@Repository
public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {

	Collection<Catalogue> findAllByProductIdIn(Iterable<Long> productIDs);

}
