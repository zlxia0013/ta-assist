package com.tc.ta.util;

import com.tc.ta.util.exception.ComRuntimeException;

import java.math.BigDecimal;
import java.util.Date;


public class ValidateUtil {
    public static boolean isCorrectInteger(Integer i) {
        if (i != null && i > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void assertPosInt(Integer v, String msg) {
        if (NumberUtil.nullAsZero(v) <= 0) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertPosIntArr(String[] strArr, String msg) {
        for (String str : strArr) {
            try {
                int i = Integer.valueOf(str.trim());
                if (i <= 0) {
                    throw new ComRuntimeException(msg);
                }
            } catch (Exception e) {
                throw new ComRuntimeException(msg);
            }
        }
    }

    public static void assertNotNegDecimalArr(String[] strArr, String msg) {
        for (String str : strArr) {
            try {
                BigDecimal b = new BigDecimal(str.trim());
                if (b.compareTo(BigDecimal.ZERO) < 0) {
                    throw new ComRuntimeException(msg);
                }
            } catch (Exception e) {
                throw new ComRuntimeException(msg);
            }
        }
    }

    public static void assertNotNeg(BigDecimal v, String msg) {
        if (v == null || v.compareTo(BigDecimal.ZERO) < 0) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertPosDecimalArr(String[] strArr, String msg) {
        for (String str : strArr) {
            try {
                BigDecimal b = new BigDecimal(str.trim());
                if (b.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new ComRuntimeException(msg);
                }
            } catch (Exception e) {
                throw new ComRuntimeException(msg);
            }
        }
    }

    public static void assertLength(Object[] objArr, int len, String msg) {
        if (objArr == null || objArr.length != len) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertEqual(Integer v1, Integer v2, String msg) {
        if (v1 == null || v2 == null || v1.intValue() != v2.intValue()) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertEqual(BigDecimal v1, BigDecimal v2, String msg) {
        if (v1 == null || v2 == null || v1.compareTo(v2) != 0) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertTrue(boolean b, String msg) {
        if (!b) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertFalse(boolean b, String msg) {
        if (b) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertNotEmpty(String v, String msg) {
        if (StringUtil.isEmpty(v)) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertPhoneNum(String v, String msg) {
        if (!StringUtil.isValidCellPhoneNumber(v)) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertNotGreaterThanLen(String v, int len, String msg) {
        if (!StringUtil.isEmpty(v) && v.length() > len) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertInRange(String v, String range, String msg) {
        boolean b = false;

        String[] rangeArr = range.split(",");
        for (String str : rangeArr) {
            if (str.equals(v)) {
                b = true;
                break;
            }
        }

        if (!b) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertInRange(String[] arr, String range, String msg) {
        for (String str : arr) {
            assertInRange(str, range, msg);
        }
    }

    public static void assertPosDecimal(BigDecimal v, String msg) {
        if (NumberUtil.nullAsZero(v).compareTo(BigDecimal.ZERO) <= 0) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertNotNull(Object o, String msg) {
        if (o == null) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void assertAsend(Date dt1, Date dt2, String msg) {
        if (dt1.after(dt2)) {
            throw new ComRuntimeException(msg);
        }
    }

    public static void main(String[] args) {
        assertPosInt(1, "1");
        assertPosInt(2, "2");
        assertPosInt(0, "0");
    }
}
