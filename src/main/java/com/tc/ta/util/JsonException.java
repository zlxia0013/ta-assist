package com.tc.ta.util;

public class JsonException extends Exception {

	final static String NULLTOBEAN = "can not parse null to a javabean";
	final static String NULLTOJAVA = "can not parse null to a java-inside object";
	final static String NULLTOJSON = "can not parse null to a JSON string";

	static String defaultJsonMsg = "解析失败";
	/**
     *
     */
	private static final long serialVersionUID = 1L;

	public JsonException() {
		super(defaultJsonMsg);
	}

	public JsonException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
	}

}