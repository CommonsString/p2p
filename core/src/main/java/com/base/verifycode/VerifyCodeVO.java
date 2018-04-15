package com.base.verifycode;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 存放手机验证码相关内容
 *
 */
@Getter
@Setter
@ToString
public class VerifyCodeVO {
	
	private String verifyCode; //验证码
	private String phoneNumber; //发送验证码的手机号
	private Date lastSendTime; //最近一次发送成功的时间
	
	public VerifyCodeVO() {
	}
	
	public VerifyCodeVO(String verifyCode, String phoneNumber, Date lastSendTime) {
		this.verifyCode = verifyCode;
		this.phoneNumber = phoneNumber;
		this.lastSendTime = lastSendTime;
	}
	
	
}
