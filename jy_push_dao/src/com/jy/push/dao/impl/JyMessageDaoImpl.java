package com.jy.push.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jy.framework.dao.impl.BaseHibernateDaoImpl;
import com.jy.framework.exception.DAOException;
import com.jy.push.dao.JyMessageDao;
import com.jy.push.entity.JyMessage;

public class JyMessageDaoImpl extends BaseHibernateDaoImpl<JyMessage> implements
		JyMessageDao {

	@Override
	public Map<String, Object> getMessagePage(JyMessage message,
			Integer pageNum, Integer pageSize) throws DAOException {
		
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("from JyMessage entity where 1=1 ");
		
		if(message != null){
			if(message.getMessageContent() != null && !message.getMessageContent().equals("")){
				hql.append(" and entity.messageContent like ? ");
				params.add("%" + message.getMessageContent() + "%");
			}
		}
		
		hql.append(" order by entity.firstDate desc nulls last ");
		
		return super.findPage(hql.toString(), params, pageNum, pageSize);
		
	}

	@Override
	public void deleteMessage(String id) throws DAOException {
		if(id != null && !id.equals("")){
			super.delete("delete from JyMessage where id = ?", id);
		}
	}

}
