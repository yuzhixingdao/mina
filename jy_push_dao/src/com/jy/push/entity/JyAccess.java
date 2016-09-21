package com.jy.push.entity;

import java.util.Date;

/**
 * JyAccess entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class JyAccess implements java.io.Serializable {

	// Fields

	private String id;
	private String accessId;
	private String accessKey;
	private String secretKey;
	private String terminalType;
	private String delFlag;
	private String createId;
	private Date createDate;
	private String updateId;
	private Date updateDate;
	
	
	// Constructors

	/** default constructor */
	public JyAccess() {
	}

	/** full constructor */
	public JyAccess(String accessId, String accessKey, String secretKey,
			String terminalType, String delFlag, String createId,
			Date createDate, String updateId, Date updateDate) {
		this.accessId = accessId;
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.terminalType = terminalType;
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

	public String getAccessId() {
		return this.accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public String getAccessKey() {
		return this.accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return this.secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getTerminalType() {
		return this.terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
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