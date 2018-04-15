package com.base.tools;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JSONResult {
	
	private Boolean success = true;
	private String msg;
	
	
	public JSONResult() {
	}
	
	public JSONResult(String msg) {
		this.msg = msg;
	}
	
	public JSONResult(Boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
}
