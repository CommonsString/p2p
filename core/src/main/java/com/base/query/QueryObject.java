package com.base.query;

import lombok.Getter;
import lombok.Setter;
/**
 *
 */
@Setter
@Getter
public abstract class QueryObject {
 
	private Integer currentPage = 1;  //当前页数
	private Integer pageSize = 10; //单页数量 10
	
	public Integer getStart(){ //返回start
		return (this.currentPage - 1) * pageSize;
	}
	
	
}
