package com.jy.push.entity;

import java.util.Date;

/**
 * JyMinaSession entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class JyMinaSession implements java.io.Serializable {

	// Fields

	private String id;
	private String sessionId;
	private String sessionState;
	
	private String remoteIp;
	private Integer remotePort;
	private String locaIp;
	private Integer locaPort;
	
	private String devicesId;
	private String token;
	private String deviceNo;
	private String msId;
	private String msIp;
	private Integer msPort;
	private String ifcUrl;
	private Date connectDate;
	private String delFlag;
	private String createId;
	private Date createDate;
	private String updateId;
	private Date updateDate;

	// Constructors

	/** default constructor */
	public JyMinaSession() {
	}

	/** full constructor */
	public JyMinaSession(String sessionId, String sessionState,
			String devicesId, String token, String deviceNo, String msId,
			String msIp, Integer msPort, String delFlag, String createId,
			Date createDate, String updateId, Date updateDate) {
		this.sessionId = sessionId;
		this.sessionState = sessionState;
		this.devicesId = devicesId;
		this.token = token;
		this.deviceNo = deviceNo;
		this.msId = msId;
		this.msIp = msIp;
		this.msPort = msPort;
		this.delFlag = delFlag;
		this.createId = createId;
		this.createDate = createDate;
		this.updateId = updateId;
		this.updateDate = updateDate;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionState() {
		return this.sessionState;
	}

	public void setSessionState(String sessionState) {
		this.sessionState = sessionState;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public Integer getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(Integer remotePort) {
		this.remotePort = remotePort;
	}

	public String getLocaIp() {
		return locaIp;
	}

	public void setLocaIp(String locaIp) {
		this.locaIp = locaIp;
	}

	public Integer getLocaPort() {
		return locaPort;
	}

	public void setLocaPort(Integer locaPort) {
		this.locaPort = locaPort;
	}

	public String getDevicesId() {
		return this.devicesId;
	}

	public void setDevicesId(String devicesId) {
		this.devicesId = devicesId;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getMsId() {
		return this.msId;
	}

	public void setMsId(String msId) {
		this.msId = msId;
	}

	public String getMsIp() {
		return this.msIp;
	}

	public void setMsIp(String msIp) {
		this.msIp = msIp;
	}

	public Integer getMsPort() {
		return this.msPort;
	}

	public void setMsPort(Integer msPort) {
		this.msPort = msPort;
	}

	public String getIfcUrl() {
		return ifcUrl;
	}

	public void setIfcUrl(String ifcUrl) {
		this.ifcUrl = ifcUrl;
	}

	public Date getConnectDate() {
		return connectDate;
	}

	public void setConnectDate(Date connectDate) {
		this.connectDate = connectDate;
	}

	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreateId() {
		return this.createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateId() {
		return this.updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}