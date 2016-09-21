package com.jy.master.service.impl;

import java.util.List;
import java.util.Map;

import com.jy.framework.exception.ServiceException;
import com.jy.framework.util.GsonUtil;
import com.jy.master.ifc.core.Request;
import com.jy.master.ifc.core.Response;
import com.jy.master.ifc.dto.pc.QtOnLineMinaSessionDTO;
import com.jy.master.ifc.dto.pc.SpOnLineMinaSessionDTO;
import com.jy.master.ifc.service.pc.OnLineMinaSessionService;
import com.jy.master.service.IfcService;
import com.jy.push.entity.JyMinaSession;

public class IfcServiceImpl implements IfcService {

	private OnLineMinaSessionService onLineMinaSessionService;
	
	public void setOnLineMinaSessionService(
			OnLineMinaSessionService onLineMinaSessionService) {
		this.onLineMinaSessionService = onLineMinaSessionService;
	}

	@Override
	public void isSession(List<Map<String, Object>> sessionList) throws ServiceException {
		
		if(sessionList != null && sessionList.size() > 0){
			
			for (int i = 0; i < sessionList.size(); i++) {
				
				Map<String, Object> map = sessionList.get(i);
				JyMinaSession session = (JyMinaSession) map.get("session");
				
				if (session.getIfcUrl() != null && !"".equals(session.getIfcUrl())) {
					Request<QtOnLineMinaSessionDTO> request = new Request<QtOnLineMinaSessionDTO>();
					request.setData(new QtOnLineMinaSessionDTO(session.getIfcUrl(), session.getSessionId()));
					
					Response<SpOnLineMinaSessionDTO> response = onLineMinaSessionService.execute(GsonUtil.toJson(request));
					if (response != null && response.getData() != null) {
						SpOnLineMinaSessionDTO spDTO = response.getData();
						String isOnlineStatus = spDTO.getIsOnlineStatus();
						map.put("isOnlineStatus", isOnlineStatus);
					}
				}
				
			}
			
		}
		
	}

	
	
}
