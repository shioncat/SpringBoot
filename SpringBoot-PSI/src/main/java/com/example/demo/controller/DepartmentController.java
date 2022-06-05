package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@RequestMapping("/")
	public String index(Model model) {
		Department department = new Department();
		List<Department> departments = departmentRepository.findAll();
		model.addAttribute("department", department);
		model.addAttribute("departments", departments);
		return "department";
	}
	
	@RequestMapping("/create")
	public String create(Department department) {
		departmentRepository.save(department);
		return "redirect:/department/";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Department department = departmentRepository.findById(id).get();
		model.addAttribute("department", department);
		return "department-update";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, Department department) {
		department.setId(id);
		departmentRepository.save(department);
		return "redirect:/department/";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		departmentRepository.deleteById(id);
		return "redirect:/department/";
	}
	
}
