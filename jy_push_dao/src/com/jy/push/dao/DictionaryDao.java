package com.jy.push.dao;

import java.util.List;

import com.jy.framework.exception.DAOException;
import com.jy.push.entity.Dictionary;

public interface DictionaryDao {

	List<Dictionary> findList() throws DAOException;
	
}
