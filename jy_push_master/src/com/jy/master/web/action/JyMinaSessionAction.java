package com.jy.master.web.action;

import com.jy.framework.util.StringUtil;
import com.jy.master.init.DictionaryInit;
import com.jy.master.service.MasterService;
import com.jy.push.entity.JyDevices;
import com.jy.push.entity.JyMinaSession;

public class JyMinaSessionAction extends BaseAction {

	private JyMinaSession minaSession;
	private JyDevices devices;
	
	private DictionaryInit dictionaryInit;
	private MasterService masterService;

	/**
	 * 查询Session
	 * @return
	 */
	public String queryPage() {
		
		super.page = masterService.getMinaSessionPage(minaSession, pageNum, pageSize);
		
		return "success_queryPage";
	}
	
	/**
	 * 查询消息详情
	 * @return
	 */
	public String queryData(){
		if(id != null){
			this.minaSession = masterService.getMinaSessionData(id);
			this.devices = masterService.getDevicesData(this.minaSession.getDevicesId());
		}
		return "success_queryData";
	}
	
	/**
	 * 删除数据
	 * @return
	 */
	public String removeData(){
		
		if(ids != null){
			String[] id = StringUtil.split(ids, ",");
			masterService.removeMinaSession(id);
			return "success_removeData";
		}
		
		return "error_removeData";
	}


	public JyMinaSession getMinaSession() {
		return minaSession;
	}

	public void setMinaSession(JyMinaSession minaSession) {
		this.minaSession = minaSession;
	}

	public void setMasterService(MasterService masterService) {
		this.masterService = masterService;
	}

	public void setDictionaryInit(DictionaryInit dictionaryInit) {
		this.dictionaryInit = dictionaryInit;
	}

	public DictionaryInit getDictionaryInit() {
		return dictionaryInit;
	}

	public JyDevices getDevices() {
		return devices;
	}

	public void setDevices(JyDevices devices) {
		this.devices = devices;
	}

}
