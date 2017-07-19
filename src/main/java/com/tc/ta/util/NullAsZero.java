package com.tc.ta.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NullAsZero {
	static SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
	
	public static Integer nullAsZeroInt(String b) {
		return (b == null || "".equals(b)) ? 0 : Integer.valueOf(b);
	}

	public static Long nullAsZeroLong(String b) {
		return (b == null || "".equals(b)) ? 0 : Long.valueOf(b);
	}

	public static Float nullAsZeroFloat(String b) {
		return (b == null || "".equals(b)) ? 0 : Float.valueOf(b);
	}

	public static Double nullAsZeroDouble(String b) {
		return (b == null || "".equals(b)) ? 0 : Double.valueOf(b);
	}

	public static BigDecimal nullAsZeroBig(String b) {
		return (b == null || "".equals(b)) ? BigDecimal.ZERO
				: new BigDecimal(b);
	}

	public static Date nullAsZeroDate(String b) {
		try {
			return (b == null || "".equals(b)) ? null : sdf.parse(b);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Boolean nullAsZeroBoolean(String b) {
		return (b == null || "".equals(b)) ? false : true;
	}

	public static BigDecimal nullAsZeroBig(BigDecimal b) {
		return b == null ? BigDecimal.ZERO : b;
	}

	public static String nullAsZeroStr(String b) {
		return b == null ? "" : b;
	}

	public static boolean isNotNullBigDecimal(BigDecimal b) {
		return b != null && b.compareTo(BigDecimal.ZERO) != 0;
	}
	
	public static boolean isNotNull(BigDecimal b) {
		return b != null;
	}

	public static boolean isNotNullInteger(Integer b) {
		return b != null && b != 0;
	}
	
	public static boolean isNotNullString(String b) {
		return b != null && !"".equals(b);
	}
}
