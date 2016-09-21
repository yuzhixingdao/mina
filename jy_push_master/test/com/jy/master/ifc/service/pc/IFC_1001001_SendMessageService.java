package com.jy.master.ifc.service.pc;

import com.jy.framework.util.GsonUtil;
import com.jy.framework.util.UrlUtil;
import com.jy.master.ifc.core.Request;
import com.jy.master.ifc.dto.pc.QtSendMessageDTO;

public class IFC_1001001_SendMessageService {

	public static void main(String[] args) {
		
		QtSendMessageDTO qtDTO = new QtSendMessageDTO();
		qtDTO.setAccessId("gghelp123456");
		qtDTO.setToken("7014da1f2ecc8c2030aed16e9cb5c3c7");
		qtDTO.setData("{\"successFlag\":\"1\",\"type\":\"1\"}");

		Request<QtSendMessageDTO> request = new Request<QtSendMessageDTO>();
		request.setRequestCode("300101");
		request.setData(qtDTO);
		
		String qtData = GsonUtil.toJson(request);
		System.out.println("请求报文："+qtData);
		
		try {
//			String spData = UrlUtil.sendData("http://192.168.120.161:8787/jy_push_master/ifc" , qtData);
			String spData = UrlUtil.sendData("http://115.28.229.247:8010/jy_push_master/ifc" , qtData);
			System.out.println("响应报文："+spData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
