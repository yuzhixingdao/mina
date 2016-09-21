package com.jy.push.dao.impl;

import java.util.List;

import com.jy.framework.dao.impl.BaseHibernateDaoImpl;
import com.jy.framework.exception.DAOException;
import com.jy.push.dao.DictionaryDao;
import com.jy.push.entity.Dictionary;

public class DictionaryDaoImpl extends BaseHibernateDaoImpl<Dictionary> implements DictionaryDao {

	@Override
	public List<Dictionary> findList() throws DAOException {
		StringBuffer hql = new StringBuffer();
		hql.append("from Dictionary order by dictOrder");
		List list = super.findList(hql.toString());
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

}
