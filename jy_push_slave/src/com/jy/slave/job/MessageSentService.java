package com.jy.slave.job;

import com.jy.framework.exception.ServiceException;

public interface MessageSentService {

	/**
	 * ɨ��Ҫ���͵���Ϣ
	 * @throws ServiceException
	 */
	void scanMessageSent() throws ServiceException;
	
}
