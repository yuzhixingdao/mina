package com.jy.slave.service;

import com.jy.framework.exception.ServiceException;

public interface IfcService {

	/**
	 * ����Session
	 * @param token
	 * @throws ServiceException
	 */
	void downlineSession(String token) throws ServiceException;

}
