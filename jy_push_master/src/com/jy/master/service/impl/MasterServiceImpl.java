package com.jy.master.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jy.framework.dao.util.DataUtil;
import com.jy.framework.dao.util.ParameterMap;
import com.jy.framework.dict.PageStatus;
import com.jy.framework.exception.ServiceException;
import com.jy.framework.util.BeanUtil;
import com.jy.framework.util.UUIDUtil;
import com.jy.master.service.IfcService;
import com.jy.master.service.MasterService;
import com.jy.push.dao.JyAccessDao;
import com.jy.push.dao.JyDevicesDao;
import com.jy.push.dao.JyMessageDao;
import com.jy.push.dao.JyMinaServicerDao;
import com.jy.push.dao.JyMinaSessionDao;
import com.jy.push.dict.DictStatus;
import com.jy.push.entity.JyAccess;
import com.jy.push.entity.JyDevices;
import com.jy.push.entity.JyMessage;
import com.jy.push.entity.JyMinaServicer;
import com.jy.push.entity.JyMinaSession;

public class MasterServiceImpl implements MasterService {

	private IfcService ifcService;
	
	private JyAccessDao jyAccessDao;
	private JyDevicesDao jyDevicesDao;
	private JyMinaServicerDao jyMinaServicerDao;
	private JyMinaSessionDao jyMinaSessionDao;
	private JyMessageDao jyMessageDao;
	
	
	public void setIfcService(IfcService ifcService) {
		this.ifcService = ifcService;
	}
	public void setJyAccessDao(JyAccessDao jyAccessDao) {
		this.jyAccessDao = jyAccessDao;
	}
	public void setJyDevicesDao(JyDevicesDao jyDevicesDao) {
		this.jyDevicesDao = jyDevicesDao;
	}
	public void setJyMinaServicerDao(JyMinaServicerDao jyMinaServicerDao) {
		this.jyMinaServicerDao = jyMinaServicerDao;
	}
	public void setJyMinaSessionDao(JyMinaSessionDao jyMinaSessionDao) {
		this.jyMinaSessionDao = jyMinaSessionDao;
	}
	public void setJyMessageDao(JyMessageDao jyMessageDao) {
		this.jyMessageDao = jyMessageDao;
	}
	
	
	public JyAccess getAccessByAccessKey(String accessKey) throws ServiceException {
		return jyAccessDao.getAccessByAccessKey(accessKey);
	}
	
	public void addDevices(JyDevices devices) throws ServiceException {
		jyDevicesDao.save(devices);
	}
	
	public JyMinaServicer getMinaServicer(String ip, Integer port)
		throws ServiceException {
		return jyMinaServicerDao.getMinaServicer(ip, port);
	}
	
	public void addMinaServicer(JyMinaServicer minaServicer)
		throws ServiceException {
		jyMinaServicerDao.save(minaServicer);
	}
	
	public void updateMinaServicer(JyMinaServicer minaServicer)
		throws ServiceException {
		jyMinaServicerDao.update(minaServicer);
	}

	public JyDevices getDevicesByAccessId(String accessId)
			throws ServiceException {
		return jyDevicesDao.getDevicesByAccessId(accessId);
	}
	
	public void updateDevices(JyDevices devices) throws ServiceException{
		jyDevicesDao.update(devices);
	}

	public JyMinaServicer getAvailableMinaServicer() throws ServiceException {
		String hql = "from JyMinaServicer where state = ? and delFlag = ? order by sessionNumber";
		Object object = jyMinaServicerDao.findEntity(hql, DictStatus.MINA_SERVICER_UPLINE, "0");
		if(object != null){
			return (JyMinaServicer) object;
		}
		return null;
	}
	
	public void addMinaSession(JyMinaSession session) throws ServiceException {
		jyMinaSessionDao.save(session);
	}
	public JyMinaSession getMinaSession(JyMinaSession session) throws ServiceException{
		return jyMinaSessionDao.getMinaSession(session);
	}
	public void updateMinaSession(JyMinaSession session) throws ServiceException{
		jyMinaSessionDao.update(session);
	}
	public JyAccess getAccessByAccessId(String accessId) throws ServiceException {
		return jyAccessDao.getAccessByAccessId(accessId);
	}
	public JyDevices getDevices(JyDevices devices) throws ServiceException {
		return jyDevicesDao.getDevices(devices);
	}
	public JyDevices getDevicesData(String id) throws ServiceException{
		if(id != null){
			return jyDevicesDao.get(id);
		}
		return null;
	}
	public void addMessage(JyMessage message) throws ServiceException {
		if(message != null){
			message.setCreateDate(new Date());
			message.setDelFlag("0");
			jyMessageDao.save(message);
		}
	}
	
	public Map<String, Object> getMessagePage(JyMessage message, Integer pageNum,
			Integer pageSize) throws ServiceException{
		
		List<Object> params = new ArrayList<Object>();
		List<ParameterMap> clazzs = new ArrayList<ParameterMap>();
		StringBuffer sql = new StringBuffer("SELECT {MESSAGE.*}, {DEVICES.*} FROM JY_MESSAGE MESSAGE INNER JOIN JY_DEVICES DEVICES ON MESSAGE.DEVICES_ID = DEVICES.ID WHERE 1=1 ");
		
		clazzs.add(new ParameterMap("MESSAGE", JyMessage.class));
		clazzs.add(new ParameterMap("DEVICES", JyDevices.class));
		
		if(message != null){
			if(message.getToken() != null && !message.getToken().equals("")){
				sql.append(" AND MESSAGE.TOKEN LIKE ? ");
				params.add("%" + message.getToken() + "%");
			}
			if(message.getMessageContent() != null && !message.getMessageContent().equals("")){
				sql.append(" AND MESSAGE.MESSAGE_CONTENT LIKE ? ");
				params.add("%" + message.getMessageContent() + "%");
			}
		}
		
		Map<String, Object> map = jyMessageDao.findPageBySql(sql.toString(), params, clazzs, pageNum, pageSize);
		
		DataUtil.convert(map, new String[]{"message", "devices"});
		
		return map;
		
	}
	
