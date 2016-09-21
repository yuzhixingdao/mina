package com.jy.slave.service;

import com.jy.framework.exception.ServiceException;
import com.jy.push.entity.JyAccess;

public interface JyAccessService {

	JyAccess getAccess(String accessKey) throws ServiceException;
	
}
