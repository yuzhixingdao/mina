package com.jy.master.service;

import java.util.List;
import java.util.Map;

import com.jy.framework.exception.ServiceException;

public interface IfcService {

	void isSession(List<Map<String, Object>> sessionList) throws ServiceException;

}
