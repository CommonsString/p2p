package com.base.service;


/**
 * 验证码相关服务
 *
 */
public interface VerifyCodeService {

	void sendVerifyCodeForPhone(String phoneNumber); //给手机发送验证码

	boolean validate(String phoneNumber, String verifyCode);
	 
}
