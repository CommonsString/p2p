package com.base.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 用户登录信息
 * @author MyPC
 *my
 */
@Setter
@Getter
@ToString
public class LoginInfo{
	
	public static final int STATE_NORMAL = 0; //正常
	public static final int STATE_LOCK = 1;  //锁定
	public static final int USER_MANAGER = 0; //后台用户
	public static final int USER_CLIENT = 1; //前台用户
	
	
	
	private String id;
	private String userName;
	private String password;
	private int state;
	private int userType;  //前台还是后台用户信息，用户类型
	
}
