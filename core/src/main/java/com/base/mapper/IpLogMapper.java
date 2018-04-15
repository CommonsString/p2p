package com.base.mapper;

import java.util.List;

import com.base.domain.IpLog;
import com.base.query.IpLogQueryObject;

public interface IpLogMapper {
	
	int addIpLog(IpLog log);  //添加登录日志

	int getIpLogForCount(IpLogQueryObject log); //分页查询总数
 
	List<IpLog> getIpLogForList(IpLogQueryObject log); //查询集合
	
}
