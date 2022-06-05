package com.gjun.myservice;

import java.net.http.HttpClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//聲明多個Bean配置 可以觸發多個元件掃描器
@SpringBootApplication
@ComponentScan("com.gjun") //掃描控制項配置 Compoennt/Bean...
public class MyServiceApplication {

	//Boot Entry Point
	public static void main(String[] args) {
		
		//啟動服務(Engine--隱含注入服務與Middle ware)
		//啟動了@Configuration/@EnableAutoConfiguration/@ComponentScan
		SpringApplication.run(MyServiceApplication.class, args);
	}

}
