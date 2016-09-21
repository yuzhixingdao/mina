package com.jy.slave.init;

import com.jy.push.service.PushConfigService;
import com.jy.slave.service.SlaveService;


/**
 * 
 * @author sxq
 * @date 2016年4月22日 上午11:17:50
 */
public class MinaSessionInit {

	private PushConfigService pushConfigService;
	public void setPushConfigService(PushConfigService pushConfigService) {
		this.pushConfigService = pushConfigService;
	}

	private SlaveService slaveService;
	public void setSlaveService(SlaveService slaveService) {
		this.slaveService = slaveService;
	}

	public void init() {
		
		slaveService.downlineMinaSession(pushConfigService.getPushPort());
		
	}
	
}
