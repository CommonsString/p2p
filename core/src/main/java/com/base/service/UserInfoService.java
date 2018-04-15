package com.base.service;

import com.base.domain.UserInfo;

public interface UserInfoService {
	
	void update(UserInfo info); //更新，更新账户
	
	void addUserInfo(UserInfo info); //添加
	
	UserInfo getUserInfoById(String userInfoId);

	void bindIPhoneNumber(String phoneNumber, String verifyCode); //绑定手机号

}
