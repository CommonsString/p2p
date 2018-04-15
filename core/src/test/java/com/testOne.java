package com;

public class testOne {

	
	public static void main(String[] args) {
		Double temp = Math.ceil(Math.random() * 1000000);
		String val = temp.toString();
		System.out.println(val.substring(0, 6));
		System.out.println(val);  
	}
}
