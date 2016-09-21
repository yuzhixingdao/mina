package com.jy.push.dao;

import java.util.Map;

import com.jy.framework.dao.BaseHibernateDao;
import com.jy.framework.exception.DAOException;
import com.jy.push.entity.JyMessage;

public interface JyMessageDao extends BaseHibernateDao<JyMessage>{

	Map<String, Object> getMessagePage(JyMessage message, Integer pageNum,
			Integer pageSize) throws DAOException;

	void deleteMessage(String id) throws DAOException;

}
