package com.jy.master.ifc.dto.pc;

public class QtOnLineMinaSessionDTO {

	private String ifcUrl;
	private String sessionId;

	public QtOnLineMinaSessionDTO() {
		super();
	}

	public QtOnLineMinaSessionDTO(String ifcUrl, String sessionId) {
		super();
		this.ifcUrl = ifcUrl;
		this.sessionId = sessionId;
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
