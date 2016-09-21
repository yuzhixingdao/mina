package com.jy.push.entity;

import java.util.Date;

/**
 * JyMinaServicer entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class JyMinaServicer implements java.io.Serializable {

	// Fields

	private String id;
	private String ip;
	private Integer port;
	private String state;
	private Integer sessionNumber;
	private String ifcUrl;
	private String delFlag;
	private String createId;
	private Date createDate;
	private String updateId;
	private Date updateDate;

	// Constructors

	/** default constructor */
	public JyMinaServicer() {
	}

	/** full constructor */
	public JyMinaServicer(String ip, Integer port, String state,
			Integer sessionNumber, String delFlag, String createId,
			Date createDate, String updateId, Date updateDate) {
		this.ip = ip;
		this.port = port;
		this.state = state;
		this.sessionNumber = sessionNumber;
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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getSessionNumber() {
		return this.sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getIfcUrl() {
		return ifcUrl;
	}

	public void setIfcUrl(String ifcUrl) {
		this.ifcUrl = ifcUrl;
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