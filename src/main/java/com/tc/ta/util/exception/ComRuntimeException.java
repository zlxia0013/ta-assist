package com.tc.ta.util.exception;


import com.tc.ta.util.ReturnCodes;

public class ComRuntimeException extends RuntimeException {
	private static final long	serialVersionUID	= 3443937526999432007L;

	private Integer				errorCode			= ReturnCodes.RUNTIME_EXCEPTION;

	// default construct
	public ComRuntimeException() {
		super();
	}

	public ComRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ComRuntimeException(String message, Throwable cause, Integer errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ComRuntimeException(String message) {
		super(message);
	}

	public ComRuntimeException(String message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ComRuntimeException(Throwable cause) {
		super(cause);
	}

	public ComRuntimeException(Throwable cause, Integer errorCode) {
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
