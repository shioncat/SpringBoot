package com.example.demo.testing.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Department;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DepartmentRepository;

@SpringBootTest
public class CreateDepartment {
	@Autowired
	DepartmentRepository departmentRepository;
	@Test
	public void start() {
		Department d1 = new Department();
		d1.setName("採購部");
		Department d2 = new Department();
		d2.setName("業務部");
		
		departmentRepository.save(d1);
		departmentRepository.save(d2);
	}
	
}
