package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.service.VerifyCodeService;
import com.base.tools.JSONResult;

@Controller
public class VerifycodeController {
	
	
	@Autowired
	private VerifyCodeService VoService;
	

	@RequestMapping("/sendVerifyCode")
	@ResponseBody
	public JSONResult sendPhoneVerifyCode(String phoneNumber){
		JSONResult json = new JSONResult();
		try {
System.out.println(phoneNumber + "手机号");
			this.VoService.sendVerifyCodeForPhone(phoneNumber);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}

}
