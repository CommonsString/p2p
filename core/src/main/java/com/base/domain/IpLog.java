package com.base.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录日志 
 */
@Getter
@Setter
@ToString
public class IpLog {
	
	public static final int STATE_SUCESS = 1; //登录成功 
	public static final int STATE_FAULT = 0; //登录失败
	
	private String IpLogId;
	private String ipPath; //up地址
	private Date loginTime; //登录时间
	private String userName; //用户名
	private int state; //登录结果 成功/失败
	private int userType; //用户登录类型
	
	
	public String getStateDisplay(){
		return state == STATE_SUCESS ? "登录成功" : "登录失败";
	}
	
	public String getUserTypeDisplay(){ 
		return userType == LoginInfo.USER_CLIENT ? "前端用户" : "后台管理员";
	}
	
}
