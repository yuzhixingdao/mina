package com.jy.slave.ifc.service;

import com.google.gson.reflect.TypeToken;
import com.jy.push.mina.session.SessionManager;
import com.jy.slave.ifc.core.IFCService;
import com.jy.slave.ifc.core.Request;
import com.jy.slave.ifc.core.Response;
import com.jy.slave.ifc.dto.QtOnLineMinaSessionDTO;
import com.jy.slave.ifc.dto.SpOnLineMinaSessionDTO;

/**
 * 判断Session是否存在
 * @author Administrator
 *
 */
public class OnLineMinaSessionService implements IFCService<SpOnLineMinaSessionDTO> {
	
	public Response<SpOnLineMinaSessionDTO> execute(String qtData) {
		
		Response<SpOnLineMinaSessionDTO> response = new Response<SpOnLineMinaSessionDTO>();
		
		try{
			Request<QtOnLineMinaSessionDTO> request = new Request<QtOnLineMinaSessionDTO>().fromJson(
					qtData, new TypeToken<Request<QtOnLineMinaSessionDTO>>(){});
			
			QtOnLineMinaSessionDTO qtDTO = request.getData();
			String sessionId = qtDTO.getSessionId();
			
			if(sessionId != null && !sessionId.trim().equals("")){
				
				boolean ret = SessionManager.containsSession(sessionId);
				if(ret){
					
					SpOnLineMinaSessionDTO spDTO = new SpOnLineMinaSessionDTO("1");
					response.setData(spDTO);
					
					response.setResponseCode("1");
					response.setErrorMessage("成功");
				}else{
					
					SpOnLineMinaSessionDTO spDTO = new SpOnLineMinaSessionDTO("0");
					response.setData(spDTO);
					
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


}
