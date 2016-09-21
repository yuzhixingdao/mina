package com.jy.master.ifc.core;

public interface IFCService<T> {

	public Response<T> execute(String request);
	
}
