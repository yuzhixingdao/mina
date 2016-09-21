package com.jy.slave.service.impl;

import java.util.Date;
import java.util.List;

import com.jy.framework.exception.ServiceException;
import com.jy.push.dao.JyAccessDao;
import com.jy.push.dao.JyDevicesDao;
import com.jy.push.dao.JyMessageDao;
import com.jy.push.dao.JyMinaServicerDao;
import com.jy.push.dao.JyMinaSessionDao;
import com.jy.push.dict.DictStatus;
import com.jy.push.entity.JyAccess;
import com.jy.push.entity.JyCommunion;
import com.jy.push.entity.JyDevices;
import com.jy.push.entity.JyMessage;
import com.jy.push.entity.JyMinaServicer;
import com.jy.push.entity.JyMinaSession;
import com.jy.slave.service.ConfigService;
import com.jy.slave.service.SlaveService;

public class SlaveServiceImpl implements SlaveService {

	private JyMinaServicerDao jyMinaServicerDao;
	private JyAccessDao jyAccessDao;
	private JyMinaSessionDao jyMinaSessionDao;
	private JyMessageDao jyMessageDao;
	private JyDevicesDao jyDevicesDao;
	
	private ConfigService configService;

	public void setJyDevicesDao(JyDevicesDao jyDevicesDao) {
		this.jyDevicesDao = jyDevicesDao;
	}
	public void setJyMinaServicerDao(JyMinaServicerDao jyMinaServicerDao) {
		this.jyMinaServicerDao = jyMinaServicerDao;
	}
	public void setJyAccessDao(JyAccessDao jyAccessDao) {
		this.jyAccessDao = jyAccessDao;
	}
	public void setJyMinaSessionDao(JyMinaSessionDao jyMinaSessionDao) {
		this.jyMinaSessionDao = jyMinaSessionDao;
	}
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}
	public void setJyMessageDao(JyMessageDao jyMessageDao) {
		this.jyMessageDao = jyMessageDao;
	}

	public void registerMinaServicer(String ip, Integer port)
			throws ServiceException {
		
		JyMinaServicer ms = jyMinaServicerDao.getMinaServicer(ip, port);
		if(ms != null){
			
			ms.setIp(ip);
			ms.setPort(port);
			ms.setSessionNumber(0);
			ms.setState(DictStatus.MINA_SERVICER_UPLINE);
			ms.setIfcUrl(configService.getIfcUrl());
			ms.setUpdateDate(new Date());
			ms.setDelFlag("0");
			
			jyMinaServicerDao.update(ms);
			
		}else{
			
			ms = new JyMinaServicer();
			ms.setIp(ip);
			ms.setPort(port);
			ms.setSessionNumber(0);
			ms.setState(DictStatus.MINA_SERVICER_UPLINE);
			ms.setIfcUrl(configService.getIfcUrl());
			ms.setCreateDate(new Date());
			ms.setDelFlag("0");
			
			jyMinaServicerDao.save(ms);
			
		}
		
	}

	public JyAccess getAccess(String accessKey) throws ServiceException {
		if(accessKey != null && !accessKey.trim().equals("")){
			return jyAccessDao.getAccessByAccessKey(accessKey);
		}
		return null;
	}
	public void removeSession(String sessionId) throws ServiceException {
		jyMinaSessionDao.delete("delete from JyMinaSession where sessionId = ? ", sessionId);
	}
	
	@Override
	public void addMessage(JyMessage message) throws ServiceException {
		if(message != null){
			message.setCreateDate(new Date());
			message.setDelFlag("0");
			jyMessageDao.save(message);
		}
	}
	@Override
	public void updateMessageReceive(String messageId) throws ServiceException {
		if(messageId != null){
			JyMessage message = jyMessageDao.get(messageId);
			if(message != null){
				message.setSuccessFlag("1");
				message.setReceiveDate(new Date());
				message.setUpdateDate(new Date());
				jyMessageDao.update(message);
			}
		}
	}
	
	@Override
	public List getMessageSentList() throws ServiceException {
		String hql = "from JyMessage where (successFlag != ? OR successFlag is null) and delFlag = ?";
		List<Object> list = jyMessageDao.findList(hql, "1", "0");
		return jyMessageDao.findList(hql, "1", "0");
	}
	
	@Override
	public JyDevices getDevicesByToken(String token) throws ServiceException {
		
		Object object = jyDevicesDao.findEntity("from JyDevices where token = ?", token);
		
		if(object != null){
			return (JyDevices) object;
		}
		
		return null;
	}
	
	@Override
	public JyCommunion getCommunionByAccessId(String accessId) throws ServiceException{
		
		Object object = jyDevicesDao.findEntity("from JyCommunion where accessId = ?", accessId);
		
		if(object != null){
			return (JyCommunion) object;
		}
		
		return null;
		
	}
	
	@Override
	public JyMinaSession getMinaSessionBySessionId(String sessionId) throws ServiceException {
		
		Object object = jyMinaSessionDao.findEntity("from JyMinaSession where sessionId = ?", sessionId);
		
		if(object != null){
			return (JyMinaSession) object;
		}
		
		return null;
		
	}
	
	@Override
	public void addMinaSession(JyMinaSession minaSession) throws ServiceException {
		
		if (minaSession != null) {
			
			jyMinaSessionDao.save(minaSession);
			
		}
	}
	
	@Override
	public void updateMinaSession(JyMinaSession minaSession) throws ServiceException {
		
		if (minaSession != null) {
			
			jyMinaSessionDao.update(minaSession);
			
		}

	}
	
	@Override
	public void downlineMinaSession(Integer pushPort) throws ServiceException {
		
		if (pushPort != null) {
			jyMinaSessionDao.update("update from JyMinaSession set sessionState = ?  where locaPort = ? ", new Object[]{DictStatus.MINA_SESSION_UNAVAILABLE, pushPort});
		}
		
	}
	
}
