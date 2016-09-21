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
 * ������Ϣ
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
					response.setErrorMessage("�ɹ�");
				}else{
					response.setResponseCode("0");
					response.setErrorMessage("ʧ�ܣ�session������");
				}
			}else{
				response.setResponseCode("0");
				response.setErrorMessage("ʧ�ܣ�����Ϊ��");
			}
			
		}catch(Exception ex){
			response.setResponseCode("0");
			response.setErrorMessage("ʧ�ܣ������쳣");
			ex.printStackTrace();
		}
		
		return response;
		
	}

	public void setSlaveService(SlaveService slaveService) {
		this.slaveService = slaveService;
	}

}
