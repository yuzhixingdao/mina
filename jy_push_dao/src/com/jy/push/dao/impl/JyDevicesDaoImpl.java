package com.jy.push.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.jy.framework.dao.impl.BaseHibernateDaoImpl;
import com.jy.framework.exception.DAOException;
import com.jy.push.dao.JyDevicesDao;
import com.jy.push.entity.JyDevices;

public class JyDevicesDaoImpl extends BaseHibernateDaoImpl<JyDevices> implements
		JyDevicesDao {

	public JyDevices getDevicesByAccessId(String accessId) throws DAOException {
		if(accessId != null && !accessId.trim().equals("")){
			Object entity = super.findEntity("from JyDevices where accessId = ? and delFlag = ?", accessId, "0");
			if(entity != null){
				return (JyDevices) entity;
			}
		}
		return null;
	}
	
	public JyDevices getDevices(JyDevices devices) throws DAOException {
		
		if(devices != null){
			
			List<Object> params = new ArrayList<Object>();
			StringBuffer hql = new StringBuffer();
			
			hql.append("from JyDevices where delFlag = ? ");
			params.add("0");
			
			if(devices.getAccessId() != null && !devices.getAccessId().trim().equals("")){
				hql.append(" and accessId = ? ");
				params.add(devices.getAccessId().trim());
			}
			if(devices.getToken() != null && !devices.getToken().trim().equals("")){
				hql.append(" and token = ? ");
				params.add(devices.getToken().trim());
			}
			if(devices.getDeviceNo() != null && !devices.getDeviceNo().trim().equals("")){
				hql.append(" and deviceNo = ? ");
				params.add(devices.getDeviceNo().trim());
			}
			if(devices.getUserId() != null && !devices.getUserId().trim().equals("")){
				hql.append(" and userId = ? ");
				params.add(devices.getUserId().trim());
			}
			if(devices.getOther() != null && !devices.getOther().trim().equals("")){
				hql.append(" and other = ? ");
				params.add(devices.getOther().trim());
			}
			
			Object entity = super.findEntity(hql.toString(), params);
			if(entity != null){
				return (JyDevices) entity;
			}
			
		}
		return null;
	}

}
