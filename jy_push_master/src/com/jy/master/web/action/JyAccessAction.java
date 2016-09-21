package com.jy.master.web.action;

import com.jy.framework.util.StringUtil;
import com.jy.master.init.DictionaryInit;
import com.jy.master.service.MasterService;
import com.jy.push.entity.JyAccess;

public class JyAccessAction extends BaseAction {

	private JyAccess access;
	
	private DictionaryInit dictionaryInit;
	private MasterService masterService;

	/**
	 * ��ѯAccess
	 * @return
	 */
	public String queryPage() {
		
		super.page = masterService.getAccessPage(access, pageNum, pageSize);
		
		return "success_queryPage";
	}
	
	/**
	 * ��ѯAccess����
	 * @return
	 */
	public String queryData(){
		if(id != null){
			this.access = masterService.getAccessData(id);
		}
		return "success_queryData";
	}
	
	/**
	 * �ύ���
	 * @return
	 */
	public String editDataSubmit(){
		
		if(access != null){
			masterService.addOrUpdateAccess(access);
		}
		
		return "success_editDataSubmit";
	}
	
	/**
	 * ��ȡ�༭����
	 * @return
	 */
	public String editData(){
		if(id != null){
			this.access = masterService.getAccessData(id);
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
			masterService.removeAccess(id);
			return "success_removeData";
		}
		
		return "error_removeData";
	}


	public JyAccess getAccess() {
		return access;
	}

	public void setAccess(JyAccess access) {
		this.access = access;
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
