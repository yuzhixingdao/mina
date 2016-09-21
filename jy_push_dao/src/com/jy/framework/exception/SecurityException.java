package com.jy.framework.exception;

public class SecurityException extends BaseException {
	public SecurityException() {
	}

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(Throwable cause) {
		super(cause);
	}

	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}
}
