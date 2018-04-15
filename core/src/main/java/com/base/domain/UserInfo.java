package com.base.domain;

import com.base.tools.StateCodeUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserInfo {
	
	private String userInfoId;
	private int version; //版本号
	private long bitState = 0; //用户状态码
	private String realName; //真实姓名
	private String idNumber; //身份证号
	private String phoneNumber; //电话号码
	
	private SystemDictionaryItem incomeGrade;
	private SystemDictionaryItem marriage;
	private SystemDictionaryItem kidCount;
	private SystemDictionaryItem educationBackground;
	private SystemDictionaryItem houseCondition;
	
	
	//添加一个状态码
	public void addBitState(long state){
		this.setBitState(StateCodeUtils.addState(this.bitState, state));
	}
	
	public boolean getIsBindPhone(){
		return StateCodeUtils.hasState(this.bitState, StateCodeUtils.BIND_PHONE);
	}
	
}
