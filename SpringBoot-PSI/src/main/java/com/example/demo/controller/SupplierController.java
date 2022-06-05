package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("supplier", new Supplier());
		model.addAttribute("suppliers", supplierRepository.findAll());
		return "supplier";
	}
	
	@RequestMapping("/create")
	public String create(Supplier supplier) {
		supplierRepository.save(supplier);
		return "redirect:/supplier/";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Supplier supplier = supplierRepository.findById(id).get();
		model.addAttribute("supplier", supplier);
		return "supplier-update";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, Supplier supplier) {
		supplierRepository.save(supplier);
		return "redirect:/supplier/";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		supplierRepository.deleteById(id);
		return "redirect:/supplier/";
	}
	
	
}
