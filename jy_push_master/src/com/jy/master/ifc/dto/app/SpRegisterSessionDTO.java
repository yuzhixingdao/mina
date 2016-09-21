package com.jy.master.ifc.dto.app;

/**
 * APP register mina
 * 
 * @author Sxq
 * @date Oct 29, 2015
 * 
 */
public class SpRegisterSessionDTO {

	private String token;
	private String ip;
	private Integer port;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
