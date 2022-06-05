package com.gjun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.Customers;
import com.gjun.domain.Message;

//客戶資料服務
@RestController
public class GjunCustomersController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//傳遞客戶編號 進行單一筆客戶資料查詢服務作業 參數傳遞採用QueryString
	//採用Request method-GET
	@GetMapping(path={"/customers/qry/id/{custid}/rawdata"}) 
	public ResponseEntity<Object> customersQryById(@PathVariable(name="custid",required=true)String customerId) {
		//確認是否傳遞合法客戶編號
		//2.透過注入的Data Source(or DAO物件模組)進行查詢
		String sql="SELECT CustomerID,CompanyName,Address,Phone,Country FROM Customers WHERE CustomerID=?";
		ResponseEntity<Object> response=null;
		//單筆查詢
		try {
			Customers cust=jdbcTemplate.queryForObject(
					sql,
					(rs,num)->{
						Customers customer=new Customers();
						//注入屬性
						customer.setCustomerId(rs.getString("CustomerID"));
						customer.setCompanyName(rs.getString("CompanyName"));
						customer.setAddress(rs.getString("Address"));
						customer.setPhone(rs.getString("Phone"));
						customer.setCountry(rs.getString("Country"));
						return customer;
					}
					,
					customerId
					);
			//3.將查詢結果序列化為Json(Status code-200)
			//3.2 找不到是否回應不同的Json文件格式(不同Http Status code-400)
			//建構ResponseEntity物件
			response=new ResponseEntity<>(cust,HttpStatus.OK);
			
		}catch(DataAccessException ex){
			Message msg=new Message();
			msg.setCode(400);
			msg.setMsg(String.format("客戶編號:%s 查無資料!!!",customerId));
			
			response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	//特定前端參數名稱應對
	@GetMapping(path={"/customers/qry2","/customers/qryid2"}) 
	public String customersQryById2(@RequestParam("cid")String customerId) {
		//確認是否傳遞合法客戶編號
		//2.透過注入的Data Source(or DAO物件模組)進行查詢
		//3.將查詢結果序列化為Json(Status code-200)
		//3.2 找不到是否回應不同的Json文件格式(不同Http Status code-400)
		return customerId;
	}
	
	//Method Overloading
	//特定前端參數名稱應對
	@GetMapping(path={"/customers/customerid/country"}) 
	public String customersQryById2(@RequestParam("cid")String customerId,
				@RequestParam("country")String country) {
			//確認是否傳遞合法客戶編號
			//2.透過注入的Data Source(or DAO物件模組)進行查詢
			//3.將查詢結果序列化為Json(Status code-200)
			//3.2 找不到是否回應不同的Json文件格式(不同Http Status code-400)
			return customerId+" "+country;
		}
	
	
	//傳遞參數 可以依照客戶編號 或者再配合國家別進行細部查詢
	@GetMapping(path={"/customers/customerid/orcountry"}) 
	public String customersQryById3(@RequestParam("cid")String customerId,
				@RequestParam(name="country",required=false)String country) {
			//確認是否傳遞合法客戶編號
			//2.透過注入的Data Source(or DAO物件模組)進行查詢
			//3.將查詢結果序列化為Json(Status code-200)
			//3.2 找不到是否回應不同的Json文件格式(不同Http Status code-400)
			return customerId+" "+country;
		}
	
	//傳遞參數 可以依照國家別 沒有傳遞預設為USA
	@GetMapping(path={"/customers/country"}) 
	public String customersQryByCountry(@RequestParam(name="country",defaultValue="USA")String country) {
				//確認是否傳遞合法客戶編號
				//2.透過注入的Data Source(or DAO物件模組)進行查詢
				//3.將查詢結果序列化為Json(Status code-200)
				//3.2 找不到是否回應不同的Json文件格式(不同Http Status code-400)
				return country;
			}
}