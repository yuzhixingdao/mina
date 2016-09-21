package com.jy.push.service;

public class PushConfigService {

	private Integer heartbeat;
	private Integer pushPort;

	public Integer getHeartbeat() {
		return heartbeat;
	}

	public void setHeartbeat(Integer heartbeat) {
		this.heartbeat = heartbeat;
	}

	public Integer getPushPort() {
		return pushPort;
	}

	public void setPushPort(Integer pushPort) {
		this.pushPort = pushPort;
	}

}
