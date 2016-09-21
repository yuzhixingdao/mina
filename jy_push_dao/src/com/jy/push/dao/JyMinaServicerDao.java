package com.jy.push.dao;

import com.jy.framework.dao.BaseHibernateDao;
import com.jy.framework.exception.DAOException;
import com.jy.push.entity.JyMinaServicer;

public interface JyMinaServicerDao extends BaseHibernateDao<JyMinaServicer> {

	JyMinaServicer getMinaServicer(String ip, Integer port) throws DAOException;

}
