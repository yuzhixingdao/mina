package com.jy.framework.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(super.getClass());
	private Throwable _rootCause;

	public BaseException() {
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message);
		if (cause instanceof BaseException)
			this._rootCause = ((BaseException) cause)._rootCause;
		else {
			this._rootCause = cause;
		}
		this.log.error(message, cause);
	}
}
