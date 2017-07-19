package com.tc.ta.common.web;

import com.tc.ta.util.ReturnCodes;

/**
 * 
 * JSON模型
 * 
 * 用户后台向前台返回的JSON对象
 * 
 * @author 孙宇
 * 
 */
public class Json implements java.io.Serializable {

	private boolean	success		= false;

	private Integer	returnCode	= ReturnCodes.SUCCESS;

	private String	msg			= "";

	private Object	obj			= null;

	private Object	obj2		= null;

	public Json() {
	}

	public Json(boolean success) {
		this.success = success;
	}

	public Json(Integer returnCode, String msg) {
		this.returnCode = returnCode;
		this.msg = msg;
	}

	public Json(Integer returnCode, String msg, Object obj) {
		this.returnCode = returnCode;
		this.msg = msg;
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Object getObj2() {
		return obj2;
	}

	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}

	public Integer getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}

}
