package com.jy.master.ifc.dto.app;

import java.io.Serializable;

/**
 * APP register mina
 * 
 * @author Sxq
 * @date Oct 29, 2015
 * 
 */
public class QtRegisterSessionDTO implements Serializable {

	private static final long serialVersionUID = -2942446362010991465L;

	private String accessKey; // ACCESS KEY
	private String mid;// （设备唯一标识）
	private String userId;
	private String other;

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
}
