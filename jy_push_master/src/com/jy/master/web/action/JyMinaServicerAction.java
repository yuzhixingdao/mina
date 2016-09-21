package com.jy.master.web.action;

import com.jy.framework.util.StringUtil;
import com.jy.master.init.DictionaryInit;
import com.jy.master.service.MasterService;
import com.jy.push.entity.JyMinaServicer;

public class JyMinaServicerAction extends BaseAction {

	private JyMinaServicer minaServicer;
	
	private DictionaryInit dictionaryInit;
	private MasterService masterService;

	/**
	 * ��ѯSession
	 * @return
	 */
	public String queryPage() {
		
		super.page = masterService.getMinaServicerPage(minaServicer, pageNum, pageSize);
		
		return "success_queryPage";
	}
	
	/**
	 * ��ѯ��Ϣ����
	 * @return
	 */
	public String queryData(){
		if(id != null){
			this.minaServicer = masterService.getMinaServicerData(id);
		}
		return "success_queryData";
	}
	
	/**
	 * �ύ���
	 * @return
	 */
	public String editDataSubmit(){
		
		if(minaServicer != null){
			masterService.addOrUpdateMinaServicer(minaServicer);
		}
		
		return "success_editDataSubmit";
	}
	
	/**
	 * ��ȡ�༭����
	 * @return
	 */
	public String editData(){
		if(id != null){
			this.minaServicer = masterService.getMinaServicerData(id);
		}
		return "success_editData";
	}
	
	/**
	 * ɾ������
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


	public void setMasterService(MasterService masterService) {
		this.masterService = masterService;
	}

	public void setDictionaryInit(DictionaryInit dictionaryInit) {
		this.dictionaryInit = dictionaryInit;
	}

	public DictionaryInit getDictionaryInit() {
		return dictionaryInit;
	}

	public JyMinaServicer getMinaServicer() {
		return minaServicer;
	}

	public void setMinaServicer(JyMinaServicer minaServicer) {
		this.minaServicer = minaServicer;
	}

}
