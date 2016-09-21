package com.jy.slave.ifc.dto;

public class SpOnLineMinaSessionDTO {

	/**
	 * 是否在线：1-在线、0-下线
	 */
	private String isOnLine;

	public SpOnLineMinaSessionDTO(String isOnLine) {
		super();
		this.isOnLine = isOnLine;
	}

	public String getIsOnLine() {
		return isOnLine;
	}

	public void setIsOnLine(String isOnLine) {
		this.isOnLine = isOnLine;
	}

}
