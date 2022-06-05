package com.gjun.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.UBike;
import com.gjun.domain.UbikeData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//提供一個前端服務  串接另一個服務(Ubike Open Data Service)
@RestController
public class GjunUbikeController {
	
	//採用Path單座參數傳遞(區域) 查詢相關區域的Ubike即時資訊
	@GetMapping(path="/ubike/qry/area/{sarea}/rawdata",
			produces="application/json;charset=UTF-8")
	public List<UbikeData> ubikeQryByArea(@PathVariable("sarea")String area) {
		//HttpClient物件(類別多型化)
		CloseableHttpClient client=HttpClients.createDefault();
		//Ubike url
		String ubikeURL="https://tcgbusfs.blob.core.windows.net/dotapp/youbike/v2/youbike_immediate.json";
		//建構一個HttpGet物件(Request Method-GET)
		HttpGet get=new HttpGet(ubikeURL);
		List<UbikeData> data=null;
		//正式提出請求
		List<UbikeData> result=null;
		try {
			//提出請求 接回來Response物件(URL/Header/Body--HttpEntity)
			CloseableHttpResponse response=client.execute(get);
			
			//3.讀取回應的結果Json 
			//取出回應的內容(Http header/body)
			InputStream is=response.getEntity().getContent(); //java.net 
			InputStreamReader reader=new InputStreamReader(is,"UTF-8");
			//....即使讀取到字串 目的要反序列化成可操作的物件
			//4.如何將Json反序列化(JavaBean依據)成可以操作的集合物件 進行查詢
			//5 Gson 個體物件
			Gson gson=new Gson();
			Type listType = new TypeToken<List<UbikeData>>(){}.getType();
			data=gson.fromJson(reader,listType);
			//TODO 配合前端傳遞的區域進行查詢
			result=data.stream().filter(c->c.sarea.equals(area)).collect(Collectors.toList());
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//對外(Ubike Open Data)服務進行串些
		
		
		//5.查詢結果序列化回應
		return result;
	}

}
