package com.example.demo.entity.view;

public interface Inventory {
	Long getId();
	String getName();
	Integer getCost();
	Integer getPrice();
	Integer getAmount1(); // 採購數量
	Integer getAmount2(); // 銷貨(訂購)數量
	
}
