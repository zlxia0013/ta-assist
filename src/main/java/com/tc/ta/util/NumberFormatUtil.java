package com.tc.ta.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberFormatUtil {
	public static String formatToInteger(BigDecimal value) {
		return formatToDicimal(value.doubleValue(), 0);
	}

	public static String formatToInteger(double value) {
		return formatToDicimal(value, 0);
	}

	public static String formatTo2Dicimal(BigDecimal value) {
		if (value == null) {
			return "0";
		}

		return formatToDicimal(value.doubleValue(), 2);
	}

	public static String formatTo2Dicimal(double value) {
		return formatToDicimal(value, 2);
	}

	public static String formatToDicimal(double value, int maxDecimalCnt) {
		StringBuilder pattern = new StringBuilder("0");

		for (int i = 0; i < maxDecimalCnt; i++) {
			if (i == 0) {
				pattern.append(".");
			}

			pattern.append("#");
		}

		DecimalFormat df = new DecimalFormat(pattern.toString());

		return df.format(value);
	}

	public static String formatToDicimal(BigDecimal value, int maxDecimalCnt) {
		StringBuilder pattern = new StringBuilder("0");

		for (int i = 0; i < maxDecimalCnt; i++) {
			if (i == 0) {
				pattern.append(".");
			}

			pattern.append("#");
		}

		DecimalFormat df = new DecimalFormat(pattern.toString());

		return df.format(value.floatValue());
	}

	public static String format(double value, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(value);
	}

	public static void main(String[] args) {
		System.out.println(formatToDicimal(new BigDecimal("1").floatValue(), 1));
	}
}
