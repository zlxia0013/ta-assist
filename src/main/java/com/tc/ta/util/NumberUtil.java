package com.tc.ta.util;

import java.math.BigDecimal;

public class NumberUtil {
	// round
	public static BigDecimal round(BigDecimal v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}

		BigDecimal one = new BigDecimal("1");

		return v.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal round(double v, int scale) {
		BigDecimal b = new BigDecimal(Double.toString(v));

		return round(b, scale);
	}

	// round up
	public static BigDecimal roundUp(BigDecimal v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}

		BigDecimal one = new BigDecimal("1");

		return v.divide(one, scale, BigDecimal.ROUND_UP);
	}

	public static BigDecimal roundUp(double v, int scale) {
		BigDecimal b = new BigDecimal(Double.toString(v));

		return roundUp(b, scale);
	}

	// trunc
	public static BigDecimal trunc(BigDecimal v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}

		BigDecimal one = new BigDecimal("1");

		return v.divide(one, scale, BigDecimal.ROUND_DOWN);
	}

	public static BigDecimal trunc(double v, int scale) {
		BigDecimal b = new BigDecimal(Double.toString(v));

		return trunc(b, scale);
	}

	//
	public static Boolean isPositiveDecimal(String str) {
		if (StringUtil.isEmpty(str)) {
			return false;
		}

		try {
			double d = Double.valueOf(str);

			return d > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static Boolean isNumber(String str) {
		if (StringUtil.isEmpty(str)) {
			return false;
		}

		try {
			Double.valueOf(str);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static boolean isEqual(BigDecimal num1, BigDecimal num2) {
		if (num1 == num2) {
			return true;
		}

		if ((num1 == null && num2 != null) || (num1 != null && num2 == null)) {
			return false;
		} else {
			return num1.compareTo(num2) == 0;
		}
	}

	public static BigDecimal nullAsZero(BigDecimal b) {
		return b == null ? BigDecimal.ZERO : b;
	}

	public static BigDecimal nullAsZero(BigDecimal b, int maxDecimalCnt) {
		BigDecimal v = nullAsZero(b);
		String sv = NumberFormatUtil.formatToDicimal(v, maxDecimalCnt);
		return new BigDecimal(sv);
	}

	public static Integer nullAsZero(Integer b) {
		return b == null ? 0 : b;
	}

	public static void main(String[] args) {
		System.out.println(isEqual(null, null));
		System.out.println(isEqual(new BigDecimal(0.123456), null));
		System.out.println(isEqual(null, new BigDecimal(0.123456)));
		System.out.println(isEqual(new BigDecimal(0.123456), new BigDecimal(0.1234567)));
		System.out.println(isEqual(new BigDecimal(0.123456), new BigDecimal(0.123456)));
	}
}
