package com.jy.push.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jy.framework.dao.impl.BaseHibernateDaoImpl;
import com.jy.framework.exception.DAOException;
import com.jy.push.dao.JyAccessDao;
import com.jy.push.entity.JyAccess;

public class JyAccessDaoImpl extends BaseHibernateDaoImpl<JyAccess> implements
		JyAccessDao {

	public JyAccess getAccessByAccessKey(String accessKey) throws DAOException {
		if(accessKey != null && !accessKey.trim().equals("")){
			Object entity = super.findEntity("from JyAccess where accessKey = ? and delFlag = ? ", accessKey, "0");
			if(entity != null){
				return (JyAccess) entity;
			}
		}
		return null;
	}

	public JyAccess getAccessByAccessId(String accessId) throws DAOException {
		if(accessId != null && !accessId.trim().equals("")){
			Object entity = super.findEntity("from JyAccess where accessId = ? and delFlag = ? ", accessId, "0");
			if(entity != null){
				return (JyAccess) entity;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> getAccessPage(JyAccess access, Integer pageNum,
			Integer pageSize) throws DAOException {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("from JyAccess entity where delFlag = ? ");
		params.add("0");
		
		if(access != null){
			if(access.getAccessKey() != null && !access.getAccessKey().equals("")){
				hql.append(" and entity.accessKey like ? ");
				params.add("%" + access.getAccessKey() + "%");
			}
		}
		
		hql.append(" order by entity.createDate desc nulls last ");
		
		return super.findPage(hql.toString(), params, pageNum, pageSize);
	}

}
