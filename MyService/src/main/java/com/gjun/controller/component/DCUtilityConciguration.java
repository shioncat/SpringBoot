package com.gjun.controller.component;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

@Configuration
public class DCUtilityConciguration {
	@Bean("datasource")
	@Scope("singleton")
	//@Scope("prototype")
	public DataSource dataSource() {
		System.out.println("DataSource Singleton 物件");
		//產生一個獨體資料庫來源物件
		SQLServerDataSource datasource=new SQLServerDataSource();
		datasource.setURL("jdbc:sqlserver://localhost;instance=MSSQLSERVER;"
				+ "databaseName=NORTHWND");
		datasource.setUser("sa");
		datasource.setPassword("1234");
		return datasource;
	}
}