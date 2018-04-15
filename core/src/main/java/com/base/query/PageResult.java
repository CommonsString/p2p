package com.base.query;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class PageResult {
	
	private Integer totalCount;
	private Integer pageSize = 10; //在类中初始化
	private Integer currentPage = 1;
	private List result;
	
	public PageResult() {
	}

	public PageResult(Integer totalCount, Integer pageSize, Integer currentPage, List result) {
		super();
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.result = result;                             
	}

	public static PageResult empty(int pageSize){ //返回一个空的分页对象
		return new PageResult(0, pageSize, 1, new ArrayList());
	}

	public Integer getTotalPage() { //获取总页数  
		//this.pageSize - 1 就是 totalCount / pageSize的最大余数
		return Math.max((this.totalCount + this.pageSize - 1) / this.pageSize, 1);
		//第二种算法 ：
		//return totalCount / pageSize == 0 ? totalCount/pageSize : totalCount/pageSize + 1
	}   
	
	public Integer getPrew(){  //返回前一页
 		return Math.max(this.currentPage - 1, 1);
	}
	
	public Integer getNext(){ //获取下一页
		return Math.max(this.currentPage + 1, this.getTotalPage());
	}
	
	public Integer getTotalCount(){
		return this.totalCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public List getResult() {
		return result;
	}
}
