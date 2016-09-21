package com.jy.slave.service.impl;

import com.jy.framework.exception.ServiceException;
import com.jy.push.entity.JyCommunion;
import com.jy.push.entity.JyDevices;
import com.jy.slave.service.IfcService;
import com.jy.slave.service.SlaveService;

public class IfcServiceImpl implements IfcService {

	private SlaveService slaveService;
	
	public void setSlaveService(SlaveService slaveService) {
		this.slaveService = slaveService;
	}

	@Override
	public void downlineSession(String token) throws ServiceException {
		
		if(token != null){
			
			JyDevices devices = slaveService.getDevicesByToken(token);
			
			if(devices != null && devices.getAccessId() != null){
				JyCommunion communion = slaveService.getCommunionByAccessId(devices.getAccessId());
				
				
				
			}
			
		}
		
	}

}
