package com.jy.push.dao;

import com.jy.framework.dao.BaseHibernateDao;
import com.jy.framework.exception.DAOException;
import com.jy.push.entity.JyDevices;

public interface JyDevicesDao extends BaseHibernateDao<JyDevices> {

	JyDevices getDevicesByAccessId(String accessId) throws DAOException;

	JyDevices getDevices(JyDevices devices) throws DAOException;

}
