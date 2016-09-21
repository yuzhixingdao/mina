package com.jy.framework.util;

import java.util.List;

/**
 * @author ShenXiaoqiang
 * @date 2014-8-25
 *
 */
public class ConvertUtil {

	public static Object[] list2array(List<Object> list){
		if (list != null && list.size() > 0) {
			Object[] obj = new Object[list.size()];
			for(int i=0; i<list.size(); i++){
				obj[i] = list.get(i);
			}
			return obj;
		}
		return null;
	}
	
}
