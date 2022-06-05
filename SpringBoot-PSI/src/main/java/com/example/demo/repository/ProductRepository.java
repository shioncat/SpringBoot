package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.view.Inventory;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(nativeQuery = true, value = "select p.id, p.name, p.cost, p.price,\n"
			+ "(select sum(amount) from purchase_items where product_id = p.id limit 1) as amount1,\n"
			+ "(select sum(amount) from order_items where product_id = p.id limit 1) as amount2\n"
			+ "from products p")
	List<Inventory> queryInventory();
	
	@Query(nativeQuery = true, value = "select p.id, p.name, p.cost, p.price,\n"
			+ "(select sum(amount) from purchase_items where product_id = p.id limit 1) as amount1,\n"
			+ "(select sum(amount) from order_items where product_id = p.id limit 1) as amount2\n"
			+ "from products p where p.id=:id")
	Inventory findInventoryById(Long id);
	
}
