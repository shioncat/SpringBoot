package com.gjun.controller.component;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.gjun.domain.MemberBean;
//Spring Bean Configuration
@Configuration
public class EntityBeanConfiguration {
	
	//生產一個MemberBean物件
	//為個體物件
	@Bean("member")
	//@Scope("prototype")
	@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public MemberBean createMember() {
		return new MemberBean();
	}
	
	
}