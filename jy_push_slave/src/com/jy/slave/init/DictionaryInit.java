package com.jy.slave.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jy.framework.util.GsonUtil;
import com.jy.push.dao.DictionaryDao;
import com.jy.push.entity.Dictionary;

public class DictionaryInit {
	
	private DictionaryDao dictionaryDao;
	
	private static Map<String, Object> dataMap = null;
	
	public void init() {
		
		if(dataMap == null){
			
			dataMap = new HashMap<String, Object>();
			
			List<Dictionary> listDTO = dictionaryDao.findList();
			
			if(listDTO != null && listDTO.size() > 0){
				
				for (int i = 0; i < listDTO.size(); i++) {
					Dictionary dto = listDTO.get(i);
					if(!"0".equals(dto.getDictPaterCode()) && !"".equals(dto.getDictPaterCode()) && null != dto.getDictPaterCode()){
						Object obj = dataMap.get(dto.getDictPaterCode());
						List<Dictionary> list = null;
						if(obj == null){
							list = new ArrayList<Dictionary>();
						}else{
							list = (ArrayList<Dictionary>)obj;
						}
						list.add(dto);
						dataMap.put(dto.getDictPaterCode(), list);
					}
				}
				
			}
		}
		
		System.out.println("常量被初始化>>" + GsonUtil.toJson(dataMap));
		
	}
	
	public Map<String, Object> getData() {
		
		if(dataMap == null)
			init();
		
		return dataMap;
		
	}
	
	public List<Dictionary> getDataByType(String type){
		
		Object object = dataMap.get(type);
		
		if(object != null){
			return (List<Dictionary>)object;
		}
		
		return null;
	}
	
	public String getDataByTypeCode(String type, String code){
		
		Object object = dataMap.get(type);
		
		if(object != null){
			List<Dictionary> list = (List<Dictionary>)object;
			
			if(list != null){
				for (int i = 0; i < list.size(); i++) {
					Dictionary dto = list.get(i);
					if(code.equals(dto.getDictCode())){
						return dto.getDictName();
					}
				}
			}
		}
		return null;
	}

	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}

}
