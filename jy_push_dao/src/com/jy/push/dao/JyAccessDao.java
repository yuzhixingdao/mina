package com.jy.push.dao;

import java.util.Map;

import com.jy.framework.dao.BaseHibernateDao;
import com.jy.framework.exception.DAOException;
import com.jy.push.entity.JyAccess;

public interface JyAccessDao extends BaseHibernateDao<JyAccess>{

	public JyAccess getAccessByAccessKey(String accessKey) throws DAOException;

	public JyAccess getAccessByAccessId(String accessId) throws DAOException;

	public Map<String, Object> getAccessPage(JyAccess access, Integer pageNum,
			Integer pageSize) throws DAOException;

}
