package com;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.base.domain.LoginInfo;
import com.base.domain.UserInfo;
import com.base.service.LoginInfoService;
import com.base.service.UserInfoService;
import com.base.service.VerifyCodeService;

public class SpringTest {

	private ApplicationContext content;
//	private LoginInfoService service;
	
//	private UserInfoService userService;
	private VerifyCodeService verifyCode;
	
	@Before
	public void before(){
		this.content = new ClassPathXmlApplicationContext("application-core.xml");
//		this.service = (LoginInfoService) content.getBean("loginInfoServiceImpl");
//		this.userService = (UserInfoService) content.getBean("userInfoServiceImpl");
		this.verifyCode =  (VerifyCodeService) content.getBean("verifyCodeServiceImpl");
	}
		
	@Test
	public void testOne(){
//		LoginInfo info = new LoginInfo();
//		info.setUserName("admin");
//		info.setPassword("123465");
//		info.setState(1);
//		int result = this.service.addUser(info);
//		this.service.loginUserInfo("commonsstring", "972c476203703523268a8302582d3a95");
		verifyCode.sendVerifyCodeForPhone("1111");
	}
	
}
