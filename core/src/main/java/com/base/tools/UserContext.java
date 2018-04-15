package com.base.tools;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.base.domain.LoginInfo;
import com.base.verifycode.VerifyCodeVO;

/**
 * 用于存放当前用户的上下文
 * @author 
 *
 */
public class UserContext {
	
	public static final String USER_IN_SESSION = "logininfo";
	public static final String VERIFYCODE_IN_SESSION = "verifyCode"; //验证码

	
	private static HttpSession getSession() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
				.getRequest().getSession();
	}
	
	public static void putCurrent(LoginInfo info){
		//得到session,并把info放在session中
		getSession().setAttribute(USER_IN_SESSION, info);
	}
	
	public static LoginInfo getCurrent(){
		return (LoginInfo) getSession().getAttribute(USER_IN_SESSION);
	}
	
	//获取当前手机验证码
	public static VerifyCodeVO getCurrentVerifyCode(){
		return (VerifyCodeVO)getSession().getAttribute(VERIFYCODE_IN_SESSION);
	}
	
	
	//获取当前手机验证码
	public static void setVerifyCode(VerifyCodeVO vc){
		getSession().setAttribute(VERIFYCODE_IN_SESSION, vc);
	}
}
