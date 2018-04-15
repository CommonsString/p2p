
package com.base.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.domain.UserInfo;
import com.base.mapper.UserInfoMapper;
import com.base.service.UserInfoService;
import com.base.service.VerifyCodeService;
import com.base.tools.StateCodeUtils;
import com.base.tools.UserContext;

/**
 * 用户服务相关信息 
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper mapper;
	
	@Autowired
	private VerifyCodeService vCodeService;
	
	
	@Override
	public void update(UserInfo info) {
		int result = mapper.updateUserInfo(info);
		if(result == 0){
			throw new RuntimeException("乐观锁失败，对象UserInfo::" + info.getUserInfoId());
		}
	}

	@Override
	public void addUserInfo(UserInfo info) {
		int result = mapper.addUserInfo(info);
		if(result == 0){
			throw new RuntimeException("添加用户信息失败");
		}
	}

	@Override
	public UserInfo getUserInfoById(String userInfoId) {
		return this.mapper.getUserInfoById(userInfoId);
	}

	@Override
	public void bindIPhoneNumber(String phoneNumber, String verifyCode) {
		//判断当前用户是否绑定手机
		UserInfo current = this.mapper.getUserInfoById(UserContext.getCurrent().getId());
		if(!current.getIsBindPhone()){ //非空才做以下操作
			//session的手机号，和当前的手机对比
System.out.println("操作：  " + "bindIPhoneNumber");
			//session的验证码与当前的相比。两者同时满足，绑定
			boolean flag = this.vCodeService.validate(phoneNumber, verifyCode); //校正验证码合法性
System.out.println("验证码合法性： " + flag);
			if(flag){
				current.addBitState(StateCodeUtils.BIND_PHONE); //如果合法，增加状态码
				current.setPhoneNumber(phoneNumber); //设置电话号码
				this.update(current); //乐观锁
			}else{
				throw new RuntimeException("绑定手机失败！");
			}
		}
	}
	
	
	

}
