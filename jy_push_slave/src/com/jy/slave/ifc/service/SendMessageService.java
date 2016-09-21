package com.jy.slave.ifc.service;

import com.google.gson.reflect.TypeToken;
import com.jy.push.mina.session.SessionManager;
import com.jy.slave.ifc.core.IFCService;
import com.jy.slave.ifc.core.Request;
import com.jy.slave.ifc.core.Response;
import com.jy.slave.ifc.dto.QtSendMessageDTO;
import com.jy.slave.ifc.dto.SpSendMessageDTO;
import com.jy.slave.service.SlaveService;

/**
 * 发送消息
 * @author Sxq
 * @date Oct 30, 2015
 *
 */
public class SendMessageService implements IFCService<SpSendMessageDTO> {
	
	private SlaveService slaveService;

	public Response<SpSendMessageDTO> execute(String qtData) {
		
		Response<SpSendMessageDTO> response = new Response<SpSendMessageDTO>();
		
		try{
			Request<QtSendMessageDTO> request = new Request<QtSendMessageDTO>().fromJson(
					qtData, new TypeToken<Request<QtSendMessageDTO>>(){});
			
			QtSendMessageDTO qtDTO = request.getData();
			String sessionId = qtDTO.getSessionId();
			String data = qtDTO.getData();
			
			if(sessionId != null && !sessionId.trim().equals("") && data != null && !data.trim().equals("")){
				
				boolean ret = SessionManager.pushToken(sessionId, data);
				if(ret){
					response.setResponseCode("1");
					response.setErrorMessage("成功");
				}else{
					response.setResponseCode("0");
					response.setErrorMessage("失败，session不存在");
				}
			}else{
				response.setResponseCode("0");
				response.setErrorMessage("失败，参数为空");
			}
			
		}catch(Exception ex){
			response.setResponseCode("0");
			response.setErrorMessage("失败，服务异常");
			ex.printStackTrace();
		}
		
		return response;
		
	}

	public void setSlaveService(SlaveService slaveService) {
		this.slaveService = slaveService;
	}

}
