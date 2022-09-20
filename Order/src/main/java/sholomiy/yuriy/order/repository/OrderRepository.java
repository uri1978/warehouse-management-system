package sholomiy.yuriy.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sholomiy.yuriy.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
