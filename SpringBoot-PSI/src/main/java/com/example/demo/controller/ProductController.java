package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("products", productRepository.findAll());
		return "product";
	}
	
	@RequestMapping("/create")
	public String create(Product product,BindingResult result,Model model) {
		if(result.hasErrors()) { // 是否有錯誤發生			
			model.addAttribute("products", productRepository.findAll());
			System.out.println("有錯誤發生");
			return "redirect:/product/";
		}
		productRepository.save(product);
		return "redirect:/product/";
	}

	
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Product product = productRepository.findById(id).get();
		model.addAttribute("product", product);
		return "product-update";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, Product product) {
		productRepository.save(product);
		return "redirect:/product/";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		productRepository.deleteById(id);
		return "redirect:/product/";
	}
	
	
}
