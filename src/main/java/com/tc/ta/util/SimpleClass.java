package com.tc.ta.util;

public enum SimpleClass {
	DOUBLE("Double"), FLOAT("Float"), STRING("String"), LONG("Long"), CHAR(
			"Char"), INTEGER("Integer");
	private final String classType;

	private SimpleClass(String classType) {
		this.classType = classType;
	}

	public String getClassType() {
		return classType;
	}

	public static Boolean isSimpleClass(Class c) {
		String className = c.getSimpleName();
		for (SimpleClass sc : SimpleClass.values()) {
			if (sc.getClassType().equals(className))
				return true;
		}
		return false;
	}
}