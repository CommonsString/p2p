package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.query.IpLogQueryObject;
import com.base.service.IpLogService;
import com.base.tools.UserContext;
import com.controller.util.MustLogin;


/**
 * 登录日志 
 */
@Controller
public class IpLogController {
	
	@Autowired
	private IpLogService service;
	
	//登录日志列表
	@MustLogin
	@RequestMapping("/iplog")
	public String ipLogHistory(@ModelAttribute("qo")IpLogQueryObject qo, Model model){
		//设置用户名
		qo.setUserName(UserContext.getCurrent().getUserName());
		model.addAttribute("pageResult", this.service.query(qo));
System.out.println(this.service.query(qo));
System.out.println("进入");
		return "iplog_list";
	}

}
