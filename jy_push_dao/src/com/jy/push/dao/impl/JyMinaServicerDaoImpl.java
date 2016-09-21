package com.jy.push.dao.impl;

import com.jy.framework.dao.impl.BaseHibernateDaoImpl;
import com.jy.framework.exception.DAOException;
import com.jy.push.dao.JyMinaServicerDao;
import com.jy.push.entity.JyMinaServicer;

public class JyMinaServicerDaoImpl extends BaseHibernateDaoImpl<JyMinaServicer> implements
		JyMinaServicerDao {

	public JyMinaServicer getMinaServicer(String ip, Integer port)
			throws DAOException {
		if(ip != null && port != null){
			Object entity = super.findEntity("from JyMinaServicer where ip = ? and port = ?", ip, port);
			if(entity != null){
				return (JyMinaServicer) entity;
			}
		}
		return null;
	}

}
