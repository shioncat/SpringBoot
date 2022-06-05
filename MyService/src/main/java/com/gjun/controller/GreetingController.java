package com.gjun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.controller.component.EmployeesGeneratedComponent;
import com.gjun.domain.Greeting;

//對外對內的控制器
//POJO(Plain Old Java Object)
@RestController   //通稱web service
public class GreetingController {
	public GreetingController() {
		System.out.println("Greeting 控制器產生個體...");
	}
	//Property Injection(屬性注入)
	@Autowired
	private Greeting gjunGreeting;
	//屬性注入元件(Component)
	@Autowired
	private EmployeesGeneratedComponent generate;
	//提供Action 回應打招呼訊息 前端base on http
	//需要揭露URL End Point/明確Request Method...
	@RequestMapping(path="/hello/rawdata",method= {RequestMethod.GET})
	public Greeting helloGreeting() {
		//操作被注入的Greeting物件
		return gjunGreeting;
	}
	
	//Method 參數注入 配合類型(具有可建構架構) 直接建構一個新個物件注入
	@GetMapping("/hello/component")
	public Greeting helloInjection(Greeting gjunGreeting) {
		gjunGreeting.setWho(generate.generate()); //呼叫注入的元件個體功能
		gjunGreeting.setMessage("世界和平");
		return gjunGreeting;
	}
	
}
