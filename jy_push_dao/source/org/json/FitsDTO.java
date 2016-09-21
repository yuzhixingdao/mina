package org.json;

import java.io.Serializable;

public class FitsDTO implements Serializable {
	private String bz;
	/** 条款列表 */ 

	private String mc;
	
	public FitsDTO(){}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}

}