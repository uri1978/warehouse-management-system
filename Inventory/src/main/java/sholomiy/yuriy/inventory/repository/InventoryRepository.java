package sholomiy.yuriy.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sholomiy.yuriy.inventory.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	List<Inventory> findAllByProductIdIn(Iterable<Long> id);

}
