package com.tc.ta.util.exception;


import com.tc.ta.common.web.ReturnCodes;

public class ComSystemException extends RuntimeException {
	private static final long	serialVersionUID	= 17874544414545L;

	private Integer				errorCode			= ReturnCodes.SYSTEM_EXCEPTION;

	// default construct
	public ComSystemException() {
		super();
	}

	public ComSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public ComSystemException(String message, Throwable cause, Integer errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ComSystemException(String message) {
		super(message);
	}

	public ComSystemException(String message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ComSystemException(Throwable cause) {
		super(cause);
	}

	public ComSystemException(Throwable cause, Integer errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

}