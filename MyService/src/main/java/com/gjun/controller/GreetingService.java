package com.gjun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.controller.component.AutoGenId;

@RestController
@RequestMapping("/greeting/rawdata")
public class GreetingService {
	//屬性注入
	@Autowired
	private AutoGenId id;
	
	@GetMapping()
	public String engHello() {
		return id.generate()+" say Hello World";
	}
	@PutMapping()
	public String twHello() {
		return "吃飽沒";
	}
}
