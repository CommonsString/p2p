package com.base.tools;

import java.math.BigDecimal;

/**
 * 系统中的常量
 */
public class BidConst {
	/**
	 * 定义存储精度
	 */
	public static final int STORE_SCALE = 4;
	/**
	 * 定义运算精度
	 */
	public static final int CAL_SCALE = 8; //运算精度是存储精度的两倍
	/**
	 * 定于显示精度
	 */
	public static final int DISPLAY_SCALE = 2;
	/**
	 * 定义系统级别的0
	 */
	public static final BigDecimal ZERO = new BigDecimal("0.0000");
	/**
	 * 定义初始授信额度
	 */
	public static final BigDecimal BORROW_LIMIT = new BigDecimal("5000.0000");
	
	
	/**
	 * 默认管理员账户
	 */
	public static final String DEFAULT_ACCOUNT = "admin";
	
	/**
	 * 默认管理员密码
	 */
	public static final String DEFAULT_PASSWORD = "wanmcdmmaa";
	
	/**
	 * 手机验证码有效期
	 */
	public static final int VEIFITY_CODE_LIMIT_TIME = 300; //秒  5分钟
	
	
;	
}
