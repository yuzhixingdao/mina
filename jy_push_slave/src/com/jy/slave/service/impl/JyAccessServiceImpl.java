package com.jy.slave.service.impl;

import com.jy.framework.exception.ServiceException;
import com.jy.push.dao.JyAccessDao;
import com.jy.push.entity.JyAccess;
import com.jy.slave.service.JyAccessService;

public class JyAccessServiceImpl implements JyAccessService{
	
	private JyAccessDao jyAccessDao;

	public JyAccess getAccess(String accessKey) throws ServiceException {
		if(accessKey != null && !accessKey.trim().equals("")){
			return jyAccessDao.getAccessByAccessKey(accessKey);
		}
		return null;
	}

	public void setJyAccessDao(JyAccessDao jyAccessDao) {
		this.jyAccessDao = jyAccessDao;
	}

}
