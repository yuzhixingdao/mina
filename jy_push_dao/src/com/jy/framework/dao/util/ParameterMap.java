package com.jy.framework.dao.util;

public class ParameterMap {

	private String key;
	private Class<?> value;

	public ParameterMap(String key, Class<?> value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Class<?> getValue() {
		return value;
	}

	public void setValue(Class<?> value) {
		this.value = value;
	}

}
