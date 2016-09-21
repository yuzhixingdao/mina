package com.jy.framework.util;

import java.util.UUID;

public class UUIDUtil {

	public static String getUuid(){
		UUID uuid = UUID.randomUUID();
		String retUuid = uuid.toString().replace("-", "");
		return retUuid;
	}
	
	public static void main(String[] args) {
		System.out.println(getUuid());
	}
	
}
