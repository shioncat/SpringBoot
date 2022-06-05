package com.example.demo.testing.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@SpringBootTest
public class CreateProduct {
	@Autowired
	ProductRepository productRepository;
	@Test
	public void start() {
		Product p1 = new Product();
		p1.setName("P1");
		p1.setCost(10);
		p1.setPrice(15);
		
		Product p2 = new Product();
		p2.setName("P2");
		p2.setCost(20);
		p2.setPrice(25);
		
		Product p3 = new Product();
		p3.setName("P3");
		p3.setCost(30);
		p3.setPrice(35);
		
		productRepository.save(p1);
		productRepository.save(p2);
		productRepository.save(p3);
	}
	
}
