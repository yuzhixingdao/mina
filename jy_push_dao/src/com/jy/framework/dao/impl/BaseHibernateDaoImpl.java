package com.jy.framework.dao.impl;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.jy.framework.dao.BaseHibernateDao;
import com.jy.framework.dao.util.ParameterMap;
import com.jy.framework.dict.PageStatus;
import com.jy.framework.exception.DAOException;


public class BaseHibernateDaoImpl<ENTITY> implements BaseHibernateDao<ENTITY> {

	private Class<ENTITY> entityClass;

	protected HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public BaseHibernateDaoImpl() {
		Class clazz = this.getClass();
		Type type = clazz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] types = ((ParameterizedType) type).getActualTypeArguments();
			entityClass = (Class<ENTITY>) types[0];
		}
	}

	
	public ENTITY save(ENTITY entity) throws DAOException {
		if(entity != null){
			hibernateTemplate.save(entity);
		}
		return entity;
	}

	
	public ENTITY update(ENTITY entity) throws DAOException {
		if(entity != null){
			hibernateTemplate.update(entity);
		}
		return entity;
	}

	
	public Integer update(final String hql, final List<Object> params) throws DAOException {
		
		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			
			public Integer doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				if(params != null && params.size() > 0){
					for(int i=0; i<params.size(); i++){
						query.setParameter(i, params.get(i));
					}
				}
				
				int row = query.executeUpdate();
				
				return row;
			}
			
		});
	}

	
	public Integer update(String hql, Object... params) throws DAOException {
		int row = getHibernateTemplate().bulkUpdate(hql, params);
		return row;
	}

	
	public void delete(ENTITY entity) throws DAOException {
		hibernateTemplate.delete(entity);
	}

	
	public Integer delete(final String hql, final List<Object> params) throws DAOException {
		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			
			
			public Integer doInHibernate(Session session) throws HibernateException,
			SQLException {
				Query query = session.createQuery(hql);
				if(params != null && params.size() > 0){
					for(int i=0; i<params.size(); i++){
						query.setParameter(i, params.get(i));
					}
				}
				int row = query.executeUpdate();
				return row;
			}
			
		});
	}

	
	public Integer delete(final String hql, final Object... params) throws DAOException {
		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			
			public Integer doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				if(params != null && params.length > 0){
					for(int i=0; i<params.length; i++){
						query.setParameter(i, params[i]);
					}
				}
				int row = query.executeUpdate();
				return row;
			}
			
		});
	}

	
	public ENTITY get(Serializable id) throws DAOException {
		return hibernateTemplate.get(entityClass, id);
	}

	
	public ENTITY load(Serializable id) throws DAOException {
		return hibernateTemplate.load(entityClass, id);
	}

	
	public List<ENTITY> findList() throws DAOException {
		return hibernateTemplate.find("from " + entityClass.getName());
	}
	
	
	public List<Object> findList(String hql) throws DAOException{
		return hibernateTemplate.find(hql);
	}
	
	
	public List<Object> findList(String hql, List<Object> params) throws DAOException{
		if(params != null && params.size() > 0){
			Object[] values = new Object[params.size()];
			for (int i = 0; i < params.size(); i++) {
				values[i] = params.get(i);
			}
			return hibernateTemplate.find(hql, values);
		}else{
			return hibernateTemplate.find(hql);
		}
	}
	
	
	public List<Object> findList(String hql, Object ... params) throws DAOException{
		return hibernateTemplate.find(hql, params);
	}

	public Object findEntity(String hql, Object ... params) throws DAOException{
		if(params != null && params.length > 0){
			List list = hibernateTemplate.find(hql, params);
			if(list != null && list.size() > 0){
				return list.get(0);
			}
		}
		return null;
	}
	
	public Object findEntity(String hql, List<Object> params) throws DAOException{
		if(params != null && params.size() > 0){
			Object[] values = new Object[params.size()];
			for (int i = 0; i < params.size(); i++) {
				values[i] = params.get(i);
			}
			List list = hibernateTemplate.find(hql, values);
			if(list != null && list.size() > 0){
				return list.get(0);
			}
		}
		return null;
	}
	
	public Map<String, Object> findPage(final Integer pageNum, final Integer pageSize)
			throws DAOException {
		return getHibernateTemplate().execute(
				new HibernateCallback<Map<String, Object>>() {

					
					public Map<String, Object> doInHibernate(Session session)
							throws HibernateException, SQLException {

						Map<String, Object> map = new HashMap<String, Object>();

						String hql = "from " + entityClass.getName();
						
						Query query = session.createQuery(hql);
						
						query.setFirstResult((pageNum - 1) * pageSize);
						query.setMaxResults(pageSize);

						List list = query.list();
						
						Integer recTotal = getTotalHql(hql, null);
						
						map.put(PageStatus.REC_TOTAL, recTotal);
						map.put(PageStatus.PAGE_TOTAL, (recTotal - 1) / pageSize + 1);
						map.put(PageStatus.PAGE_NUM, pageNum);
						map.put(PageStatus.PAGE_SIZE, pageSize);
						map.put(PageStatus.LIST, list);

						return map;
					}
				});
	}
	
	
	public Map<String, Object> findPage(final String hql, final Integer pageNum, final Integer pageSize) throws DAOException{
		return getHibernateTemplate().execute(
				new HibernateCallback<Map<String, Object>>() {

					
					public Map<String, Object> doInHibernate(Session session)
							throws HibernateException, SQLException {

						Map<String, Object> map = new HashMap<String, Object>();

						Query query = session.createQuery(hql);
						
						query.setFirstResult((pageNum - 1) * pageSize);
						query.setMaxResults(pageSize);

						List list = query.list();
						
						Integer recTotal = getTotalHql(hql, null);
						
						map.put(PageStatus.REC_TOTAL, recTotal);
						map.put(PageStatus.PAGE_TOTAL, (recTotal - 1) / pageSize + 1);
						map.put(PageStatus.PAGE_NUM, pageNum);
						map.put(PageStatus.PAGE_SIZE, pageSize);
						map.put(PageStatus.LIST, list);

						return map;
					}
				});
	}
	
	
	public Map<String, Object> findPage(final String hql, final List<Object> params, final Integer pageNum, final Integer pageSize) throws DAOException{
		return getHibernateTemplate().execute(
				new HibernateCallback<Map<String, Object>>() {

					
					public Map<String, Object> doInHibernate(Session session)
							throws HibernateException, SQLException {

						Map<String, Object> map = new HashMap<String, Object>();

						Query query = session.createQuery(hql);
						if(params != null && params.size() > 0){
							for(int i=0; i<params.size(); i++){
								query.setParameter(i, params.get(i));
							}
						}
						
						query.setFirstResult((pageNum - 1) * pageSize);
						query.setMaxResults(pageSize);

						List list = query.list();
						
						Integer recTotal = getTotalHql(hql, params);
						
						map.put(PageStatus.REC_TOTAL, recTotal);
						map.put(PageStatus.PAGE_TOTAL, (recTotal - 1) / pageSize + 1);
						map.put(PageStatus.PAGE_NUM, pageNum);
						map.put(PageStatus.PAGE_SIZE, pageSize);
						map.put(PageStatus.LIST, list);

						return map;
					}
				});
	}

	
	public List<Object> findListBySql(final String sql, final List<Object> params, final Map<String, Class<?>> clazzs) throws DAOException{
		return getHibernateTemplate().execute(
				new HibernateCallback<List<Object>>() {

					
					public List<Object> doInHibernate(Session session)
							throws HibernateException, SQLException {

						SQLQuery query = session.createSQLQuery(sql);

						if(params != null && params.size() > 0){
							for(int i=0; i<params.size(); i++){
								query.setParameter(i, params.get(i));
							}
						}
						
						if(clazzs != null && clazzs.size() > 0){
							Set<String> keySet = clazzs.keySet();
							Iterator<String> iterator = keySet.iterator();
							
							while(iterator.hasNext()){
								String key = iterator.next();
								Class<?> clazz = clazzs.get(key);
								query.addEntity(key, clazz);
							}
						}
						
						List list = query.list();
						
						return list;
						
					}
				});
	}
	
	
	public Map<String, Object> findPageBySql(final String sql, final List<Object> params, final List<ParameterMap> clazzs, final Integer pageNum, final Integer pageSize) throws DAOException{
		return getHibernateTemplate().execute(
				new HibernateCallback<Map<String, Object>>() {

					
					public Map<String, Object> doInHibernate(Session session)
							throws HibernateException, SQLException {

						SQLQuery query = session.createSQLQuery(sql);

						if(params != null && params.size() > 0){
							for(int i=0; i<params.size(); i++){
								query.setParameter(i, params.get(i));
							}
						}
						
						if(clazzs != null && clazzs.size() > 0){
							
							for (int i = 0; i < clazzs.size(); i++) {
								ParameterMap pm = clazzs.get(i);
								query.addEntity(pm.getKey(), pm.getValue());
							}
						}
						
						query.setFirstResult((pageNum - 1) * pageSize);
						query.setMaxResults(pageSize);
						
						List list = query.list();
						
						Integer recTotal = getTotalSql(sql, params);
						
						Map<String, Object> map = new HashMap<String, Object>();
						
						map.put(PageStatus.REC_TOTAL, recTotal);
						map.put(PageStatus.PAGE_TOTAL, (recTotal - 1) / pageSize + 1);
						map.put(PageStatus.PAGE_NUM, pageNum);
						map.put(PageStatus.PAGE_SIZE, pageSize);
						map.put(PageStatus.LIST, list);

						return map;
						
					}
				});
		
	}
	
	public Map<String, Object> findPageBySql(final String sql, final List<Object> params, final Map<String, Class<?>> clazzs, final Integer pageNum, final Integer pageSize) throws DAOException{
		return getHibernateTemplate().execute(
				new HibernateCallback<Map<String, Object>>() {
					
					public Map<String, Object> doInHibernate(Session session)
							throws HibernateException, SQLException {

						SQLQuery query = session.createSQLQuery(sql);

						if(params != null && params.size() > 0){
							for(int i=0; i<params.size(); i++){
								query.setParameter(i, params.get(i));
							}
						}
						
						if(clazzs != null && clazzs.size() > 0){
							Set<String> keySet = clazzs.keySet();
							Iterator<String> iterator = keySet.iterator();
							
							while(iterator.hasNext()){
								String key = iterator.next();
								Class<?> clazz = clazzs.get(key);
								query.addEntity(key, clazz);
							}
						}
						
						query.setFirstResult((pageNum - 1) * pageSize);
						query.setMaxResults(pageSize);
						
						List list = query.list();
						
						Integer recTotal = getTotalSql(sql, params);
						
						Map<String, Object> map = new HashMap<String, Object>();
						
						map.put(PageStatus.REC_TOTAL, recTotal);
						map.put(PageStatus.PAGE_TOTAL, (recTotal - 1) / pageSize + 1);
						map.put(PageStatus.PAGE_NUM, pageNum);
						map.put(PageStatus.PAGE_SIZE, pageSize);
						map.put(PageStatus.LIST, list);

						return map;
						
					}
				});
		
	}
	
	/**
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	protected Integer getTotalHql(final String hql, final List<Object> params){
		return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {

			
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				
				String hqlBak = hql;
				String strHql = "";
				if(hqlBak.toUpperCase().indexOf("SELECT") == -1){
					strHql += "SELECT COUNT(*) " + hql;
				}else if(hqlBak.toUpperCase().indexOf("SELECT") < 6){
					strHql += "SELECT COUNT(*) " + hqlBak.substring(hqlBak.toUpperCase().indexOf("FROM"));
				}
				
				Query totalQuery = session.createQuery(strHql);
				if(params != null && params.size() > 0){
					for(int i=0; i<params.size(); i++){
						totalQuery.setParameter(i, params.get(i));
					}
				}
				
				Object resultTotal = totalQuery.uniqueResult();
				
				return resultTotal != null ? Integer.parseInt(resultTotal + "") : 0;
				
			}
		});
	}
	
	/**
	 *
	 * @param sql
	 * @param params
	 * @return
	 */
	protected Integer getTotalSql(final String sql, final List<Object> params){
		return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {

			
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String sqlBak = sql;
				String strSql = "";
				if(sqlBak.toUpperCase().indexOf("FROM") == -1){
					strSql += "SELECT COUNT(*) " + sql;
				}else if(sqlBak.toUpperCase().indexOf("FROM") != -1){
					strSql += "SELECT COUNT(*) " + sqlBak.substring(sqlBak.toUpperCase().indexOf("FROM"));
				}
				
				SQLQuery sqlQueryCount = session.createSQLQuery(strSql);
				
				if(params != null && params.size() > 0){
					for(int i=0; i<params.size(); i++){
						sqlQueryCount.setParameter(i, params.get(i));
					}
				}
				
				Object resultTotal = sqlQueryCount.uniqueResult();
				
				int count = resultTotal != null ? Integer.parseInt(resultTotal + "") : 0;
				
				return count;
				
			}
		});
	}
}
