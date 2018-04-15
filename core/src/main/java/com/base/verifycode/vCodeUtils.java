package com.base.verifycode;

public class vCodeUtils {
	
	/**
	 * 返回六位验证码 
	 */
	public static String getRandomNum(){
		Double temp = Math.ceil(Math.random() * 1000000);
		String val = temp.toString().substring(0, 6);
		return val;
	}
	

}
