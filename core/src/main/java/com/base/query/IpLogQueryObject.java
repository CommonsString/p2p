package com.base.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import com.base.tools.DateUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * 登录日志查询对象
 * 结果对象
 *
 */

@Getter
@Setter
public class IpLogQueryObject extends QueryObject{
	
	private Date beginDate; //开始时间
	private Date endDate; //结束时间
	private int state = -1;
	private String userName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") //pattern 模式
	public void setBeginDate(Date beginDate){
		this.beginDate = beginDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd") //pattern 模式
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	
	public Date getEndDate(){
		return this.endDate == null ? null : DateUtil.endOfDate(this.endDate);
	}
	
	public String getUserName(){
		return StringUtils.hasLength(this.userName) ? this.userName : null;
	}
	
	
}
