package com.base.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.domain.IpLog;
import com.base.mapper.IpLogMapper;
import com.base.query.IpLogQueryObject;
import com.base.query.PageResult;
import com.base.service.IpLogService;


@Service
public class IpLogServiceImpl implements IpLogService {
	
	@Autowired
	private IpLogMapper mapper;

	@Override
	public void addIpLog(IpLog log) {
		int result = this.mapper.addIpLog(log);
		if(result <= 0){
			throw new RuntimeException("异常：IpLog");
		}
	}

	@Override
	public PageResult query(IpLogQueryObject log) {
		int count = this.mapper.getIpLogForCount(log);
		if(count > 0){
			List<IpLog> list = this.mapper.getIpLogForList(log); 
			return new PageResult(count, log.getPageSize(), log.getCurrentPage(), list); //在类中已经初始化
		}
		return PageResult.empty(log.getPageSize());
	}
	

}
