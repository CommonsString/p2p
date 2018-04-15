package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.domain.LoginInfo;
import com.base.service.LoginInfoService;
import com.base.tools.JSONResult;


@Controller
public class RegisterController {
	
	@Autowired
	private LoginInfoService service;
	
	//注册
	@RequestMapping("/register")
	@ResponseBody
	public JSONResult register(String username, String password){
System.out.println("账号: " + username + " 密码： " + password);
		JSONResult json = new JSONResult();
		try{
			service.registerUser(username, password);
System.out.println("调用添加");
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg("系统异常！");
		}
		return json;
	}
	
	//查重账户
	@RequestMapping("/checkUsername")
	@ResponseBody
	public Boolean checkUsername(String username){
System.out.println("检查用户名：" + username);
		boolean flag = false;
		try {
			if(username != null && !"".equals(username)){
				int result = service.getUserIsCopy(username);
System.out.println(result + " : result");
				if(result > 0){
					flag = true; //存在
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return !flag;
	}
	
	
	//登录
	@RequestMapping("/login")
	@ResponseBody
	public JSONResult loginUser(String username, String password, HttpServletRequest request){
		JSONResult json = new JSONResult();
System.out.println(username + " : "  + password);
		if(username != null && !"".equals(username)
				&& password != null && !"".equals(password)){
			LoginInfo info = this.service.loginUserInfo(username, DigestUtils.md5DigestAsHex(password.getBytes()), 
					request.getRemoteAddr(), LoginInfo.USER_CLIENT); //前台登录
			if(info == null){
				json.setSuccess(false);
				json.setMsg("账号或密码错误！");
			}
		}else{
			json.setSuccess(false);
			json.setMsg("账号或密码为空！");
		}
System.out.println(json.toString());
		return json;
	}
	
	
}
