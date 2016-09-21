package com.jy.push.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jy.framework.dao.impl.BaseHibernateDaoImpl;
import com.jy.framework.exception.DAOException;
import com.jy.push.dao.JyMinaSessionDao;
import com.jy.push.entity.JyMinaSession;

public class JyMinaSessionDaoImpl extends BaseHibernateDaoImpl<JyMinaSession> implements
		JyMinaSessionDao {

	public JyMinaSession getMinaSession(JyMinaSession session)
			throws DAOException {
		if(session != null){
			
			List<Object> params = new ArrayList<Object>();
			StringBuffer hql = new StringBuffer();
			
			hql.append("from JyMinaSession where 1=1 ");
			
			if(session.getSessionId() != null && !session.getSessionId().trim().equals("")){
				hql.append(" and sessionId = ? ");
				params.add(session.getSessionId());
			}
			
			if(session.getDevicesId() != null && !session.getDevicesId().trim().equals("")){
				hql.append(" and devicesId = ? ");
				params.add(session.getDevicesId());
			}
			if(session.getDeviceNo() != null && !session.getDeviceNo().trim().equals("")){
				hql.append(" and deviceNo = ? ");
				params.add(session.getDeviceNo());
			}
			if(session.getToken() != null && !session.getToken().trim().equals("")){
				hql.append(" and token = ? ");
				params.add(session.getToken());
			}
			
			if(session.getMsId() != null && !session.getMsId().trim().equals("")){
				hql.append(" and msId = ? ");
				params.add(session.getMsId());
			}
			if(session.getMsIp() != null && !session.getMsIp().trim().equals("")){
				hql.append(" and msIp = ? ");
				params.add(session.getMsIp());
			}
			if(session.getMsPort() != null){
				hql.append(" and msPort = ? ");
				params.add(session.getMsPort());
			}
			
			Object entity = super.findEntity(hql.toString(), params);
			if(entity != null){
				return (JyMinaSession) entity;
			}
			
		}
		return null;
	}

	@Override
	public Map<String, Object> getMinaSessionPage(JyMinaSession minaSession,
			Integer pageNum, Integer pageSize) throws DAOException {

		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("from JyMinaSession entity where 1=1 ");
		
		if(minaSession != null){
			if(minaSession.getToken() != null && !minaSession.getToken().equals("")){
				hql.append(" and entity.token like ? ");
				params.add("%" + minaSession.getToken() + "%");
			}
		}
		
		hql.append(" order by entity.createDate desc nulls last ");
		
		return super.findPage(hql.toString(), params, pageNum, pageSize);

	}

}
