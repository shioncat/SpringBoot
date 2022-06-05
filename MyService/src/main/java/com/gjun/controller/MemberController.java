package com.gjun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.MemberBean;

//會員作業處理Controller
@RestController
public class MemberController {
	public MemberController() {
		System.out.println("MemberController個體物件產生");
	}
	@RequestMapping("/getmember")
	public MemberBean memberGenerated(@Autowired MemberBean member) {
		//會員物件資訊設定
		member.setUserName("demo");
		member.setEmail("1111");
		member.setRealName("張三丰");
		member.setEmail("chung@cht.com.tw");
		return member;
	}

}
