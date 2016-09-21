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
	 * 获取可用Mina Servicer(Slaver)
	 * @return
	 * @throws ServiceException
	 */
	JyMinaServicer getAvailableMinaServicer() throws ServiceException;

	/**
	 * 增加session
	 * @param session
	 * @throws ServiceException
	 */
	void addMinaSession(JyMinaSession session) throws ServiceException;

	JyMinaSession getMinaSession(JyMinaSession session) throws ServiceException;
	
	void updateMinaSession(JyMinaSession session) throws ServiceException;
	
	JyAccess getAccessByAccessId(String accessId) throws ServiceException;

	JyDevices getDevices(JyDevices devices) throws ServiceException;
	
	/**
	 * 查询设备信息
	 * @param devicesId
	 * @return
	 * @throws ServiceException
	 */
	JyDevices getDevicesData(String devicesId) throws ServiceException;

	/**
	 * 添加发送的消息
	 * @param message
	 * @throws ServiceException
	 */
	void addMessage(JyMessage message) throws ServiceException;

	/**
	 * 查询发送的消息
	 * @param message
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Map<String, Object> getMessagePage(JyMessage message, Integer pageNum,
			Integer pageSize) throws ServiceException;

	/**
	 * 删除发送信息
	 * @param id
	 * @throws ServiceException
	 */
	void removeMessage(String[] id)  throws ServiceException;
	
	/**
	 * 删除发送信息
	 * @param id
	 * @throws ServiceException
	 */
	void removeMessage(String id)  throws ServiceException;

	/**
	 * 查询信息详情
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	JyMessage getMessageData(String id) throws ServiceException;

	/**
	 * 查询Access
	 * @param access
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Map<String, Object> getAccessPage(JyAccess access, Integer pageNum,
			Integer pageSize) throws ServiceException;

	/**
	 * 获取Access详情
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	JyAccess getAccessData(String id) throws ServiceException;

	/**
	 * 删除Access
	 * @param id
	 * @throws ServiceException
	 */
	void removeAccess(String[] id) throws ServiceException;

	/**
	 * 添加或修改Access
	 * @param access
	 * @throws ServiceException
	 */
	void addOrUpdateAccess(JyAccess access) throws ServiceException;

	/**
	 * 查询在线的session
	 * @param minaSession
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Map<String, Object> getMinaSessionPage(JyMinaSession minaSession,
			Integer pageNum, Integer pageSize) throws ServiceException;

	/**
	 * 获取Session详情
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	JyMinaSession getMinaSessionData(String id) throws ServiceException;

	/**
	 * 删除Session
	 * @param id
	 * @throws ServiceException
	 */
	void removeMinaSession(String[] id)  throws ServiceException;

	/**
	 * 获取服务列表
	 * @param minaServicer
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Map<String, Object> getMinaServicerPage(JyMinaServicer minaServicer, Integer pageNum, Integer pageSize)
			throws ServiceException;

	/**
	 * 获取服务详情
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	JyMinaServicer getMinaServicerData(String id) throws ServiceException;

	/**
	 * 添加或修改
	 * @param minaServicer
	 * @throws ServiceException
	 */
	void addOrUpdateMinaServicer(JyMinaServicer minaServicer) throws ServiceException;

}
