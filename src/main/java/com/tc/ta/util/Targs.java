package com.tc.ta.util;

import java.util.ArrayList;


public class Targs {
	// 数据字典下拉选择
	public static String select(String name, String dictKey, String value) {
		return select(name, dictKey, value, false, "");
	}

	public static String select(String name, String dictKey, String value,
			String other) {
		return select(name, dictKey, value, false, other);
	}

	public static String select(String name, String dictKey, String value,
			boolean haveHead, String other) {
		return select(name, dictKey, value, "", haveHead, other);
	}

	public static String select(String name, String dictKey, String value,
			String containValue, boolean haveHead, String other) {
		// 取相应的字典
		ArrayList valueList=null;// = DictSingle.getInstance().getValue(dictKey);
		value = value == null || value == "" ? "" : value;
		if (valueList == null) {
			return "";
		}

		// 生产HTML
		StringBuffer htmlStr = new StringBuffer();
		htmlStr = htmlStr
				.append("<select id='" + name + "' name='" + name + "' ")
				.append((other == null ? "" : other)).append(">");
		if (haveHead) {
			htmlStr = htmlStr.append("<option value='-1'>请选择</option>");
		}
		for (int i = 0; i < valueList.size(); i++) {
			String[] aValue = (String[]) valueList.get(i);
			aValue[0] = aValue[0].trim();
			if (!"".equals(containValue)) {
				if (containValue.indexOf("'" + aValue[0].trim() + "'") > -1) {
					if (value.equals(aValue[0].trim())) {
						htmlStr = htmlStr.append("<option value='")
								.append(aValue[0]).append("' selected>")
								.append(aValue[1]).append("</option>");
					} else {
						htmlStr = htmlStr.append("<option value='")
								.append(aValue[0]).append("'>")
								.append(aValue[1]).append("</option>");
					}
				}
			} else {
				if (value.equals(aValue[0].trim())) {
					htmlStr = htmlStr.append("<option value='")
							.append(aValue[0]).append("' selected>")
							.append(aValue[1]).append("</option>");
				} else {
					htmlStr = htmlStr.append("<option value='")
							.append(aValue[0]).append("'>").append(aValue[1])
							.append("</option>");
				}
			}
		}
		htmlStr = htmlStr.append("</select>");

		return htmlStr.toString();
	}

	/**
	 * 是否可操作的下拉框控件
	 * 
	 * @param name
	 * @param dictKey
	 * @param value
	 * @param haveHead
	 * @param other
	 * @param isDisable
	 * @return
	 */
	public static String select(String name, String dictKey, String value,
			boolean haveHead, String other, boolean isDisable) {
		return select(name, dictKey, value, "", haveHead, other, isDisable);
	}

	public static String select(String name, String dictKey, String value,
			String containValue, boolean haveHead, String other,
			boolean isDisable) {
		// 取相应的字典
		ArrayList valueList = null;//DictSingle.getInstance().getValue(dictKey);
		value = value == null || value == "" ? "" : value;
		if (valueList == null) {
			return "";
		}
		String disable = "";
		if (isDisable) {
			disable = "disabled='disabled'";
		}
		// 生产HTML
		StringBuffer htmlStr = new StringBuffer();
		htmlStr = htmlStr.append("<select name='" + name + "' ")
				.append(disable).append((other == null ? "" : other))
				.append(">");
		if (haveHead) {
			htmlStr = htmlStr.append("<option value='-1'></option>");
		}
		for (int i = 0; i < valueList.size(); i++) {
			String[] aValue = (String[]) valueList.get(i);
			aValue[0] = aValue[0].trim();
			if (!"".equals(containValue)) {
				if (containValue.indexOf("'" + aValue[0].trim() + "'") > -1) {
					if (value.equals(aValue[0].trim())) {
						htmlStr = htmlStr.append("<option value='")
								.append(aValue[0]).append("' selected>")
								.append(aValue[1]).append("</option>");
					} else {
						htmlStr = htmlStr.append("<option value='")
								.append(aValue[0]).append("'>")
								.append(aValue[1]).append("</option>");
					}
				}
			} else {
				if (value.equals(aValue[0].trim())) {
					htmlStr = htmlStr.append("<option value='")
							.append(aValue[0]).append("' selected>")
							.append(aValue[1]).append("</option>");
				} else {
					htmlStr = htmlStr.append("<option value='")
							.append(aValue[0]).append("'>").append(aValue[1])
							.append("</option>");
				}
			}
		}
		htmlStr = htmlStr.append("</select>");

		return htmlStr.toString();
	}



	public static String multipleSelect(String name, String dictKey,
			String value, boolean haveHead, String other) {

		// 取相应的字典
		ArrayList valueList =null;// DictSingle.getInstance().getValue(dictKey);
		if (valueList == null) {
			return "";
		}

		value = value == null || value == "" ? "" : value;

		// 生产HTML
		StringBuffer htmlStr = new StringBuffer();
		htmlStr = htmlStr
				.append("<select multiple=\"multiple\" id=\"")
				.append(name)
				.append("\" style=\"width:130px;height:160px;\" name='" + name
						+ "' ").append("id='" + name + "'").append(">");

		for (int i = 0; i < valueList.size(); i++) {
			String[] aValue = (String[]) valueList.get(i);
			aValue[0] = aValue[0].trim();
			// 如果是右侧 有的则显示
			if ("1".equals(other)) {
				if (value.indexOf(aValue[1].trim()) != -1) {
					htmlStr = htmlStr.append("<option value='")
							.append(aValue[1] + "' >").append(aValue[1])
							.append("</option>");
				}
				// 如果是左侧 有的则不显示
			} else {
				if (value.indexOf(aValue[1].trim()) == -1) {
					htmlStr = htmlStr.append("<option value='")
							.append(aValue[1] + "' >").append(aValue[1])
							.append("</option>");
				}
			}
		}

		htmlStr = htmlStr.append("</select>");
		return htmlStr.toString();

	}

}
