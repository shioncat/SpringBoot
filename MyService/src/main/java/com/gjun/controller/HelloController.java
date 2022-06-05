package com.gjun.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.Greeting;
//POJO(Plain Old Java Object無須特定繼承父類別或者實作介面) 可保持向前向後的相容性
//Candy Syntax(語法糖架構)重新封裝類別或者方法 進行功能或者屬性擴充(往往屬於定義)
//定義這一個類別是一個REST Controller
@RestController
public class HelloController {
	//Spring BeanFactory(工廠模式)
	//Injection(依賴注入(DI)--要互動使用) 採用屬性注入 或者建構子注入
	@Autowired
	public Greeting greetings;
	//自訂建構
	public HelloController() {
		System.out.println("Hello控制項物件產生了...");
	}

	//定義成一個Action(控制流程程序)
	//定義端點End Point /定義請求方式(Request Method) 或者具有預設
	@RequestMapping("/")
	public String sayHello() {
		return "Hello World";
	}
}
