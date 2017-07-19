package com.tc.ta.util;

import com.tc.ta.util.exception.ComRuntimeException;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

	public static String join(List<String> strs, String sep) {
		String result = "";
		for (String str : strs) {
			if (!StringUtil.isEmpty(result)) {
				result = result + sep;
			}
			result = result + str;
		}
		return result;
	}

	public static String trim(String srcStr, String trimedStr) {
		if (srcStr == null) {
			return null;
		}

		if (trimedStr == null) {
			return srcStr;
		}

		String tmpStr = srcStr;

		while (tmpStr.startsWith(trimedStr)) {
			tmpStr = tmpStr.substring(trimedStr.length());
		}

		while (endsWith(tmpStr, trimedStr)) {
			tmpStr = tmpStr.substring(0, tmpStr.lastIndexOf(trimedStr));
		}

		return tmpStr;
	}

	public static boolean endsWith(String str, String end) {
		if (str == null || str.length() == 0 || end == null || end.length() == 0) {
			return false;
		}

		if (end.length() > str.length()) {
			return false;
		}

		String endOfStr = str.substring(str.length() - end.length());

		return endOfStr.equals(end);
	}

	public static boolean endsWithIgnoreCase(String str, String end) {
		if (str == null || str.length() == 0 || end == null || end.length() == 0) {
			return false;
		}

		if (end.length() > str.length()) {
			return false;
		}

		String endOfStr = str.substring(str.length() - end.length());

		return endOfStr.equalsIgnoreCase(end);
	}

	public static boolean isEqual(String str1, String str2) {
		if (str1 == str2) {
			return true;
		}

		if ((str1 == null && str2 != null) || (str1 != null && str2 == null)) {
			return false;
		} else {
			return str1.equals(str2);
		}
	}

	public static boolean isValidAppVersion(String ver) {
		if (isEmpty(ver)) {
			return false;
		}

		String check = "^[0-9]+\\.[0-9]+\\.[0-9]+$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(ver);
		return matcher.matches();
	}

	public static boolean isValidEmail(String email) {
		if (isEmpty(email)) {
			return false;
		}
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidCellPhoneNumber(String phoneNumber) {
		String check = "^\\d{11}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(phoneNumber);
		return matcher.matches();
	}

	public static boolean isInteger(String str) {
		try {
			Integer.valueOf(str);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static boolean isEmpty(StringBuilder str) {
		return str == null || isEmpty(str.toString());
	}

	public static String paddingLeft0(Integer num, Integer len) {
		if (num == null || len == null) {
			return "";
		}

		StringBuilder str = new StringBuilder(num + "");

		while (str.length() < len) {
			str.insert(0, "0");
		}

		return str.toString();
	}

	public static String nullAsEmpty(String str) {
		return str == null ? "" : str;
	}

	public static String[] split(String str, String reg) {
		if (StringUtil.isEmpty(str)) {
			return new String[0];
		}

		return str.split(reg);
	}

	public static String genUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static String replaceAll(String src, String regex, String replacement, boolean isRecursive) {
		String result = src;

		if (isRecursive) {
			if (replacement.indexOf(regex) >= 0) {
				throw new ComRuntimeException("replace string error. replacement[" + replacement + "] contains regex[" + regex
						+ "], this will cause infinitely loop.");
			}

			while (true) {
				int idx = result.indexOf(regex);

				if (idx < 0) {
					break;
				}

				result = result.substring(0, idx) + replacement + result.substring(idx + regex.length());
			}
		} else {
			int startIdx = 0;

			while (true) {
				int idx = result.indexOf(regex, startIdx);

				if (idx < 0) {
					break;
				}

				result = result.substring(0, idx) + replacement + result.substring(idx + regex.length());
				startIdx = idx + replacement.length();
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public static String convertRequestParamsToStr(HttpServletRequest request) {
		// 获取参数
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = (Map<String, String[]>) request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = valueStr + values[i];
				if (i < values.length - 1) {
					valueStr = valueStr + ",";
				}
				// valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}

		// Pay Return Msg
		StringBuilder sbMsg = new StringBuilder();
		for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String value = params.get(name);
			sbMsg.append(name).append(": ").append(value).append("^");
		}

		return sbMsg.toString();
	}
}
