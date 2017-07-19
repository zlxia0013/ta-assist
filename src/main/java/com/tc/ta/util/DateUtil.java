package com.tc.ta.util;


import com.tc.ta.util.exception.ComRuntimeException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String format2YYMMDD(Date date) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat("yyMMdd").format(date);
	}

	public static String format2YYMM(Date date) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat("yyMM").format(date);
	}

	public static String format2ym(Date date) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat("yyyy-MM").format(date);
	}

	public static String format2ym6(Date date) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat("yyyyMM").format(date);
	}

	public static String format2ymd(Date date) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static String format2YYMMDDPointSep(Date date) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat("yyyy.MM.dd").format(date);
	}

	public static String format2ymdPointSep(Date date) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat("yyyy.MM.dd").format(date);
	}

	public static String format2ymdhmsUnderScoreSep(Date date) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(date);
	}

	public static String format2ymdhms(Date date) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String format2ymdhm(Date date) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}

	public static String formatToDetail(Date date) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(date);
	}

	public static String format2Str(Date date, String format) {
		if (date == null) {
			return "";
		}

		return new SimpleDateFormat(format).format(date);
	}

	public static Date parse(String strDate) {
		if (strDate == null) {
			return null;
		}

		try {
			if (strDate.matches("\\d{4}\\d{2}\\d{2}")) {
				return new SimpleDateFormat("yyyyMMdd").parse(strDate);
			}
		} catch (ParseException e) {
		}

		try {
			if (strDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				return new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
			}
		} catch (ParseException e) {
		}

		try {
			if (strDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
				return new SimpleDateFormat("MM/dd/yyyy").parse(strDate);
			}
		} catch (ParseException e) {
		}

		try {
			if (strDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
			}
		} catch (ParseException e) {
		}

		try {
			if (strDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strDate);
			}
		} catch (ParseException e) {
		}

		throw new RuntimeException("日期格式不正确:" + strDate);
	}

	public static Date parseDateTime(String strDateTime) {
		if (strDateTime == null || "".equals(strDateTime)) {
			return null;
		}

		String tmpStrDateTime = strDateTime;

		if (tmpStrDateTime.matches("^\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}$") || tmpStrDateTime.matches("^\\d{2}-\\d{2}\\s+\\d{1}:\\d{2}$")) {
			int month = Integer.valueOf(strDateTime.substring(0, strDateTime.indexOf("-")));
			if (Calendar.getInstance().get(Calendar.MONTH) <= month) {
				tmpStrDateTime = Calendar.getInstance().get(Calendar.YEAR) + "-" + tmpStrDateTime;
			} else {
				tmpStrDateTime = (Calendar.getInstance().get(Calendar.YEAR) + 1) + "-" + tmpStrDateTime;
			}
		}

		try {
			if (tmpStrDateTime.matches("^\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}$")) {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(tmpStrDateTime);
			} else if (tmpStrDateTime.matches("^\\d{4}-\\d{2}-\\d{2}\\s+\\d{1}:\\d{2}$")) {
				return new SimpleDateFormat("yyyy-MM-dd H:mm").parse(tmpStrDateTime);
			}
		} catch (ParseException e) {
		}

		try {
			if (tmpStrDateTime.matches("^\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}$")) {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tmpStrDateTime);
			} else if (tmpStrDateTime.matches("^\\d{4}-\\d{2}-\\d{2}\\s+\\d{1}:\\d{2}:\\d{2}$")) {
				return new SimpleDateFormat("yyyy-MM-dd H:mm:ss").parse(tmpStrDateTime);
			}
		} catch (ParseException e) {
		}

		throw new RuntimeException("日期格式不正确:" + tmpStrDateTime);
	}

	public static Date parseDateFullTime(String strDateTime) {
		if (StringUtil.isEmpty(strDateTime)) {
			return null;
		}

		String tmpStrDateTime = strDateTime;

		if (tmpStrDateTime.matches("\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
			tmpStrDateTime = Calendar.getInstance().get(Calendar.YEAR) + "-" + tmpStrDateTime;
		}

		if (tmpStrDateTime.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
			tmpStrDateTime = tmpStrDateTime + ":00";
		}

		try {
			if (tmpStrDateTime.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tmpStrDateTime);
			}
		} catch (ParseException e) {
		}

		throw new RuntimeException("日期格式不正确:" + tmpStrDateTime);
	}

	public static Date parseMonth(String strDateMonth) {
		if (StringUtil.isEmpty(strDateMonth)) {
			throw new ComRuntimeException("时间不能为空");
		}

		try {
			if (strDateMonth.matches("\\d{4}-\\d{2}")) {
				return new SimpleDateFormat("yyyy-MM").parse(strDateMonth);
			}
		} catch (ParseException e) {
		}

		try {
			if (strDateMonth.matches("\\d{4}-\\d{1}")) {
				return new SimpleDateFormat("yyyy-M").parse(strDateMonth);
			}
		} catch (ParseException e) {
		}

		try {
			if (strDateMonth.matches("\\d{4}年\\d{2}月")) {
				return new SimpleDateFormat("yyyy年MM月").parse(strDateMonth);
			}
		} catch (ParseException e) {
		}

		try {
			if (strDateMonth.matches("\\d{4}年\\d{1}月")) {
				return new SimpleDateFormat("yyyy年M月").parse(strDateMonth);
			}
		} catch (ParseException e) {
		}

		throw new ComRuntimeException("日期格式不正确:" + strDateMonth);
	}

	public static boolean isSameDate(Date dt1, Date dt2) {
		if (dt1 == null || dt2 == null) {
			throw new RuntimeException("日期不能为空.");
		}

		Date tmpDt1 = extractDatePart(dt1);
		Date tmpDt2 = extractDatePart(dt2);

		return tmpDt1.compareTo(tmpDt2) == 0;
	}

	public static Date extractDatePart(Date date) {
		if (date == null) {
			throw new RuntimeException("日期不能为空.");
		}

		return parse(format2ymd(date));
	}

	public static Date tickToTheLastSecondOfTheDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 998);

		return c.getTime();
	}

	public static boolean isTodayDropInDate(Date startDate, Date endDate) {
		Date today = extractDatePart(new Date());

		if (startDate == null) {
			if (endDate == null) {
				return true;
			} else {
				return today.compareTo(tickToTheLastSecondOfTheDay(endDate)) <= 0;
			}
		} else {
			if (endDate == null) {
				return today.compareTo(extractDatePart(startDate)) >= 0;
			} else {
				return today.compareTo(extractDatePart(startDate)) >= 0 && today.compareTo(tickToTheLastSecondOfTheDay(endDate)) <= 0;
			}
		}
	}

	public static boolean isCurrentDatetimeDropIn(Date startDate, Date endDate) {
		Date current = new Date();

		if (startDate == null) {
			if (endDate == null) {
				return true;
			} else {
				return current.compareTo(endDate) <= 0;
			}
		} else {
			if (endDate == null) {
				return current.compareTo(startDate) >= 0;
			} else {
				return current.compareTo(startDate) >= 0 && current.compareTo(endDate) <= 0;
			}
		}
	}

	public static Date addDays(Date date, int days) {
		if (date == null) {
			throw new RuntimeException("日期不能为空.");
		}

		long oneDayMilliSecond = 24 * 60 * 60 * 1000;

		return new Date(date.getTime() + days * oneDayMilliSecond);
	}

	public static Date addMonths(Date date, int num) {
		return add(date, num, Calendar.MONTH);
	}

	public static boolean isValidDate(String birthday) {
		if (birthday == null || birthday.trim().length() <= 0) {
			return false;
		}

		try {
			new SimpleDateFormat("yyyyMMdd").parse(birthday);
			return true;
		} catch (ParseException e) {
		}

		try {
			new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
			return true;
		} catch (ParseException e) {
		}

		try {
			new SimpleDateFormat("MM/dd/yyyy").parse(birthday);
			return true;
		} catch (ParseException e) {
		}

		return false;
	}

	public static int getWeekOfMonth(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int week = (c.get(Calendar.DATE) - 1) / 7 + 1;
		week = week > 4 ? 4 : week;
		return week;
	}

	public static Date getMonthStartDateTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		c.set(Calendar.DATE, 1);

		return extractDatePart(c.getTime());
	}

	public static Date getMonthEndDateTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DATE, 1);

		c.add(Calendar.DATE, -1);

		return tickToTheLastSecondOfTheDay(c.getTime());
	}

	public static String getTimeInterval(Date start, Date end) {
		if (start == null || end == null) {
			return "";
		}

		long totalMs = end.getTime() - start.getTime();

		String sign = "";
		if (totalMs < 0) {
			sign = "-";
			totalMs = Math.abs(totalMs);
		}

		long totalSeconds = totalMs / 1000;
		long totalMin = totalSeconds / 60;

		long day = totalMin / (60 * 24);

		totalMin = totalMin % (60 * 24);
		long hour = totalMin / 60;

		long min = totalMin % 60;

		return sign + day + "天" + hour + "小时" + min + "分";
	}

	public static Date getSettWeekStartDateTime(Date monthDate, int settlementWeek) {
		int startDay = (settlementWeek - 1) * 7 + 1;

		Calendar c = Calendar.getInstance();
		c.setTime(monthDate);
		c.set(Calendar.DATE, startDay);

		return extractDatePart(c.getTime());
	}

	public static Date getSettWeekEndDateTime(Date monthDate, int settlementWeek) {
		Date dt;

		if (settlementWeek <= 3) {
			int endDay = settlementWeek * 7;
			Calendar c = Calendar.getInstance();
			c.setTime(monthDate);
			c.set(Calendar.DATE, endDay);
			dt = tickToTheLastSecondOfTheDay(c.getTime());
		} else {
			dt = getMonthEndDateTime(monthDate);
		}

		return dt;
	}

	public static Date addHours(Date date, BigDecimal hours) {
		if (date == null) {
			throw new RuntimeException("日期不能为空.");
		}

		// 转为秒
		int seconds = hours.multiply(new BigDecimal(3600)).intValue();

		Date dt = add(date, seconds, Calendar.SECOND);

		return dt;
	}

	public static Date add(Date date, int num, int field) {
		if (date == null) {
			throw new RuntimeException("日期不能为空.");
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, num);

		return c.getTime();
	}

	public static BigDecimal calcWorkHours(Date startTime, Date endTime) {
		int halfHourCount = calcHalfHourCount(startTime, endTime);
		BigDecimal workHours = BigDecimal.valueOf(halfHourCount).divide(new BigDecimal("2"), 1, BigDecimal.ROUND_HALF_UP);
		return workHours;
	}

	public static int calcHalfHourCount(Date startTime, Date endTime) {
		// 半小时的秒数
		final int secondsPerHalfHour = 1800;
		// 半小时的秒数
		final int secondsPerHour = secondsPerHalfHour * 2;
		// 10分钟的秒数, 超过10分钟按半小时算
		final int secondsPer10Min = 600;

		int totalSeconds = (int) (endTime.getTime() - startTime.getTime()) / 1000;

		int totalHourCount = totalSeconds / secondsPerHour;
		int totalHalfHourCount = totalHourCount * 2;

		int leftSeconds = totalSeconds - totalHourCount * secondsPerHour;
		if (leftSeconds > secondsPer10Min) {
			totalHalfHourCount++;
		}

		if (leftSeconds > secondsPerHalfHour) {
			totalHalfHourCount++;
		}

		return totalHalfHourCount;
	}

	public static Date addHourMin(Date date, int hours, int mins) {
		if (date == null) {
			throw new RuntimeException("日期不能为空.");
		}

		Date d1 = add(date, hours, Calendar.HOUR_OF_DAY);

		Date d2 = add(d1, mins, Calendar.MINUTE);

		return d2;
	}

	public static Date setHourMin(Date date, String hourMin) {
		if (date == null) {
			throw new RuntimeException("日期不能为空.");
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int hour = Integer.valueOf(hourMin.split(":")[0]);
		int min = Integer.valueOf(hourMin.split(":")[1]);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);

		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * 计算两个日期相隔天数: 不考虑时分秒
	 */
	public static int diffDate(Date d1, Date d2) {
		try {
			long tmp = extractDatePart(d1).getTime() - extractDatePart(d2).getTime();
			if (tmp < 0) {
				tmp = -tmp;
			}

			int diff = (int) (tmp / (24 * 60 * 60 * 1000));

			return diff;
		} catch (Exception e) {
			// e.printStackTrace();
			return -1;
		}

	}

	/**
	 * 计算两个日期相隔小时分秒数
	 */
	public static BigDecimal diffHours(Date dt1, Date dt2) {
		return new BigDecimal(dt2.getTime() - dt1.getTime()).divide(new BigDecimal(1000 * 60 * 60), 2, BigDecimal.ROUND_HALF_UP);
	}

	public static void main(String[] args) {
		System.out.println(diffHours(parseDateFullTime("2015-08-09 12:00:00"), parseDateFullTime("2015-08-09 16:30:00")));
	}
}
