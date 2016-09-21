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
	 * ע�� Mina Servicer
	 * @param ip
	 * @param port
	 * @throws ServiceException
	 */
	void registerMinaServicer(String ip, Integer port) throws ServiceException;

	JyAccess getAccess(String accessKey) throws ServiceException;

	/**
	 * ɾ��Session
	 * @param sessionId
	 * @throws ServiceException
	 */
	void removeSession(String sessionId) throws ServiceException;

	/**
	 * ������͵�Message
	 * @param message
	 * @throws ServiceException
	 */
	void addMessage(JyMessage message) throws ServiceException;

	/**
	 * ������Ϣ
	 * @param messageId
	 * @throws ServiceException
	 */
	void updateMessageReceive(String messageId) throws ServiceException;

	/**
	 * ��ȡҪ���͵���Ϣ�б�
	 * @return
	 * @throws ServiceException
	 */
	List getMessageSentList() throws ServiceException;

	/**
	 * ��ȡ�豸��Ϣ
	 * @param token
	 * @return
	 * @throws ServiceException
	 */
	JyDevices getDevicesByToken(String token) throws ServiceException;

	/**
	 * ��ȡ������Ϣ
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
	 * �޸�session����
	 * @param pushPort
	 * @throws ServiceException
	 */
	void downlineMinaSession(Integer pushPort) throws ServiceException;

}
