package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.domain.LoginInfo;
import com.base.service.AccountService;
import com.base.service.UserInfoService;
import com.base.tools.JSONResult;
import com.base.tools.UserContext;
import com.controller.util.MustLogin;

/**
 * 处理个人中心 
 */

@Controller
public class PersonalController {
	
	@Autowired
	private AccountService accountService; 
	
	@Autowired
	private UserInfoService userInfoService;
	
	@MustLogin
	@RequestMapping("/personal")
	public String personalCenter(Model model){
		
		LoginInfo info = UserContext.getCurrent(); //获取当前用户信息
System.out.println(info + " 近来");
System.out.println(this.userInfoService.getUserInfoById(info.getId()));
System.out.println(this.accountService.getAccountById(info.getId()));
		model.addAttribute("userinfo", this.userInfoService.getUserInfoById(info.getId()));
		model.addAttribute("account", this.accountService.getAccountById(info.getId()));
		
		return "personal"; //freemaker
	}
	
	/**
	 * 绑定手机号
	 * @param phoneNumber
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping("/bindPhone")
	@ResponseBody
	public JSONResult bindPhone(String phoneNumber, String verifyCode){
		JSONResult json = new JSONResult();
		try{
System.out.println();
			this.userInfoService.bindIPhoneNumber(phoneNumber, verifyCode);
		}catch (Exception e) {
			json.setMsg(e.getMessage());
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}

}
