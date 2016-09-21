package com.jy.slave.job.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jy.framework.util.GsonUtil;
import com.jy.push.entity.JyMessage;
import com.jy.push.mina.session.SessionManager;
import com.jy.slave.job.MessageSentService;
import com.jy.slave.service.SlaveService;

public class MessageSentServiceImpl implements MessageSentService {

	private SlaveService slaveService;

	public void setSlaveService(SlaveService slaveService) {
		this.slaveService = slaveService;
	}
	
	public void scanMessageSent(){
		//System.out.println("ÕýÔÚÉ¨ÃèÍÆËÍ...");
		
		List list = slaveService.getMessageSentList();
		if(list != null && list.size() > 0){
			
			for (int i = 0; i < list.size(); i++) {
				
				JyMessage message = (JyMessage) list.get(i);
				
				Map<String, Object> pushData = new HashMap<String, Object>();
				Map<String, Object> pushJyData = new HashMap<String, Object>();
				pushJyData.put("messageId", message.getId());
				
				pushData.put("push", GsonUtil.toJson(pushJyData));
				pushData.put("data", message.getMessageContent());
				
				//{"data":"{\"successFlag\":\"1\",\"type\":\"1\"}","push":"{\"messageId\":\"4028f82151195dad01511993d2ab0037\"}"}
				String data = GsonUtil.toJson(pushData);
				
				SessionManager.pushToken(message.getToken(), data);
				
			}
			
		}
		
	}
	
}
