package com.jy.push.dao;

import java.util.Map;

import com.jy.framework.dao.BaseHibernateDao;
import com.jy.framework.exception.DAOException;
import com.jy.push.entity.JyMinaSession;

public interface JyMinaSessionDao extends BaseHibernateDao<JyMinaSession> {

	JyMinaSession getMinaSession(JyMinaSession session) throws DAOException;

	Map<String, Object> getMinaSessionPage(JyMinaSession minaSession,
			Integer pageNum, Integer pageSize) throws DAOException;

}
