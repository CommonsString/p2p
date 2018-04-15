package com.base.service;

import com.base.domain.IpLog;
import com.base.query.IpLogQueryObject;
import com.base.query.PageResult;

public interface IpLogService {
	
	void addIpLog(IpLog log);  //添加登录日志
	
	PageResult query(IpLogQueryObject log); //分页查询
	
}
