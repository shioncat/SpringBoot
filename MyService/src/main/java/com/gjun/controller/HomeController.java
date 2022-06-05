package com.gjun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//MVC控制器(經由端點 調用樣板下的頁面.html)
@Controller
public class HomeController {
	//Action 進行控制流程
	//採用Http Get 回應頁面旗標給DispatcherServlet進行調用
	@GetMapping(path="/")
	public String home(Model model) {
		//調用頁面
		model.addAttribute("com","巨匠電腦");
		return "index";
	}
}
