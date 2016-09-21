package com.jy.push.entity;

import java.util.Date;

/**
 * JyMessage entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class JyMessage implements java.io.Serializable {

	// Fields

	private String id;
	private String devicesId;
	private String token;
	private String messageContent;
	private String successFlag;
	private Date firstDate;
	private Date sendDate;
	private Date receiveDate;
	private String delFlag;
	private String createId;
	private Date createDate;
	private String updateId;
	private Date updateDate;

	// Constructors

	/** default constructor */
	public JyMessage() {
	}

	/** full constructor */
	public JyMessage(String devicesId, String token, String messageContent,
			String successFlag, Date sendDate, Date receiveDate,
			String delFlag, String createId, Date createDate, String updateId,
			Date updateDate) {
		this.devicesId = devicesId;
		this.token = token;
		this.messageContent = messageContent;
		this.successFlag = successFlag;
		this.sendDate = sendDate;
		this.receiveDate = receiveDate;
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

	public String getMessageContent() {
		return this.messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getSuccessFlag() {
		return this.successFlag;
	}

	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}

	public Date getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getReceiveDate() {
		return this.receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
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