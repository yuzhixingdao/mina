package com.jy.push.entity;

import java.sql.Timestamp;

/**
 * JyCommunion entity. @author MyEclipse Persistence Tools
 */

public class JyCommunion implements java.io.Serializable {

	// Fields

	private String id;
	private String accessId;
	private String token;
	private String url;
	private String data;
	private String delFlag;
	private String createId;
	private Timestamp createDate;
	private String updateId;
	private Timestamp updateDate;

	// Constructors

	/** default constructor */
	public JyCommunion() {
	}

	/** full constructor */
	public JyCommunion(String accessId, String token, String url, String data,
			String delFlag, String createId, Timestamp createDate,
			String updateId, Timestamp updateDate) {
		this.accessId = accessId;
		this.token = token;
		this.url = url;
		this.data = data;
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

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
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

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getUpdateId() {
		return this.updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

}