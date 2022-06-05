package com.gjun.controller.component;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.gjun.domain.AppInformation;

//佈署可以重複使用的Spring Bean(元件Component)
@Configuration
public class EntityConfiguration {

	public EntityConfiguration () {
		System.out.println("Entity組態物件建構...");
	}
	//生命週期是什麼? Scope預設狀態
	@Bean("information")
	//@Scope("singletion")
	@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public AppInformation getInformation() {
		
		//建構物件(Instance)
		AppInformation infor=new AppInformation();
		infor.setAppName("財務系統...");
		infor.setAddress("台北市公園路");
		infor.setCopyLight("版權所有");
		return infor;
	}
}
