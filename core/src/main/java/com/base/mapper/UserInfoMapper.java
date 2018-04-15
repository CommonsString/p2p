package com.base.mapper;

import com.base.domain.UserInfo;

public interface UserInfoMapper {
	
	int updateUserInfo(UserInfo info); //更新，用户信息
	
	int addUserInfo(UserInfo info); //添加，用户信息
	
	UserInfo getUserInfoById(String userInfoId); //根基Id，查找userInfo
	
}
