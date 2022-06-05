package com.gjun.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	//佈署OpenApi物件
	@Bean
	public OpenAPI initOpenApi() {
		return new OpenAPI().info(
				new Info()
				.title("Eric 雲端服務系統")
				.description("巨匠電腦 金融產業Sample")
				.version("V1.0")
				);
		}
}