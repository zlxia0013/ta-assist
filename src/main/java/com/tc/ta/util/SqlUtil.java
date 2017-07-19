package com.tc.ta.util;

import java.util.List;

public class SqlUtil {

	public static String convertListToSqlStr(List<Integer> intList) {
		if (intList == null || intList.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for (Integer i : intList) {
			if (!StringUtil.isEmpty(sb)) {
				sb.append(",");
			}

			sb.append(i);
		}

		return sb.toString();
	}

}
