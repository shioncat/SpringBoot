package com.gjun.controller.component;
//抽取現有員工的元件

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class EmployeesGeneratedComponent {
	private List<String> employees;
	
	//建構子進行初始化
	public EmployeesGeneratedComponent() {
		System.out.println("員工物件選擇器物件產生....");
		employees=List.of(
				"張三丰","張無忌","張翠珊","張泰山"
				);
	}
	
	//動態抽取員工字串
	//Instance Method
	public String generate() {
		//建構隨機物件
		var r=new Random();
		int value=r.nextInt(employees.size());
		return employees.get(value);
	}

}
