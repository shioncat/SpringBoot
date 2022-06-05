package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping("/")
	//@ResponseBody
	public String index(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("customers", customerRepository.findAll());
		return "customer";
	}
	
	@RequestMapping("/create")
	public String create(Customer customer) {
		customerRepository.save(customer);
		return "redirect:/customer/";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Customer customer = customerRepository.findById(id).get();
		model.addAttribute("customer", customer);
		return "customer-update";
	}
	
	@RequestMapping("/update/{id}") // 要加入 id 設定，才能指向此筆 id 進行修改，不然會變成新增
	public String update(Customer customer) {
		//customer.setId(id);
		customerRepository.save(customer);
		return "redirect:/customer/";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		customerRepository.deleteById(id);
		return "redirect:/customer/";
	}
}
