package com.gjun.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.nio.charset.Charset;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.SendData;
import com.gjun.domain.SendMessage;
import com.gjun.domain.WebHookData;
import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//訂閱Line Messaging API WebHook for Line Bot
//註冊Resurce file
@Tag(name = "接受LineBot WebHook API",description="掛勾在特定LineBot WebHok->推送使用者聊天訊息->進行Workflow->回應訊息到使用者")
@PropertySource(value = { "classpath:appurl.properties" })
@RestController
public class WebHookController {
	//注入Resource value
	@Value(value = "${line.sendpushmessage}")
	private String sendPushURL;
	@Value(value = "${line.token}")
	private String token;
	
	//接受訂閱資料進來(加入好友 封鎖 或者是傳送訊息....)
	@Operation(summary="訂閱接受Line Messaging API推播的內容")
	@RequestMapping(path= {"/webhook/receive/rawdata"}
	,method= {RequestMethod.POST},consumes="application/json")
	public String receiveWebHook(@RequestBody()WebHookData data) {
		System.out.println(sendPushURL);
		String retmsg=null;
		
		String type=data.events[0].type;
		//取出user id
		String userid=data.events[0].source.userId;
		switch(type) {
		case "message":
			//聊天
			//判斷是文字
			if(data.events[0].message.type.equals("text")) {
				//取出訊息
				String message=data.events[0].message.text;
				//TODO 配合NLP(自然語言解析AI推測)
				//...
				//回應內容
				String content=String.format("鸚鵡說:%s 使用者是:%s",message,userid);
				//TODO 送訊息到Line Bot 推送到使用者端(已讀已回) send push message api
				//HttpClient
				CloseableHttpClient client=HttpClients.createDefault();
				//設定前端請求方式
				HttpPost request=new HttpPost();
				//設定URL
				try {
					request.setURI(new URI(sendPushURL));
					//設定Request物件的Header
					request.setHeader("Content-Type","application/json");
					//設定Token
					request.setHeader("Authorization",token);
					//設定Http Body
					//建構物件
					SendMessage msg=new SendMessage();
					msg.type="text";
					msg.text=content;
					//窗口物件
					SendData sendData=new SendData();
					sendData.to=userid;
					sendData.messages=new SendMessage[] {msg};
					//序列化成Json String
					Gson gson=new Gson();
					String sendJson=gson.toJson(sendData);
					//封裝成Entity
					request.setEntity(new StringEntity(sendJson,
							Charset.forName("UTF-8")));
					//執行request
					client.execute(request);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					retmsg=e.getMessage();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					retmsg=e.getMessage();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					retmsg=e.getMessage();
				}
				
			}
			break;
		case "follow":
			//加入好友 或者解除封鎖
			break;
		case "unfollow":
			//封鎖
			break;
		} 
		return retmsg;
		//WebHook 訂閱的服務位址 往往無須回應值
		
	}

}