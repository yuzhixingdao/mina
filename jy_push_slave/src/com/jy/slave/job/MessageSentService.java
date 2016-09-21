package com.jy.slave.job;

import com.jy.framework.exception.ServiceException;

public interface MessageSentService {

	/**
	 * 扫描要发送的消息
	 * @throws ServiceException
	 */
	void scanMessageSent() throws ServiceException;
	
}
