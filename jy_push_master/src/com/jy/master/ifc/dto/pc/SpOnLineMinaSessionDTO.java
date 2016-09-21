package com.jy.master.ifc.dto.pc;

public class SpOnLineMinaSessionDTO {

	private String ifcUrl;
	private String sessionId;
	private String isOnlineStatus;

	public String getIsOnlineStatus() {
		return isOnlineStatus;
	}

	public void setIsOnlineStatus(String isOnlineStatus) {
		this.isOnlineStatus = isOnlineStatus;
	}

	public String getIfcUrl() {
		return ifcUrl;
	}

	public void setIfcUrl(String ifcUrl) {
		this.ifcUrl = ifcUrl;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
