package com.jy.slave.ifc.core;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jy.framework.util.GsonUtil;

public class Response<T> implements Serializable {

	private String responseCode;
	private String errorMessage;
	private String requestCode;
	private String sid;
	private String token;
	private T data;

	public Response<T> fromJson(String jsondata, TypeToken<Response<T>> type) {
		Gson gson = GsonUtil.createGson();
		return gson.fromJson(jsondata, type.getType());
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public final String getRequestCode() {
		return requestCode;
	}

	public final void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}

	public final String getToken() {
		return token;
	}

	public final void setToken(String token) {
		this.token = token;
	}

	public final String getSid() {
		return sid;
	}

	public final void setSid(String sid) {
		this.sid = sid;
	}

	@Override
	public String toString() {
		return "Response [data=" + data + ", errorMessage=" + errorMessage
				+ ", requestCode=" + requestCode + ", responseCode="
				+ responseCode + ", sid=" + sid + ", token=" + token + "]";
	}

}
