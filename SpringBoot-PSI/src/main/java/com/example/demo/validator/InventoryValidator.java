package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.entity.OrderItem;
import com.example.demo.entity.view.Inventory;
import com.example.demo.repository.ProductRepository;

@Component
public class InventoryValidator implements Validator {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return OrderItem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderItem orderItem = (OrderItem)target;
		int amount = orderItem.getAmount();
		// 2. 買進數量必須 >0 
		if(amount < 0) {
			errors.rejectValue("amount", "orderitem.amount.required", "請輸入大於0數量");
		}
	
		if(orderItem.getAmount() == null || orderItem.getAmount() == 0) {
			errors.rejectValue("amount", "orderitem.amount.required", "請輸入數量");
		} else {
			Inventory inventory = productRepository.findInventoryById(orderItem.getProduct().getId());
			int remaining = 0;			
			try {
				// amount1 : 進貨/採購數量
				// amount2 : 銷售/訂單數量
				
				
				if(inventory.getAmount1() == null) {
					// 沒有進貨資料
					errors.rejectValue("amount", "inventory.amount.null", "此商品無進貨資料");
					return;
				}
				if(inventory.getAmount2() == null) {
					remaining = inventory.getAmount1();
				} else {
					remaining = inventory.getAmount1() - inventory.getAmount2();
				}
				if(orderItem.getAmount() > remaining) {
					errors.rejectValue("amount", "inventory.amount.insufficient", "此商品庫存不足（目前庫存：" + remaining + "）");
				}
				
				
			} catch (Exception e) {
				errors.rejectValue("amount", "inventory.amount.error", "其他錯誤：" + e);
			}
			
		}
		
	}
	
}
