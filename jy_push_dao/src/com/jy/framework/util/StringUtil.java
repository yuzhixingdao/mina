package com.jy.framework.util;


public class StringUtil {

	public static String[] split(String str, String regex){
		
		if(str != null){
			return str.split(regex);
		}
		
		return null;
		
	}
	
}
