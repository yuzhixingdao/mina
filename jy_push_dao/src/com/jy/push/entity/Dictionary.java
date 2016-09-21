package com.jy.push.entity;

import java.util.Date;

public class Dictionary {

	private String dictId;
	private String dictName;
	private String dictCode;
	private String dictPaterCode;
	private Integer dictOrder;
	private Date createDate;

	public Dictionary() {
		super();
	}

	public Dictionary(String dictId, String dictName, String dictCode,
			String dictPaterCode, Integer dictOrder, Date createDate) {
		super();
		this.dictId = dictId;
		this.dictName = dictName;
		this.dictCode = dictCode;
		this.dictPaterCode = dictPaterCode;
		this.dictOrder = dictOrder;
		this.createDate = createDate;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictPaterCode() {
		return dictPaterCode;
	}

	public void setDictPaterCode(String dictPaterCode) {
		this.dictPaterCode = dictPaterCode;
	}

	public Integer getDictOrder() {
		return dictOrder;
	}

	public void setDictOrder(Integer dictOrder) {
		this.dictOrder = dictOrder;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
