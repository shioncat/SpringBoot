package com.gjun.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//提供客戶資料維護的View(與控制流程)
@Controller
public class UiCustomersController {
	//提供客戶編號查詢View Page
	//Http Request-GET
	@GetMapping(path="/customers/qrybyid")
	public String customersQryId() {
		return "custqryid";
	}
	
}