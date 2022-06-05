package com.gjun.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.DHT;
import com.gjun.domain.DHTIoTData;
import com.gjun.domain.DhtData;
import com.gjun.domain.Message;
import com.google.gson.Gson;

//教室物聯網getway
@RestController
@PropertySource("classpath:appurl.properties") //Register Resource File
public class IoTController {
	//Property Injection
	@Value("${iot.posturl}")
	private String postURL;
	@RequestMapping(path="/iot/dht/add",method= {RequestMethod.POST}
	,consumes="application/json",produces="application/json")
	public String dhtSender(@RequestBody DhtData data) {
		//串接CHT IoT服務(HttpClient/Http Request/Header???/body JSON
		String urlString=String.format(postURL,"29269655917");
		//1.使用HttpClient相關工廠產生一個HttpClient物件
		//使用介面多型化 向前向後相容性考量
		HttpClient client=HttpClients.createDefault();
		HttpPost request=new HttpPost(); //Request物件
		
			try {
				request.setURI(new URI(urlString));
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		//Http Request Header(Content-Type:application/json and CK:xxxxxxx)
		request.setHeader("Content-Type","application/json");
		request.setHeader("CK","DKWUKE1YC2BUZ1MAUH");
		
		//TODO 建構DHT  整理Http  Request Body HttpEntity(or Http Content)
//		DHT dht=new DHT();
//		dht.temper=data.getTemper();
//		dht.humi=data.getHumi();
//		dht.location=data.getLocation();
		//序列化成字串
		Gson gson=new Gson();
		String dhtString=gson.toJson(data);
		//建構DHTIotData物件
		DHTIoTData dhtIot=new DHTIoTData();
		dhtIot.id="DHT22";
		dhtIot.save=true;
		
		
		dhtIot.value=new String[] {dhtString};
		//最外面是一個json array [....]
		ArrayList<DHTIoTData> list=new ArrayList<DHTIoTData>();
		list.add(dhtIot);
		String jsonData=gson.toJson(list);
		System.out.println(jsonData);
		//Request->Content->HttpEntity->StringEntity
		StringEntity entity;
		try {
			entity = new StringEntity(jsonData);
			request.setEntity(entity); //HttpEntity介面
			client.execute(request); //正式透過Client提出請求
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonData;		
	}

}
