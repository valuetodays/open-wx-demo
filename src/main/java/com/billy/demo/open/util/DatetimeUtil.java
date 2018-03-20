package com.billy.demo.open.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 日期处理类
 *
 * @author TongWei.Chen
 * @since 2017-04-05 17:38:36
 */
public class DatetimeUtil {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final String YEAR_MOHTH_FORMAT = "yyyy-MM";
    private static Map<String, SimpleDateFormat> dateFormatMap = new ConcurrentHashMap<>();

    /**
     * 获取年月日的毫秒值
     *
     * @param dateTime
     * @return
     */
    public static long getLongDate(final String dateTime) {
        try {
            return getDateFormat(DATE_FORMAT).parse(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 返回Date：yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static Date getDateTime(String dateTime) {
        try {
            return getDateFormat(DATE_TIME_FORMAT).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回Date：yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date getDate(String date) {
        try {
            return getDateFormat(DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回Date：HH:mm:ss
     *
     * @param time
     * @return
     */
    public static Date getTime(String time) {
        try {
            return getDateFormat(TIME_FORMAT).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回字符串：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getDateTimeString(Date date) {
        return getDateFormat(DATE_TIME_FORMAT).format(date);
    }

    /**
     * 返回字符串： yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getDateString(Date date) {
        return getDateFormat(DATE_FORMAT).format(date);
    }

    /**
     * 返回字符串： yyyy-MM
     *
     * @param date
     * @return
     */
    public static String getYearMonth(Date date) {
        return getDateFormat(YEAR_MOHTH_FORMAT).format(date);
    }

    /**
     * 返回字符串： HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String getTimeString(Date time) {
        return getDateFormat(TIME_FORMAT).format(time);
    }

    private static SimpleDateFormat getDateFormat(String parttern) {
        SimpleDateFormat simpleDateFormat = dateFormatMap.get(parttern);
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        } else {
            dateFormatMap.put(parttern, new SimpleDateFormat(parttern));
            return dateFormatMap.get(parttern);
        }
    }

    /**
     * get current date, as integer
     *
     * @return '2017-03-21' --> 20170321
     */
    public static int getCurrentDateAsInt() {
        SimpleDateFormat sdf = getDateFormat(DATE_FORMAT.replace("-", ""));
        return Integer.parseInt(sdf.format(new Date()));
    }


    /**
     * 参考Calendar的add方法规范
     * {@link Calendar#add(int, int)}
     *
     * @param field
     * @param amount
     * @param date
     * @return
     */
    public static Date add(Integer field, Integer amount, Date date) {

        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            // 如果传入时间非空
            calendar.setTime(date);
        }
        calendar.add(field, amount);

        return calendar.getTime();
    }

    /**
     * 输入一个天数和日期，根据当日期进行计算，获得天数之后的日期
     *
     * @param day
     * @param date
     * @return
     */
    public static String getIntervalDate(Date date, Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        Date time = calendar.getTime();
        String dateTimeString = DatetimeUtil.getDateTimeString(time);
        return dateTimeString;
    }

    /**
     * 获得相差天数
     *
     * @param oldDate
     * @param newDate
     * @return
     */
    public static int getIntervalDays(Date oldDate, Date newDate) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(oldDate);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(newDate);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) // 同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
                {
                    timeDistance += 366;
                } else // 不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else // 不同年
        {
            return day2 - day1;
        }
    }
}
