package com.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.base.tools.UserContext;
import com.controller.util.MustLogin;

/**
 * 实现该接口HandlerInterceptor要实现其所有的方法
 * 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//处理登录请求
		if(handler instanceof HandlerMethod){ //处理请求
			HandlerMethod method = (HandlerMethod) handler;
			MustLogin ml = method.getMethodAnnotation(MustLogin.class);
System.out.println("进入拦截器");

			if(ml != null && UserContext.getCurrent() == null){ //需要登录，且当前用户不存在，则跳转登录页面
				response.sendRedirect("/login.html"); //跳转登录界面
System.out.println("返回登录页面");
				return false; //阻止执行下去
			}
		}
		return super.preHandle(request, response, handler);
	}

}
