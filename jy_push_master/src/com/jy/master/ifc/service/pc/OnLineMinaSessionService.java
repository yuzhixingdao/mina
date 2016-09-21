package com.jy.master.ifc.service.pc;

import com.google.gson.reflect.TypeToken;
import com.jy.framework.util.GsonUtil;
import com.jy.framework.util.UrlUtil;
import com.jy.master.ifc.core.IFCService;
import com.jy.master.ifc.core.Request;
import com.jy.master.ifc.core.Response;
import com.jy.master.ifc.dto.pc.QtOnLineMinaSessionDTO;
import com.jy.master.ifc.dto.pc.SpOnLineMinaSessionDTO;
import com.jy.slave.dict.RequestType;

/**
 * 获取在线的session
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
			
			if(qtDTO != null && qtDTO.getIfcUrl() != null && qtDTO.getSessionId() != null){
				
				SpOnLineMinaSessionDTO spDTO = process(qtDTO.getIfcUrl(), qtDTO.getSessionId());
				response.setResponseCode("1");
				response.setData(spDTO);
				
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
	
	private SpOnLineMinaSessionDTO process(String ifcUrl, String sessionId){
		
		SpOnLineMinaSessionDTO spDTO = new SpOnLineMinaSessionDTO();
		
		try {
			
			if(ifcUrl != null && sessionId != null){
				
				com.jy.slave.ifc.dto.QtOnLineMinaSessionDTO qtDTO = new com.jy.slave.ifc.dto.QtOnLineMinaSessionDTO();
				qtDTO.setSessionId(sessionId);
				
				com.jy.slave.ifc.core.Request<com.jy.slave.ifc.dto.QtOnLineMinaSessionDTO> qtData = new com.jy.slave.ifc.core.Request<com.jy.slave.ifc.dto.QtOnLineMinaSessionDTO>();
				qtData.setRequestCode(RequestType.ON_LINE_MINA_SESSION);
				qtData.setData(qtDTO);
				
				String spData = UrlUtil.sendData(ifcUrl, GsonUtil.toJson(qtData));
				
				com.jy.slave.ifc.core.Response<com.jy.slave.ifc.dto.SpOnLineMinaSessionDTO> response = new com.jy.slave.ifc.core.Response<com.jy.slave.ifc.dto.SpOnLineMinaSessionDTO>().fromJson(
						spData, new TypeToken<com.jy.slave.ifc.core.Response<com.jy.slave.ifc.dto.SpOnLineMinaSessionDTO>>(){});
				
				if(response != null && response.getResponseCode() != null && response.getResponseCode().equals("1") && response.getData() != null){
					spDTO.setIsOnlineStatus(response.getData().getIsOnLine());
				}else{
					spDTO.setIsOnlineStatus("0");
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return spDTO;
	}


}
