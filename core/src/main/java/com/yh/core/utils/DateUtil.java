package com.yh.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: 闫昊
 * @date: 2018/7/24
 * @function: 时间、日期格式工具类
 */
public class DateUtil {

    private static class DateTimeHolder {
        private static final SimpleDateFormat FORMAT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    private static class DateOnlyHolder {
        private static final SimpleDateFormat FORMAT_DATE_ONLY = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    private static class TimeOnlyHolder {
        private static final SimpleDateFormat FORMAT_TIME_ONLY = new SimpleDateFormat("HH:mm:ss");
    }

    public static String formatDataTime(long date) {
        return DateTimeHolder.FORMAT_DATE_TIME.format(new Date(date));
    }

    public static String formatDate(long date) {
        return DateOnlyHolder.FORMAT_DATE_ONLY.format(new Date(date));
    }

    public static String formatTime(long date) {
        return TimeOnlyHolder.FORMAT_TIME_ONLY.format(new Date(date));
    }

    public static String formatDateCustom(String beginDate, String format) {
        return new SimpleDateFormat(format).format(new Date(Long.parseLong(beginDate)));
    }

    public static String formatDateCustom(Date beginDate, String format) {
        return new SimpleDateFormat(format).format(beginDate);
    }

    public static Date string2Date(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        Date date = null;
        if (s == null || s.length() < 6) {
            return null;
        }
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
    }

    public static String getDateTime() {
        return DateTimeHolder.FORMAT_DATE_TIME.format(System.currentTimeMillis());
    }

    public static String getDateTime(String format) {
        return new SimpleDateFormat(format).format(System.currentTimeMillis());
    }

    public static long subtractDate(Date dateStart, Date dateEnd) {
        return dateEnd.getTime() - dateStart.getTime();
    }

    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static int getWeekOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return week - 1;
    }

    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1) {
            day = 7;
        } else {
            day = day - 1;
        }
        return day;
    }
}
