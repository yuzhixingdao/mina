package com.jy.master.ifc.service.pc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.jy.framework.util.GsonUtil;
import com.jy.framework.util.UrlUtil;
import com.jy.master.ifc.core.IFCService;
import com.jy.master.ifc.core.Request;
import com.jy.master.ifc.core.Response;
import com.jy.master.ifc.dto.pc.QtSendMessageDTO;
import com.jy.master.ifc.dto.pc.SpSendMessageDTO;
import com.jy.master.service.MasterService;
import com.jy.push.entity.JyAccess;
import com.jy.push.entity.JyDevices;
import com.jy.push.entity.JyMessage;
import com.jy.push.entity.JyMinaSession;
import com.jy.slave.dict.RequestType;

public class SendMessageService implements IFCService<SpSendMessageDTO> {

	private MasterService masterService;
	
	public Response<SpSendMessageDTO> execute(String qtData) {
		
		Response<SpSendMessageDTO> response = new Response<SpSendMessageDTO>();
		
		try{
			Request<QtSendMessageDTO> request = new Request<QtSendMessageDTO>().fromJson(
					qtData, new TypeToken<Request<QtSendMessageDTO>>(){});
			
			QtSendMessageDTO qtDTO = request.getData();
			
			String accessId = qtDTO.getAccessId();
			String token = qtDTO.getToken();
			String data = qtDTO.getData();
			
			if(accessId != null && !accessId.trim().equals("") && token != null && !token.trim().equals("") && data != null && !data.trim().equals("")){
				
				JyAccess access = masterService.getAccessByAccessId(accessId);
				if(access == null){
					response.setResponseCode("0");
					response.setErrorMessage("失败，Access id 不存在,请注册");
					return response;
				}
				
				JyDevices devices = new JyDevices();
				devices.setAccessId(access.getId());
				devices.setToken(token);
				devices = masterService.getDevices(devices);
				if(devices != null){
					
					//添加要发送的消息
					JyMessage message = new JyMessage();
					message.setMessageContent(data);
					message.setToken(devices.getToken());
					message.setDevicesId(devices.getId());
					message.setFirstDate(new Date());
					message.setSendDate(new Date());
					masterService.addMessage(message);
					
					Map<String, Object> pushData = new HashMap<String, Object>();
					Map<String, Object> pushJyData = new HashMap<String, Object>();
					pushJyData.put("messageId", message.getId());
					
					pushData.put("push", GsonUtil.toJson(pushJyData));
					pushData.put("data", data);
					
					//{"data":"{\"successFlag\":\"1\",\"type\":\"1\"}","push":{"messageId":"4028f82151195dad0151197169e40006"}}
					data = GsonUtil.toJson(pushData);
					System.out.println("data>>>>>>>>>>>>>>>>>>>" + data);
					
					JyMinaSession ms = new JyMinaSession();
					ms.setDevicesId(devices.getId());
					ms.setToken(token);
					ms = masterService.getMinaSession(ms);
					if(ms != null){
						
						return process(ms.getSessionId(), data, ms);
						
					}else{
						response.setResponseCode("0");
						response.setErrorMessage("失败，session不存在，请注册");
						return response;
					}
					
				}else{
					response.setResponseCode("0");
					response.setErrorMessage("失败，设备token不存在，请注册");
					return response;
				}
				
			}else{
				response.setResponseCode("0");
				response.setErrorMessage("失败，参数为空");
				return response;
			}
			
		}catch(Exception ex){
			response.setResponseCode("0");
			response.setErrorMessage("失败，服务异常");
			ex.printStackTrace();
		}
		
		return response;
	}
	
	private Response<SpSendMessageDTO> process(String sessionId, String data, JyMinaSession ms){
		
		Response<SpSendMessageDTO> retResponse = new Response<SpSendMessageDTO>();
		
		try {
			com.jy.slave.ifc.dto.QtSendMessageDTO smDTO = new com.jy.slave.ifc.dto.QtSendMessageDTO();
			smDTO.setDevicesId(ms.getDevicesId());
			smDTO.setToken(ms.getToken());
			smDTO.setSessionId(sessionId);
			smDTO.setData(data);
			
			com.jy.slave.ifc.core.Request<com.jy.slave.ifc.dto.QtSendMessageDTO> qtData = new com.jy.slave.ifc.core.Request<com.jy.slave.ifc.dto.QtSendMessageDTO>();
			qtData.setRequestCode(RequestType.SEND_MESSAGE);
			qtData.setData(smDTO);
			
			String spData = UrlUtil.sendData(ms.getIfcUrl() , GsonUtil.toJson(qtData));
			
			com.jy.slave.ifc.core.Response<com.jy.slave.ifc.dto.SpSendMessageDTO> response = new com.jy.slave.ifc.core.Response<com.jy.slave.ifc.dto.SpSendMessageDTO>().fromJson(
					spData, new TypeToken<com.jy.slave.ifc.core.Response<com.jy.slave.ifc.dto.SpSendMessageDTO>>(){});
			
			if(response != null && response.getResponseCode() != null && response.getResponseCode().equals("1")){
				retResponse.setResponseCode("1");
				retResponse.setErrorMessage("发送成功");
			}else{
				retResponse.setResponseCode("0");
				retResponse.setErrorMessage(response.getErrorMessage());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retResponse;
	}


	public void setMasterService(MasterService masterService) {
		this.masterService = masterService;
	}

}
