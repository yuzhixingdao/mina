package com.jy.master.ifc.service.app;

import java.util.Date;

import com.google.gson.reflect.TypeToken;
import com.jy.framework.util.MD5Util;
import com.jy.master.ifc.core.IFCService;
import com.jy.master.ifc.core.Request;
import com.jy.master.ifc.core.Response;
import com.jy.master.ifc.dto.app.QtRegisterSessionDTO;
import com.jy.master.ifc.dto.app.SpRegisterSessionDTO;
import com.jy.master.service.MasterService;
import com.jy.push.dict.DictStatus;
import com.jy.push.entity.JyAccess;
import com.jy.push.entity.JyDevices;
import com.jy.push.entity.JyMinaServicer;
import com.jy.push.entity.JyMinaSession;

/**
 * APP终端注册mina
 * @author Sxq
 * @date Oct 29, 2015
 *
 */
public class RegisterSessionService implements IFCService<SpRegisterSessionDTO> {

	private MasterService masterService;
	
	public Response<SpRegisterSessionDTO> execute(String qtData) {
		
		Response<SpRegisterSessionDTO> response = new Response<SpRegisterSessionDTO>();
		
		try{
			Request<QtRegisterSessionDTO> request = new Request<QtRegisterSessionDTO>().fromJson(
					qtData, new TypeToken<Request<QtRegisterSessionDTO>>(){});
			
			QtRegisterSessionDTO qtDTO = request.getData();
			
			String accessKey = qtDTO.getAccessKey();
			String mid = qtDTO.getMid();
			String userId = qtDTO.getUserId();
			String other = qtDTO.getOther();
			
			if(accessKey != null && !accessKey.trim().equals("") && mid != null && !mid.trim().equals("")){
				
				//判断accessKey是否有效
				JyAccess access = masterService.getAccessByAccessKey(accessKey);
				if(access != null){
					
					//判断之前此设备是否注册过
					JyDevices devices = new JyDevices();
					devices.setAccessId(access.getId());
					devices.setDeviceNo(mid);
					devices.setUserId(userId);
					devices.setOther(other);
					devices = masterService.getDevices(devices);
					if(devices == null){
						devices = new JyDevices();
						devices.setAccessId(access.getId());
						devices.setToken(MD5Util.encrypt(mid + userId + other));
						devices.setDeviceNo(mid);
						devices.setUserId(userId);
						devices.setOther(other);
						devices.setCreateDate(new Date());
						devices.setDelFlag("0");
						masterService.addDevices(devices);
					}else{
						devices.setUpdateDate(new Date());
						devices.setDelFlag("0");
						masterService.updateDevices(devices);
					}
					
					//获取可用mina服务
					JyMinaServicer mina = masterService.getAvailableMinaServicer();
					if(mina == null){
						response.setResponseCode("0");
						response.setErrorMessage("失败，无推送服务");
						return response;
					}
					
					//添加准备链接session数据
					JyMinaSession session = new JyMinaSession();
					session.setMsId(mina.getId());
					session.setSessionId(devices.getToken());//存放sessionId,将token作为session的唯一标识
					session.setDevicesId(devices.getId());
					session.setToken(devices.getToken());
					session = masterService.getMinaSession(session);
					
					if (session == null) {
						session = new JyMinaSession();
						session.setMsId(mina.getId());
						session.setSessionId(devices.getToken());//存放sessionId,将token作为session的唯一标识
						session.setDevicesId(devices.getId());
						session.setToken(devices.getToken());
						session.setMsIp(mina.getIp());
						session.setMsPort(mina.getPort());
						session.setIfcUrl(mina.getIfcUrl());
						session.setDeviceNo(devices.getDeviceNo());
						session.setSessionState(DictStatus.MINA_SESSION_UNAVAILABLE);
						session.setCreateDate(new Date());
						session.setConnectDate(new Date());
						session.setDelFlag("0");
						
						masterService.addMinaSession(session);
					} else {
						session.setMsId(mina.getId());
						session.setSessionId(devices.getToken());//存放sessionId,将token作为session的唯一标识
						session.setDevicesId(devices.getId());
						session.setToken(devices.getToken());
						session.setMsIp(mina.getIp());
						session.setMsPort(mina.getPort());
						session.setIfcUrl(mina.getIfcUrl());
						session.setDeviceNo(devices.getDeviceNo());
						session.setSessionState(DictStatus.MINA_SESSION_UNAVAILABLE);
						session.setConnectDate(new Date());
						session.setUpdateDate(new Date());
						session.setDelFlag("0");
						
						masterService.updateMinaSession(session);
					}
					
					SpRegisterSessionDTO spDTO = new SpRegisterSessionDTO();
					spDTO.setToken(devices.getToken());
					spDTO.setIp(mina.getIp());
					spDTO.setPort(mina.getPort());
					
					response.setData(spDTO);
					response.setResponseCode("1");
					response.setErrorMessage("成功");
					
					return response;
					
				}else{
					response.setResponseCode("0");
					response.setErrorMessage("失败，ACCESS KEY不合法");
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


	public void setMasterService(MasterService masterService) {
		this.masterService = masterService;
	}

}
