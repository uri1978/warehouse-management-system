package sholomiy.yuriy.catalogue;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import sholomiy.yuriy.catalogue.entity.Catalogue;
import sholomiy.yuriy.catalogue.entity.Product;
import sholomiy.yuriy.catalogue.repository.CatalogueRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CatalogueRepositoryTest {

	@Autowired
	private CatalogueRepository repository;
	
	private Catalogue cat1, cat2;
	private Product prod1, prod2;

	@BeforeEach
	void setUp() throws Exception {

		prod1 = new Product(100L, "Test prod1");
		prod2 = new Product(101L, "Test prod2");

		cat1 = new Catalogue(100L, prod1, 1);
		cat2 = new Catalogue(101L, prod2, 10);

		cat1 = repository.save(cat1);
		cat2 = repository.save(cat2);

	}

	@Test
	void testFindAll() {
		assertThat(repository.findAll().iterator().hasNext()).isTrue();
	}

	@Test
	void testFindAllById() {
		assertThat(repository.findAllById(List.of(cat1.getId(), cat2.getId())).size() == 2).isTrue();
	}
	
}
