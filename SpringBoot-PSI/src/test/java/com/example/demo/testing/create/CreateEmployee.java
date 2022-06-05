package com.example.demo.testing.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@SpringBootTest
public class CreateEmployee {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Test
	public void start() {
		Department d1 = departmentRepository.findById(1L).get();
		Department d2 = departmentRepository.findById(2L).get();
		
		Employee e1 = new Employee(); 
		e1.setName("John");
		Employee e2 = new Employee(); 
		e2.setName("Mary");
		
		// 建立關聯
		e1.setDepartment(d1);
		e2.setDepartment(d2);
		
		// 保存
		employeeRepository.save(e1);
		employeeRepository.save(e2);
		
		
	}
	
}
