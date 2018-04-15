package com.base.service;



/**
 * 登录相关服务
 * 接口默认是public abstract
 */
import com.base.domain.LoginInfo;

public interface LoginInfoService {
	
	LoginInfo getLoginInfo(String id);  //测试
	
	void registerUser(String userName, String password); //注册
	
	int addUser(LoginInfo info); //添加用户信息
	
	int getUserIsCopy(String userName); //查询用户名是否存在
	
	LoginInfo loginUserInfo(String username, String password, String ip, int userType); //用户登录

	void initAdmin();
}
