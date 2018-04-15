package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.query.IpLogQueryObject;
import com.base.service.IpLogService;
import com.base.tools.UserContext;

/**
 * 后台登录日志
 */

@Controller
public class IpLogController {
	
	@Autowired
	private IpLogService ipLogService;
	
	@RequestMapping("/iplog")
	public String iplogHistory(@ModelAttribute("qo") IpLogQueryObject qo, Model model){
System.out.println("进入");
		qo.setUserName(UserContext.getCurrent().getUserName()); //获取用户名
		model.addAttribute("pageResult", this.ipLogService.query(qo));
		return "ipLog/list";
	}
	
	
}
