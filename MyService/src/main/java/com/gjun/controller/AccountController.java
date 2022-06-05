package com.gjun.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.Account;

//個人帳戶資料維護與查詢
//@CrossOrigin(origins = "*")  //碰到delete傳送方式 不允許
@RestController
public class AccountController {
	
	
	@CrossOrigin(origins={"http://localhost:80"})
	//查詢特定帳戶
	@GetMapping(path="/account/id/{accid}/rawdata",produces="application/json")
	public Account getAccount(@PathVariable("accid")String id) {
		//調用帳戶物件...
		Account acct=new Account();
		acct.setId(id);
		acct.setBalance(1000000);
		acct.setName("張三丰");
		acct.setBank("台灣銀行");
		return acct;
	}
	
	//@CrossOrigin(origins={"http://localhost:8080"})
	@PutMapping(path="/account/remove/id/{accid}/rawdata",produces="application/json")
	public Account removeAccount(@PathVariable("accid")String id) {
		//調用帳戶物件...
		Account acct=new Account();
		acct.setId(id);
		acct.setBalance(1000000);
		acct.setName("張三丰");
		acct.setBank("台灣銀行");
		return acct;
	}

}
