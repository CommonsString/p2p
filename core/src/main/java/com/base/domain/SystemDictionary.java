package com.base.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SystemDictionary {
	
	private String SystemDictionaryId; //id
	
	private String sn; //数据字典分类编码
	private String title; //数据字典分类名称
	private String intro; //数据字典分类使用说明
}
