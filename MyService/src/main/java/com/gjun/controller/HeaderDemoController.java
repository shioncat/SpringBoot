package com.gjun.controller;

import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderDemoController {
	//透過參數注入Servlet api-HttpServletRequest 
	@GetMapping(path="/passing/header",produces="text/plain")
	public String getHeaders(HttpServletRequest request) {
		String who=request.getParameter("who"); //QueryString or Form Field name attribute
		String key=request.getHeader("apikey");
		//Header apikey驗證(存取資料庫驗證)
		return who+" "+key;
	}
	
	//透過參數擷取Header
	@GetMapping(path="/hr/data/id/{empid}/rawdata",produces="text/plain;charset=UTF-8")
	public String securityData(@RequestHeader("CK")String apikey,
			@PathVariable("empid")String id) {
		//Processing...
		String content=String.format("apikey:%s 查詢員工編號:%s",apikey,id);
		return content;
	}
	//透過參數取出所有的Request Header(只取第一個值)
	@GetMapping(path="/headers/all",produces="text/plain;charset=UTF-8")
	public String allHeaderKey(@RequestHeader Map<String,String> headers) {
		//走訪每一個Header Name 輸出Value
		StringBuilder builder=new StringBuilder();
		headers.forEach(
				(key,value)->{
					builder.append(String.format("Header Name:%s Value:%s\n",key,value));
				}
				);
		String content=builder.toString();
		return content;
	}
	//透過參數注入Header 取出多個值操作方式
	@GetMapping(path="/headers/values/rawdata",produces="text/plain;charset=UTF-8")
	public String allHeaderMultiValues(@RequestHeader MultiValueMap<String,String> headers) {
		//走訪
		StringBuilder builder=new StringBuilder();
		headers.forEach(
				(key,values)->
				{
					String line=String.format("Header Key:%s Values:%s\n",
							key,
							values.stream().collect(Collectors.joining(" | "))
							);
					builder.append(line);
				}
				);
		return builder.toString();
	}
	
	//傳遞HttpHeader物件進來
	@GetMapping(path="/headers",produces="text/plain;charset=UTF-8")
	public String headersPassing(@RequestHeader HttpHeaders headers) {
		String hostName=headers.getHost().getHostName();
		String urlString="http://"+hostName+":"+headers.getHost().getPort();
		return urlString;
	}
	

}