	@Override
	public void removeMessage(String[] id) throws ServiceException {
		if(id != null && id.length > 0){
			for(int i=0; i<id.length; i++){
				jyMessageDao.deleteMessage(id[i]);
			}
		}
	}
	@Override
	public void removeMessage(String id) throws ServiceException {
		if(id != null){
			jyMessageDao.deleteMessage(id);
		}
	}
	@Override
	public JyMessage getMessageData(String id) throws ServiceException {
		if(id != null){
			return jyMessageDao.get(id);
		}
		return null;
	}
	
	@Override
	public Map<String, Object> getAccessPage(JyAccess access, Integer pageNum,
			Integer pageSize) throws ServiceException {
		return jyAccessDao.getAccessPage(access, pageNum, pageSize);
	}
	@Override
	public JyAccess getAccessData(String id) throws ServiceException {
		if(id != null){
			return jyAccessDao.get(id);
		}
		return null;
	}
	@Override
	public void removeAccess(String[] id) throws ServiceException {
		if(id != null){
			for (int i = 0; i < id.length; i++) {
				if(id[i] != null && !id[i].equals(""))
					jyAccessDao.update("update JyAccess set delFlag = ? where id = ?", "1", id[i]);
			}
		}
	}
	@Override
	public void addOrUpdateAccess(JyAccess access) throws ServiceException {
		
		if(access != null){
			if(access.getId() != null && !access.getId().equals("")){
				
				JyAccess ja = jyAccessDao.get(access.getId());
				BeanUtil.copyProperties(access, ja, BeanUtil.getNullPropertyNames(access));
				ja.setUpdateDate(new Date());
				jyAccessDao.update(ja);
				
			}else{
				access.setId(null);
				access.setAccessId(UUIDUtil.getUuid());
				access.setAccessKey(UUIDUtil.getUuid());
				access.setSecretKey(UUIDUtil.getUuid());
				access.setDelFlag("0");
				access.setCreateDate(new Date());
				jyAccessDao.save(access);
			}
		}
		
	}
	
	@Override
	public void addOrUpdateMinaServicer(JyMinaServicer minaServicer) throws ServiceException {
		if(minaServicer != null){
			if(minaServicer.getId() != null && !minaServicer.getId().equals("")){
				
				JyMinaServicer ja = jyMinaServicerDao.get(minaServicer.getId());
				BeanUtil.copyProperties(minaServicer, ja, BeanUtil.getNullPropertyNames(minaServicer));
				ja.setUpdateDate(new Date());
				jyMinaServicerDao.update(ja);
				
			}else{
				minaServicer.setId(null);
				minaServicer.setDelFlag("0");
				minaServicer.setCreateDate(new Date());
				jyMinaServicerDao.save(minaServicer);
			}
		}
	}
	
	@Override
	public Map<String, Object> getMinaSessionPage(JyMinaSession minaSession,
			Integer pageNum, Integer pageSize) throws ServiceException {
		
		List<Object> params = new ArrayList<Object>();
		List<ParameterMap> clazzs = new ArrayList<ParameterMap>();
		StringBuffer sql = new StringBuffer("SELECT {SESSION.*}, {DEVICES.*} FROM JY_MINA_SESSION SESSION INNER JOIN JY_DEVICES DEVICES ON SESSION.DEVICES_ID = DEVICES.ID WHERE 1=1 ");
		
		clazzs.add(new ParameterMap("SESSION", JyMinaSession.class));
		clazzs.add(new ParameterMap("DEVICES", JyDevices.class));
		
		if(minaSession != null){
			if(minaSession.getToken() != null && !minaSession.getToken().equals("")){
				sql.append(" AND SESSION.TOKEN LIKE ? ");
				params.add("%" + minaSession.getToken() + "%");
			}
		}
		
		sql.append(" ORDER BY SESSION.SESSION_STATE DESC, SESSION.CONNECT_DATE DESC ");
		
		Map<String, Object> map = jyMinaSessionDao.findPageBySql(sql.toString(), params, clazzs, pageNum, pageSize);
		
		DataUtil.convert(map, new String[]{"session", "devices"});
		
		//ifcService.isSession((List<Map<String, Object>>)map.get(PageStatus.LIST));
		
		return map;
		
	}
	
	@Override
	public Map<String, Object> getMinaServicerPage(JyMinaServicer minaServicer, Integer pageNum, Integer pageSize) throws ServiceException {
	
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("from JyMinaServicer");
		
		return jyMinaServicerDao.findPage(hql.toString(), params, pageNum, pageSize);
		
	}
	
	@Override
	public JyMinaSession getMinaSessionData(String id) throws ServiceException {
		if(id != null && !id.equals("")){
			return jyMinaSessionDao.get(id);
		}
		return null;
	}
	
	@Override
	public JyMinaServicer getMinaServicerData(String id) throws ServiceException {
		if(id != null && !id.equals("")){
			return jyMinaServicerDao.get(id);
		}
		return null;
	}
	
	@Override
	public void removeMinaSession(String[] id) throws ServiceException {
		if(id != null){
			for (int i = 0; i < id.length; i++) {
				if(id[i] != null && !id[i].equals(""))
					jyMinaSessionDao.delete("delete from JyMinaSession where id = ?", id[i]);
			}
		}
	}

}
