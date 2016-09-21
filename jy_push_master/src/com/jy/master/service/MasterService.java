package com.jy.master.service;

import java.util.Map;

import com.jy.framework.exception.ServiceException;
import com.jy.push.entity.JyAccess;
import com.jy.push.entity.JyDevices;
import com.jy.push.entity.JyMessage;
import com.jy.push.entity.JyMinaServicer;
import com.jy.push.entity.JyMinaSession;

public interface MasterService {

	JyAccess getAccessByAccessKey(String accessKey) throws ServiceException;
	
	void addDevices(JyDevices devices) throws ServiceException;

	JyDevices getDevicesByAccessId(String accessId) throws ServiceException;
	
	void updateDevices(JyDevices devices) throws ServiceException;
	
	JyMinaServicer getMinaServicer(String ip, Integer port) throws ServiceException;

	void addMinaServicer(JyMinaServicer minaServicer) throws ServiceException;

	void updateMinaServicer(JyMinaServicer minaServicer) throws ServiceException;

	/**
	 * ��ȡ����Mina Servicer(Slaver)
	 * @return
	 * @throws ServiceException
	 */
	JyMinaServicer getAvailableMinaServicer() throws ServiceException;

	/**
	 * ����session
	 * @param session
	 * @throws ServiceException
	 */
	void addMinaSession(JyMinaSession session) throws ServiceException;

	JyMinaSession getMinaSession(JyMinaSession session) throws ServiceException;
	
	void updateMinaSession(JyMinaSession session) throws ServiceException;
	
	JyAccess getAccessByAccessId(String accessId) throws ServiceException;

	JyDevices getDevices(JyDevices devices) throws ServiceException;
	
	/**
	 * ��ѯ�豸��Ϣ
	 * @param devicesId
	 * @return
	 * @throws ServiceException
	 */
	JyDevices getDevicesData(String devicesId) throws ServiceException;

	/**
	 * ��ӷ��͵���Ϣ
	 * @param message
	 * @throws ServiceException
	 */
	void addMessage(JyMessage message) throws ServiceException;

	/**
	 * ��ѯ���͵���Ϣ
	 * @param message
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Map<String, Object> getMessagePage(JyMessage message, Integer pageNum,
			Integer pageSize) throws ServiceException;

	/**
	 * ɾ��������Ϣ
	 * @param id
	 * @throws ServiceException
	 */
	void removeMessage(String[] id)  throws ServiceException;
	
	/**
	 * ɾ��������Ϣ
	 * @param id
	 * @throws ServiceException
	 */
	void removeMessage(String id)  throws ServiceException;

	/**
	 * ��ѯ��Ϣ����
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	JyMessage getMessageData(String id) throws ServiceException;

	/**
	 * ��ѯAccess
	 * @param access
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Map<String, Object> getAccessPage(JyAccess access, Integer pageNum,
			Integer pageSize) throws ServiceException;

	/**
	 * ��ȡAccess����
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	JyAccess getAccessData(String id) throws ServiceException;

	/**
	 * ɾ��Access
	 * @param id
	 * @throws ServiceException
	 */
	void removeAccess(String[] id) throws ServiceException;

	/**
	 * ��ӻ��޸�Access
	 * @param access
	 * @throws ServiceException
	 */
	void addOrUpdateAccess(JyAccess access) throws ServiceException;

	/**
	 * ��ѯ���ߵ�session
	 * @param minaSession
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Map<String, Object> getMinaSessionPage(JyMinaSession minaSession,
			Integer pageNum, Integer pageSize) throws ServiceException;

	/**
	 * ��ȡSession����
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	JyMinaSession getMinaSessionData(String id) throws ServiceException;

	/**
	 * ɾ��Session
	 * @param id
	 * @throws ServiceException
	 */
	void removeMinaSession(String[] id)  throws ServiceException;

	/**
	 * ��ȡ�����б�
	 * @param minaServicer
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Map<String, Object> getMinaServicerPage(JyMinaServicer minaServicer, Integer pageNum, Integer pageSize)
			throws ServiceException;

	/**
	 * ��ȡ��������
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	JyMinaServicer getMinaServicerData(String id) throws ServiceException;

	/**
	 * ��ӻ��޸�
	 * @param minaServicer
	 * @throws ServiceException
	 */
	void addOrUpdateMinaServicer(JyMinaServicer minaServicer) throws ServiceException;

}
