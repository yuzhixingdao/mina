package com.jy.slave.service;

import java.util.List;

import com.jy.framework.exception.ServiceException;
import com.jy.push.entity.JyAccess;
import com.jy.push.entity.JyCommunion;
import com.jy.push.entity.JyDevices;
import com.jy.push.entity.JyMessage;
import com.jy.push.entity.JyMinaSession;

public interface SlaveService {

	/**
	 * 注册 Mina Servicer
	 * @param ip
	 * @param port
	 * @throws ServiceException
	 */
	void registerMinaServicer(String ip, Integer port) throws ServiceException;

	JyAccess getAccess(String accessKey) throws ServiceException;

	/**
	 * 删除Session
	 * @param sessionId
	 * @throws ServiceException
	 */
	void removeSession(String sessionId) throws ServiceException;

	/**
	 * 添加推送的Message
	 * @param message
	 * @throws ServiceException
	 */
	void addMessage(JyMessage message) throws ServiceException;

	/**
	 * 接受消息
	 * @param messageId
	 * @throws ServiceException
	 */
	void updateMessageReceive(String messageId) throws ServiceException;

	/**
	 * 获取要推送的消息列表
	 * @return
	 * @throws ServiceException
	 */
	List getMessageSentList() throws ServiceException;

	/**
	 * 获取设备信息
	 * @param token
	 * @return
	 * @throws ServiceException
	 */
	JyDevices getDevicesByToken(String token) throws ServiceException;

	/**
	 * 获取交互信息
	 * @param accessId
	 * @return
	 * @throws ServiceException
	 */
	JyCommunion getCommunionByAccessId(String accessId) throws ServiceException;

	/**
	 * 
	 * @param sessionId
	 * @return
	 * @throws ServiceException
	 */
	JyMinaSession getMinaSessionBySessionId(String sessionId) throws ServiceException;

	void addMinaSession(JyMinaSession minaSession) throws ServiceException;

	void updateMinaSession(JyMinaSession minaSession) throws ServiceException;

	/**
	 * 修改session下线
	 * @param pushPort
	 * @throws ServiceException
	 */
	void downlineMinaSession(Integer pushPort) throws ServiceException;

}
