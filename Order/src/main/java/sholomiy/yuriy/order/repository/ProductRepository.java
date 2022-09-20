package sholomiy.yuriy.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sholomiy.yuriy.order.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
