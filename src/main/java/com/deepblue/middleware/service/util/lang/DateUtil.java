package com.deepblue.middleware.service.util.lang;


import com.alibaba.common.lang.StringUtil;
import org.apache.commons.lang.RandomStringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;




/**
 * ���õ����ڴ�����
 *  
 * @author wang shuai
 */
public final class DateUtil{

    /** yyyyMMdd */
    public final static String SHORT_FORMAT           = "yyyyMMdd";

    /** yyyyMMddHHmmss */
    public final static String LONG_FORMAT            = "yyyyMMddHHmmss";

    /** yyyy-MM-dd */
    public final static String WEB_FORMAT             = "yyyy-MM-dd";

    /** HHmmss */
    public final static String TIME_FORMAT            = "HHmmss";

	/** HHmmssSSS ���뼶�� */
	public final static String TIMESSS_FORMAT = "HHmmssSSS";
	
    /** yyyyMM */
    public final static String MONTH_FORMAT           = "yyyyMM";

    /** yyyy��MM��dd�� */
    public final static String CHINA_FORMAT           = "yyyy��MM��dd��";

    /** yyyy-MM-dd HH:mm:ss */
    public final static String LONG_WEB_FORMAT        = "yyyy-MM-dd HH:mm:ss";

    /** yyyy-MM-dd HH:mm */
    public final static String LONG_WEB_FORMAT_NO_SEC = "yyyy-MM-dd HH:mm";

    /**
     * ���ڶ�������������ַ����������������Ծݴ˷�װ�����ֱ�ݵķ���ֱ��ʹ��
     * 
     * @param date ����ʽ�������ڶ���
     * @param format ����ĸ�ʽ
     * @return ��ʽ�����ַ���
     */
    public static String format(Date date, String format) {
        if (date == null || StringUtil.isBlank(format)) {
            return StringUtil.EMPTY_STRING;
        }

        return new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE).format(date);
    }

    /**
     * ��ʽ����ǰʱ��
     * 
     * @param format ����ĸ�ʽ
     * @return
     */
    public static String formatCurrent(String format) {
        if (StringUtil.isBlank(format)) {
            return StringUtil.EMPTY_STRING;
        }

        return format(new Date(), format);
    }

    /**
     * �����ַ������������ڶ�����������������ڴ˷�װ�����ֱ�ݵķ���ֱ��ʹ��
     * 
     * @param dateStr �����ַ���
     * @param format ����ĸ�ʽ
     * @return ���ڶ���
     * @throws ParseException 
     */
    public static Date parse(String dateStr, String format) throws ParseException {
        if (StringUtil.isBlank(format)) {
            throw new ParseException("format can not be null.", 0);
        }

        if (dateStr == null || dateStr.length() < format.length()) {
            throw new ParseException("date string's length is too small.", 0);
        }

        return new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE).parse(dateStr);
    }

    /**
     * �����ַ�����ʽ�����������������ڴ˷�װ�����ֱ�ݵķ���ֱ��ʹ��
     * 
     * @param dateStr �����ַ���
     * @param formatIn ����������ַ����ĸ�ʽ
     * @param formatOut ��������ַ����ĸ�ʽ
     * @return �Ѿ���ʽ�����ַ���
     * @throws ParseException
     */
    public static String format(String dateStr, String formatIn, String formatOut)
                                                                                  throws ParseException {

        Date date = parse(dateStr, formatIn);
        return format(date, formatOut);
    }

    /**
     * �����ڶ�����<code>yyyyMMdd</code>��ʽ�������ַ���
     * 
     * @param date ����ʽ�������ڶ��� 
     * @return ��ʽ�����ַ���
     */
    public static String formatShort(Date date) {
        return format(date, SHORT_FORMAT);
    }

    /**
     * �������ַ�������<code>yyyyMMdd</code>��ʽ�����и�ʽ��
     * 
     * @param dateStr ����ʽ���������ַ���
     * @param formatIn ����������ַ����ĸ�ʽ 
     * @return ��ʽ�����ַ���
     */
    public static String formatShort(String dateStr, String formatIn) throws ParseException {
        return format(dateStr, formatIn, SHORT_FORMAT);
    }

    /**
     * �����ڶ�����<code>yyyy-MM-dd</code>��ʽ�������ַ���
     * 
     * @param date ����ʽ�������ڶ��� 
     * @return ��ʽ�����ַ���
     */
    public static String formatWeb(Date date) {
        return format(date, WEB_FORMAT);
    }

    /**
     * �������ַ�������<code>yyyy-MM-dd</code>��ʽ�����и�ʽ��
     * 
     * @param dateStr ����ʽ���������ַ���
     * @param formatIn ����������ַ����ĸ�ʽ 
     * @return ��ʽ�����ַ���
     * @throws ParseException 
     */
    public static String formatWeb(String dateStr, String formatIn) throws ParseException {
        return format(dateStr, formatIn, WEB_FORMAT);
    }

    /**
     * �����ڶ�����<code>yyyyMM</code>��ʽ�������ַ���
     * 
     * @param date ����ʽ�������ڶ��� 
     * @return ��ʽ�����ַ���
     */
    public static String formatMonth(Date date) {

        return format(date, MONTH_FORMAT);
    }

    /**
     * �����ڶ�����<code>HHmmss</code>��ʽ�������ַ���
     * 
     * @param date ����ʽ�������ڶ��� 
     * @return ��ʽ�����ַ���
     */
    public static String formatTime(Date date) {
        return format(date, TIME_FORMAT);
    }

    /**
     * ��ȡyyyyMMddHHmmss+nλ�������ʽ��ʱ���
     * 
     * @param n �����λ��
     * @return
     */
    public static String getTimestamp(int n) {
        return formatCurrent(LONG_FORMAT) + RandomStringUtils.randomNumeric(n);
    }

    /**
     * �������ڸ�ʽ������������
     * 
     * @param format ���ڸ�ʽ
     * @return
     */
    public static String getYesterdayDate(String format) {
        return getDateCompareToday(format, -1, 0);
    }

    /**
     * �ѵ���������Ϊ��׼����װ��ʽ���ط������һ�����������
     *
     * @param format ���ڸ�ʽ
     * @param daysAfter �͵��ձ����죬����3����3���-1����1��ǰ
     * @param monthAfter �͵��ձ����£�����2����2�º�-3����3��ǰ
     * @return
     */
    public static String getDateCompareToday(String format, int daysAfter, int monthAfter) {
        Calendar today = Calendar.getInstance();
        if (daysAfter != 0) {
            today.add(Calendar.DATE, daysAfter);
        }
        if (monthAfter != 0) {
            today.add(Calendar.MONTH, monthAfter);
        }
        return format(today.getTime(), format);
    }

    /**
     * �������ڸ�ʽ�������µ�����
     * @param format
     * @return
     */
    public static String getLastMonth(String format) {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, -1);
        return format(today.getTime(), format);
    }
}
