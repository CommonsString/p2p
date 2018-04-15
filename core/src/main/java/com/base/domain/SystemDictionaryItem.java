package com.base.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SystemDictionaryItem{
	
	private String SystemDictionaryItemId; //id
	
	private long parentId; //长整型
	private String title;
	private Integer sequence;

}
