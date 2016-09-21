package com.jy.master.ifc.core;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jy.framework.util.GsonUtil;

public class Request<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6340616552353301995L;

	private String requestCode;

	/**
	 * ƽ̨�ţ� 01-android 02-ios 03-winphone
	 */
	private String pno; // ��pno
	/**
	 * ������: 00 - ���� 01 - ���� 02 - �ȸ��г� 03 - appstore
	 */
	private String cno; // ��cno
	/**
	 * �ն�id
	 */
	private String tno; // ��tno
	/**
	 * ����
	 */
	private T data;
	/**
	 * �û�Id
	 */
	private String uid;
	/**
	 * �ỰID
	 */
	private String sid;
	/**
	 * ���ó���汾��
	 */
	private String vno;
	/**
	 * Ӧ�ó���ID
	 */
	private String appid;
	/**
	 * ��������
	 */
	private String netType;
	/**
	 * ƽ̨�汾��
	 */
	private String pvno;
	/**
	 * �ӿڷ���汾��
	 */
	private Integer apivno;
	/**
	 * ��ȫ���ͣ�Ĭ�ϲ����� 00�������� 01��DES����
	 */
	private String secType;
	/**
	 * ���ƺ�
	 */
	private String token;

	public Request<T> fromJson(String jsondata, TypeToken<Request<T>> type) {
		Gson gson = GsonUtil.createGson();
		return gson.fromJson(jsondata, type.getType());
	}

	public String getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public final String getUid() {
		return uid;
	}

	public final void setUid(String uid) {
		this.uid = uid;
	}

	public final String getSid() {
		return sid;
	}

	public final void setSid(String sid) {
		this.sid = sid;
	}

	public final String getVno() {
		return vno;
	}

	public final void setVno(String vno) {
		this.vno = vno;
	}

	public final String getAppid() {
		return appid;
	}

	public final void setAppid(String appid) {
		this.appid = appid;
	}

	public final String getNetType() {
		return netType;
	}

	public final void setNetType(String netType) {
		this.netType = netType;
	}

	public final String getPvno() {
		return pvno;
	}

	public final void setPvno(String pvno) {
		this.pvno = pvno;
	}

	public final Integer getApivno() {
		return apivno;
	}

	public final void setApivno(Integer apivno) {
		this.apivno = apivno;
	}

	public final String getSecType() {
		return secType;
	}

	public final void setSecType(String secType) {
		this.secType = secType;
	}

	public final String getToken() {
		return token;
	}

	public final void setToken(String token) {
		this.token = token;
	}

}
