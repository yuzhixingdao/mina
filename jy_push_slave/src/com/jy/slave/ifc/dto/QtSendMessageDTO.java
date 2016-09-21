package com.jy.slave.ifc.dto;

public class QtSendMessageDTO {

	private String devicesId;
	private String token;
	private String sessionId;
	private String data;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDevicesId() {
		return devicesId;
	}

	public void setDevicesId(String devicesId) {
		this.devicesId = devicesId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
