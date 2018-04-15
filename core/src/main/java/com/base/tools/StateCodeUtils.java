package com.base.tools;

/**
 * 状态码工具 
 */
public class StateCodeUtils {

	public static final long BIND_PHONE = 1L; //long的64位  手机状态码
	public static final long BIND_EMAIL = 2L << 0;  //二进制左移0位
	
	
	/**
	 * @param currentState 当前状态码
	 * @param value 添加的状态码
	 * @return 判断当前状态码是否是该状态
	 */
	public static boolean hasState(long currentState, long value){
		return (currentState & value) != 0;  //两个同时存在，为1
	}
	
	public static long addState(long currentState, long value){
		if(!hasState(currentState, value)){
			return currentState; //返回当前状态码
		}
		return currentState | value; //或运算
	}
	
	public static long removeState(long currentState, long value){
		if(!hasState(currentState, value)){
			return currentState; //返回当前状态码
		}
		return currentState ^ value; //异或运算
	}
	
	
}
