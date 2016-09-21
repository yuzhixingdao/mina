package com.jy.framework.dao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jy.framework.dao.util.ParameterMap;
import com.jy.framework.exception.DAOException;


public interface BaseHibernateDao<ENTITY> {

	/**
	 * Save entity
	 * @param entity
	 * @return
	 * @throws DAOException
	 */
	ENTITY save(ENTITY entity) throws DAOException;

	/**
	 * Update entity
	 * @param entity
	 * @return
	 * @throws DAOException
	 */
	ENTITY update(ENTITY entity) throws DAOException;
	
	/**
	 * Update
	 * @param hql
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	Integer update(String hql, List<Object> params) throws DAOException;
	
	/**
	 * Update
	 * @param hql
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	Integer update(String hql, Object ... params) throws DAOException;

	/**
	 * Delete
	 * @param entity
	 * @throws DAOException
	 */
	void delete(ENTITY entity) throws DAOException;
	
	/**
	 * Delete
	 * @param hql
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	Integer delete(String hql, List<Object> params) throws DAOException;
	
	/**
	 * Delete
	 * @param hql
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	Integer delete(String hql, Object ... params) throws DAOException;

	/**
	 * Get entity
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	ENTITY get(Serializable id) throws DAOException;

	/**
	 * Load
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	ENTITY load(Serializable id) throws DAOException;

	/**
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	Object findEntity(String hql, Object ... params) throws DAOException;
	
	/**
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	Object findEntity(String hql, List<Object> params) throws DAOException;
	
	/**
	 * Find all
	 * @return
	 * @throws DAOException
	 */
	List<ENTITY> findList() throws DAOException;
	
	/**
	 * Find list
	 * @param hql
	 * @return
	 * @throws DAOException
	 */
	List<Object> findList(String hql) throws DAOException;
	
	/**
	 * Find list
	 * @param hql
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	List<Object> findList(String hql, List<Object> params) throws DAOException;
	
	/**
	 * Find list
	 * @param hql
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	List<Object> findList(String hql, Object ... params) throws DAOException;
	
	/**
	 * Page find
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws DAOException
	 */
	Map<String, Object> findPage(Integer pageNum, Integer pageSize) throws DAOException;
	
	/**
	 * Page find
	 * @param hql
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws DAOException
	 */
	Map<String, Object> findPage(String hql, Integer pageNum, Integer pageSize) throws DAOException;
	
	/**
	 * Page find
	 * @param hql
	 * @param params
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws DAOException
	 */
	Map<String, Object> findPage(String hql, List<Object> params, Integer pageNum, Integer pageSize) throws DAOException;
	
	
	
	/**
	 * Find list
	 * @param sql select {a.*}, {b.*} from table_a a, table_b b where ...
	 * @param params params
	 * @param clazzs entity class
	 * @return
	 * @throws DAOException
	 */
	List<Object> findListBySql(String sql, List<Object> params, Map<String, Class<?>> clazzs) throws DAOException;
	
	/**
	 * Find page
	 * @param sql select {a.*}, {b.*} from table_a a, table_b b where ...
	 * @param params
	 * @param clazzs
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws DAOException
	 */
	Map<String, Object> findPageBySql(String sql, List<Object> params, List<ParameterMap> clazzs, Integer pageNum, Integer pageSize) throws DAOException;
	
	/**
	 * Find page
	 * @param sql	sql select {a.*}, {b.*} from table_a a, table_b b where ...
	 * @param params
	 * @param clazzs
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws DAOException
	 */
	Map<String, Object> findPageBySql(String sql, List<Object> params, Map<String, Class<?>> clazzs, Integer pageNum, Integer pageSize) throws DAOException;
	
}
