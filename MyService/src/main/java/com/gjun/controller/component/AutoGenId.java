package com.gjun.controller.component;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;
//定義類別為元件(自動配置為Bean)
@Component
public class AutoGenId {
	public AutoGenId() {
		System.out.println("自動產生姓名元件產生...");
	}
    public String generate() {
        var names = List.of("張三丰", "張無忌", "張翠珊", "張泰山", "張三");
        var r = new Random();
        int i = r.nextInt(names.size());
        return names.get(i);
    }
}
