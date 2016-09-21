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
	 * 平台号： 01-android 02-ios 03-winphone
	 */
	private String pno; // 简：pno
	/**
	 * 渠道号: 00 - 测试 01 - 机锋 02 - 谷歌市场 03 - appstore
	 */
	private String cno; // 简：cno
	/**
	 * 终端id
	 */
	private String tno; // 简：tno
	/**
	 * 数据
	 */
	private T data;
	/**
	 * 用户Id
	 */
	private String uid;
	/**
	 * 会话ID
	 */
	private String sid;
	/**
	 * 用用程序版本号
	 */
	private String vno;
	/**
	 * 应用程序ID
	 */
	private String appid;
	/**
	 * 网络类型
	 */
	private String netType;
	/**
	 * 平台版本号
	 */
	private String pvno;
	/**
	 * 接口服务版本号
	 */
	private Integer apivno;
	/**
	 * 安全类型：默认不加密 00：不加密 01：DES加密
	 */
	private String secType;
	/**
	 * 令牌号
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
