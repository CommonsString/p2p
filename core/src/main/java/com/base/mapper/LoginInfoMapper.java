package com.base.mapper;

import org.apache.ibatis.annotations.Param;

import com.base.domain.LoginInfo;

public interface LoginInfoMapper {
	
	public LoginInfo getLoginInfo(String id);
	
	int addUser(LoginInfo info); //添加用户信息
	
	int getUserIsCopy(String userName); //查询用户名是否存在
	
	LoginInfo loginUserInfo(@Param("userName") String username, 
			@Param("password") String password, @Param("userType") int userType);

	public int getAdminForCount(int userType);
	
}
