package com.jy.master.web.action;

import com.jy.framework.util.StringUtil;
import com.jy.master.init.DictionaryInit;
import com.jy.master.service.MasterService;
import com.jy.push.entity.JyMessage;

public class JyMessageAction extends BaseAction {

	private JyMessage message;
	
	private DictionaryInit dictionaryInit;
	private MasterService masterService;

	/**
	 * ��ѯ������Ϣ
	 * @return
	 */
	public String queryPage() {
		
		super.page = masterService.getMessagePage(message, pageNum, pageSize);
		
		return "success_queryPage";
	}
	
	/**
	 * ��ѯ��Ϣ����
	 * @return
	 */
	public String queryData(){
		if(id != null){
			this.message = masterService.getMessageData(id);
		}
		return "success_queryData";
	}
	
	/**
	 * ɾ������
	 * @return
	 */
	public String removeData(){
		
		if(ids != null){
			String[] id = StringUtil.split(ids, ",");
			masterService.removeMessage(id);
			return "success_removeData";
		}
		
		return "error_removeData";
	}

	public JyMessage getMessage() {
		return message;
	}

	public void setMessage(JyMessage message) {
		this.message = message;
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

}
