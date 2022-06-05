package com.gjun.controller;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.Customers;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

@RestController
public class CustomersController {
	//Attribute注入
	@Autowired
	private DataSource datasource;
	
	public CustomersController() {
		System.out.println("客戶資料控制器產生個體...");
	}
	
	@RequestMapping("/ds/demo")
	public String connectionStateDemo() {
		String content=null;
		try {
			content= datasource.getConnection().getCatalog();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	//回傳JavaBean
	@RequestMapping(path="/customers/rawdata",method={RequestMethod.GET})
	public Customers getCustomers(Customers customers) {
		customers.setCustomerId("001");
		customers.setCompanyName("巨匠電腦");
		customers.setAddress("台北市公園路");
		customers.setPhone("02-1234567");
		customers.setCountry("中華民國");
		return customers;
	}

}
