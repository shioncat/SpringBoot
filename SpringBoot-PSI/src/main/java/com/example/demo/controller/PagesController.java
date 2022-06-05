package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PagesController {
	
	@RequestMapping("/")
	public String index() {
		return "psi-tables";
	}
	
}
