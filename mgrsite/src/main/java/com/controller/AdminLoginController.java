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

/**
 * 管理员登录逻辑 
 * 后台
 */

@Controller
public class AdminLoginController {
	
	@Autowired
	private LoginInfoService loginInfoService;
	
	
	@RequestMapping("/login")
	@ResponseBody
	public JSONResult adminLogin(String username, String password, HttpServletRequest request){
System.out.println("进入");
		JSONResult json = new JSONResult();
		if(username != null && !"".equals(username)
				&& password != null && !"".equals(password)){		
			String ipPatn = request.getRemoteAddr();
			String tempPassword = DigestUtils.md5DigestAsHex(password.getBytes());
			LoginInfo info = this.loginInfoService.loginUserInfo(username, tempPassword, ipPatn, LoginInfo.USER_MANAGER);
			if(info == null){
				json.setSuccess(false);
				json.setMsg("账号或密码错误！");
			}
		}else{
			json.setSuccess(false);
			json.setMsg("账号或密码为空！");
		}
		return json;
	}
	
	
	/**
	 * 返回后台首页
	 */
	@RequestMapping("/index")
	public String indexMagager(){
		return "main";
	}

}
