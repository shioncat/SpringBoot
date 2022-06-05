package com.gjun.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.UbikeData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//串接Ubike 及時Open Data
@RestController
public class UbikeController {
	
	@GetMapping(path="/ubike/area/{sarea}/rawdata",produces="application/json")
	public UbikeData[] searchByArea(@PathVariable("sarea")String sarea){
		//建構一個HttpClient物件
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet("https://tcgbusfs.blob.core.windows.net/dotapp/youbike/v2/youbike_immediate.json");
	    List<UbikeData> data=null;
	   
	    try {
			CloseableHttpResponse response = client.execute(httpGet);
			InputStream is=response.getEntity().getContent();
			//讀取文字串內容Json
			InputStreamReader reader=new InputStreamReader(is,"UTF-8");
			
			//反序列化
			Gson gson=new Gson();
			Type listType = new TypeToken<List<UbikeData>>(){}.getType();
			data=gson.fromJson(reader,listType);
			var result=data.stream().filter(c->c.sarea==sarea).toArray();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
		
	}

}
