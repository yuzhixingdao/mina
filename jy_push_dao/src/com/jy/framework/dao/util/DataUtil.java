package com.jy.framework.dao.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jy.framework.dict.PageStatus;

public class DataUtil {
	
	public static Map<String, Object> convert(Map<String, Object> map, String[] key){
		
		if(map != null && map.get(PageStatus.LIST) != null){
			
			List list = (List) map.get(PageStatus.LIST);
			List retList = new ArrayList();
			
			for(int i=0; i<list.size(); i++){
				Object[] obj = (Object[]) list.get(i);
				
				Map<String, Object> retMap = new HashMap<String, Object>();
				for (int j = 0; j < key.length; j++) {
					retMap.put(key[j], obj[j]);
				}
				
				retList.add(retMap);
			}
			map.put(PageStatus.LIST, retList);
		}
		
		return map;
	}

}
