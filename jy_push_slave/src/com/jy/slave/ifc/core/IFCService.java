package com.jy.slave.ifc.core;

public interface IFCService<T> {

	public Response<T> execute(String request);
	
}
