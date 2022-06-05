package com.gjun.webhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gjun")
public class WebHookApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebHookApplication.class, args);
	}

}
