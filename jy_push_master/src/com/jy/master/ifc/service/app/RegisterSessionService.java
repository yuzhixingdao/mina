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
 * APP�ն�ע��mina
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
				
				//�ж�accessKey�Ƿ���Ч
				JyAccess access = masterService.getAccessByAccessKey(accessKey);
				if(access != null){
					
					//�ж�֮ǰ���豸�Ƿ�ע���
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
					
					//��ȡ����mina����
					JyMinaServicer mina = masterService.getAvailableMinaServicer();
					if(mina == null){
						response.setResponseCode("0");
						response.setErrorMessage("ʧ�ܣ������ͷ���");
						return response;
					}
					
					//���׼������session����
					JyMinaSession session = new JyMinaSession();
					session.setMsId(mina.getId());
					session.setSessionId(devices.getToken());//���sessionId,��token��Ϊsession��Ψһ��ʶ
					session.setDevicesId(devices.getId());
					session.setToken(devices.getToken());
					session = masterService.getMinaSession(session);
					
					if (session == null) {
						session = new JyMinaSession();
						session.setMsId(mina.getId());
						session.setSessionId(devices.getToken());//���sessionId,��token��Ϊsession��Ψһ��ʶ
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
						session.setSessionId(devices.getToken());//���sessionId,��token��Ϊsession��Ψһ��ʶ
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
					response.setErrorMessage("�ɹ�");
					
					return response;
					
				}else{
					response.setResponseCode("0");
					response.setErrorMessage("ʧ�ܣ�ACCESS KEY���Ϸ�");
					return response;
				}
				
			}else{
				response.setResponseCode("0");
				response.setErrorMessage("ʧ�ܣ�����Ϊ��");
				return response;
			}
			
		}catch(Exception ex){
			response.setResponseCode("0");
			response.setErrorMessage("ʧ�ܣ������쳣");
			ex.printStackTrace();
		}
		
		return response;
	}


	public void setMasterService(MasterService masterService) {
		this.masterService = masterService;
	}

}
