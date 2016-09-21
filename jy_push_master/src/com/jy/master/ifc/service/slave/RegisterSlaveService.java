package com.jy.master.ifc.service.slave;

import java.util.Date;

import com.google.gson.reflect.TypeToken;
import com.jy.master.ifc.core.IFCService;
import com.jy.master.ifc.core.Request;
import com.jy.master.ifc.core.Response;
import com.jy.master.ifc.dto.slave.QtRegisterSlaveDTO;
import com.jy.master.ifc.dto.slave.SpRegisterSlaveDTO;
import com.jy.master.service.MasterService;
import com.jy.push.dict.DictStatus;
import com.jy.push.entity.JyMinaServicer;

/**
 * Slave����ע��mina��Master
 * @author Sxq
 * @date Oct 29, 2015
 *
 */
public class RegisterSlaveService implements IFCService<SpRegisterSlaveDTO> {

	private MasterService masterService;
	
	public Response<SpRegisterSlaveDTO> execute(String qtData) {
		
		Response<SpRegisterSlaveDTO> response = new Response<SpRegisterSlaveDTO>();
		
		try{
			Request<QtRegisterSlaveDTO> request = new Request<QtRegisterSlaveDTO>().fromJson(
					qtData, new TypeToken<Request<QtRegisterSlaveDTO>>(){});
			
			QtRegisterSlaveDTO qtDTO = request.getData();
			
			String ip = qtDTO.getIp();
			Integer port = qtDTO.getPort();
			
			if(ip != null && !ip.trim().equals("") && port != null && port > 0){
				
				JyMinaServicer minaServicer = masterService.getMinaServicer(ip, port);
				if(minaServicer != null){
					
					minaServicer.setIp(ip);
					minaServicer.setPort(port);
					minaServicer.setSessionNumber(0);
					minaServicer.setState(DictStatus.MINA_SERVICER_UPLINE);
					minaServicer.setUpdateDate(new Date());
					minaServicer.setDelFlag("0");
					
					masterService.updateMinaServicer(minaServicer);
					
				}else{
					
					minaServicer = new JyMinaServicer();
					minaServicer.setIp(ip);
					minaServicer.setPort(port);
					minaServicer.setSessionNumber(0);
					minaServicer.setState(DictStatus.MINA_SERVICER_UPLINE);
					minaServicer.setCreateDate(new Date());
					minaServicer.setDelFlag("0");
					
					masterService.addMinaServicer(minaServicer);
					
				}
				
				response.setResponseCode("1");
				response.setErrorMessage("�ɹ�");
				
			}else{
				response.setResponseCode("0");
				response.setErrorMessage("ʧ�ܣ�����Ϊ��");
			}
			
		}catch(Exception ex){
			String errorMessage = "ʧ�ܣ������쳣";
			response.setResponseCode("0");
			response.setErrorMessage(errorMessage);
			ex.printStackTrace();
		}
		
		return response;
	}

	public void setMasterService(MasterService masterService) {
		this.masterService = masterService;
	}

}
