package com.gjun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.AppInformation;

@RestController
public class TestBeanScopeController {
	//Injection
	@Autowired
	private AppInformation information;
	
	//描述端點 與請求方法
	@RequestMapping(path="/showinfo/rawdata",method=RequestMethod.GET)
	public AppInformation showInfo() {
		return information;
	}

}