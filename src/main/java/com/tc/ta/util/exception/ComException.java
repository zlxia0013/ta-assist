package com.tc.ta.util.exception;


import com.tc.ta.util.ReturnCodes;

public class ComException extends Exception {
	private static final long	serialVersionUID	= 8589455703229910144L;

	private Integer				errorCode			= ReturnCodes.EXCEPTION;

	// default construct
	public ComException() {
		super();
	}

	public ComException(String message, Throwable cause) {
		super(message, cause);
	}

	public ComException(String message, Throwable cause, int errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ComException(String message) {
		super(message);
	}

	public ComException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ComException(Throwable cause) {
		super(cause);
	}

	public ComException(Throwable cause, int errorCode) {
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
