package com.jy.framework.exception;

public class ServiceException extends BaseException {
	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String msg, Throwable ex) {
		super(msg, ex);
	}
}
