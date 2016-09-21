package com.jy.framework.exception;

public class ActionException extends BaseException {

	public ActionException(Throwable cause) {
		super(cause);
	}

	public ActionException(String message) {
		super(message);
	}

	public ActionException(String msg, Throwable ex) {
		super(msg, ex);
	}
}
