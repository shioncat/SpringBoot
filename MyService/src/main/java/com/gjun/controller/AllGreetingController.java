package com.gjun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/allgreeting/rawdata")
public class AllGreetingController {
	@GetMapping()
	public String sayHello() {
		return "Hello World";
	}
	@PostMapping()
	public String sayTwHello() {
		return "Hello";
	}

}
