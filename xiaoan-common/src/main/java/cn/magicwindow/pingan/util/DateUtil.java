package cn.magicwindow.pingan.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/**
 * Created by iceman on 16/2/23.
 * 日期操作
 */
public class DateUtil {
    private static final String[] weekDayStrings = {"日", "一", "二", "三", "四", "五", "六"};

    public static final String DATETIME_PATTERN_1_1 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATETIME_PATTERN_2_1 = "yyyy-MM-dd HH:mm";
    public static final String DATETIME_PATTERN_2_2 = "yyyy/MM/dd HH:mm";
    public static final String DATETIME_PATTERN_2_3 = "yyyy年MM月dd日 HH:mm";
    public static final String DATETIME_PATTERN_3_1 = "yyyy-MM-dd";
    public static final String DATETIME_PATTERN_3_2 = "yyyy/MM/dd";
    public static final String DATETIME_PATTERN_3_3 = "yyyy年MM月dd日";
    public static final String DATETIME_PATTERN_3_4 = "yyyyMMdd";
    public static final String DATETIME_PATTERN_4_1 = "yyyy";
    public static final String DATETIME_PATTERN_5_1 = "MM-dd";
    public static final String DATETIME_PATTERN_5_2 = "yyyy-MM";
    public static final String DATETIME_PATTERN_6_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_PATTERN_6_2 = "HH:mm";
    public static final String DATETIME_PATTERN_6_3 = "HH时mm分";


    /***
     * 一种格式的时间字符串转化成另外一种格式的时间字符串
     *
     * @param datetime    时间字符串
     * @param pattern     当前时间字符串的格式
     * @param pattern_new 要转成的新的时间字符串的格式
     * @return 新格式的时间字符串
     */
    public static String getFormatTime(String datetime, String pattern, String pattern_new) {
        if (datetime != null && datetime.trim().length() > 0) {
            try {
                Date date = new SimpleDateFormat(pattern).parse(datetime);
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern_new);
                return dateFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /***
     * 时间转成指定格式的字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getFormatTime(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /***
     * 指定格式的时间字符串转成日期对象
     *
     * @param datetime
     * @param pattern
     * @return
     */
    public static Date getFormatTime(String datetime, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        try {
            return sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取刷新时间
     *
     * @param lastSaveTime 上次刷新时间（毫秒数）
     * @param currentTime  当前时间（毫秒数）
     */
    public static String getRefreshTime(Long lastSaveTime, Long currentTime) {
        Long time = currentTime - lastSaveTime;
        if (time < 60 * (1000 * 60)) { //一小时之内
            return "刚刚刷新";
        } else if (time < 24 * (60 * (1000 * 60))) {//24小时之内
            return time / (60 * (1000 * 60)) + "小时前刷新";
        }
        return time / (24 * (60 * (1000 * 60))) + "天前刷新";
    }

    /**
     * 根据当前日期往前面推data天
     *
     * @param format 返回的日期格式
     * @param data   天数
     */
    public static String getTime(String format, int data) {
        SimpleDateFormat mFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, data);
        return mFormat.format(calendar.getTime());
    }

    /**
     * 根据当前日期往前面推data月
     *
     * @param format 返回的日期格式
     * @param data   月数
     */
    public static String getTimeMonth(String format, int data) {
        SimpleDateFormat mFormat = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, data);
        return mFormat.format(calendar.getTime());
    }

    /**
     * 根据出生日期计算年龄
     *
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static int getAge(Date birthDay) {
        //获取当前系统时间
        Calendar cal = Calendar.getInstance();
        //如果出生日期大于当前时间，则抛出异常
        if (cal.before(birthDay)) {
            return -1;
        }
        //取出系统当前时间的年、月、日部分
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        //将日期设置为出生日期
        cal.setTime(birthDay);
        //取出出生日期的年、月、日部分
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //当前年份与出生年份相减，初步计算年龄搜索
        int age = yearNow - yearBirth;
        //当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
        if (monthNow <= monthBirth) {
            //如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为pattern</p>
     *
     * @param millis  毫秒时间戳
     * @param pattern 时间格式
     * @return 时间字符串
     */
    public static String millis2String(long millis, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date(millis));
    }

    /**
     * 获取任意一段时间内的简单日历,主要包含日期和星期对应关系
     *
     * @param startDay 起始日期
     * @param endDay   结束日期
     * @return 二维数组, <br>星期/日期
     */
    public static String[][] getWeekDays(Calendar startDay, Calendar endDay) {

        ArrayList<String[]> weekDays = new ArrayList<>();

        Calendar tempStart = (Calendar) startDay.clone();
        while (endDay.compareTo(tempStart) == 1) {
            String[] arr = new String[2];
            arr[0] = weekDayStrings[tempStart.get(Calendar.DAY_OF_WEEK) - 1];
            arr[1] = String.valueOf(tempStart.get(Calendar.DAY_OF_MONTH));
            weekDays.add(arr);
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return (weekDays.toArray(new String[0][0]));


    }

    /**
     * 获取星期几
     *
     * @return
     */
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    public static String getWeekOfDate(Date dt, Calendar calendar) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        calendar.setTime(dt);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    public static String getCurrentTime(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    /**
     * 将字符串转为时间戳
     *
     * @param user_time "yyyy年MM月dd日 HH:mm:ss"
     * @return
     */
    public static String getTimestamp(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    /**
     * 将字符串转为时间戳
     *
     * @param user_time "2010年12月08日11时17分00秒"
     * @return
     */
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    /**
     * 将yyyy年MM月dd日格式时间转换成毫秒值
     *
     * @param time
     * @return
     */
    public static long getLongTime(String time) {
        long l = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN_3_3);
        Date d;
        try {
            d = sdf.parse(time);
            l = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 将毫秒值转换成yyyy-MM-dd日格式
     *
     * @param ms
     * @return
     */
    public static String getStringTime(long ms) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_PATTERN_3_1);
        Date curDate = new Date(ms);//获取当前时间
        return formatter.format(curDate);
    }

    public static String getStringTime(long ms,String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date curDate = new Date(ms);//获取当前时间
        return formatter.format(curDate);
    }

    /**
     * 将yyyy年MM月dd 格式时间转换成毫秒值
     *
     * @param time
     * @return
     */
    public static long parseLongTime(String time) {
        long l = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN_3_3);
        Date d;
        try {
            d = sdf.parse(time);
            l = d.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 将yyyy-MM-dd 格式时间转换成Date
     *
     * @param time
     * @return
     */
    public static Date parseDateTime(String time) {
        long l = 0;
        if (!StringUtils.isEmpty(time)) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN_3_1);
            Date d;
            try {
                d = sdf.parse(time);
                l = d.getTime();

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new Date(l);
    }


    public static Date parseDateTime(String time, String pattern) {
        long l = 0;
        if (!StringUtils.isEmpty(time)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date d;
            try {
                d = sdf.parse(time);
                l = d.getTime();

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new Date(l);
    }

    /**
     * 将毫秒值转换成yyyy-MM-dd hh:mm:ss 格式
     *
     * @param ms
     * @return
     */
    public static String parseStrTime(long ms) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_PATTERN_6_1);
        Date curDate = new Date(ms);//获取当前时间
        return formatter.format(curDate);
    }

    public static int getMonthDays(int year, int month) {
        if (month > 12) {
            month = 1;
            year += 1;
        } else if (month < 1) {
            month = 12;
            year -= 1;
        }
        int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 0;

        if (isLeapYear(year)) {
            arr[1] = 29; // 闰年2月29天
        }

        try {
            days = arr[month - 1];
        } catch (Exception e) {
            e.getStackTrace();
        }

        return days;
    }

    /**
     * 是否为闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }


    /**
     * 根据年份和月份获取日期数组，1、2、3...
     *
     * @param year
     * @param month
     * @return
     */
    public static List<String> getMonthDaysArray(int year, int month) {
        List<String> dayList = new ArrayList<String>();
        int days = DateUtil.getMonthDays(year, month);
        for (int i = 1; i <= days; i++) {
            dayList.add(i + "");
        }
        return dayList;
    }


    /**
     * 获取当前系统时间的年份
     *
     * @return
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前系统时间的月份
     *
     * @return
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前系统时间的月份的第几天
     *
     * @return
     */
    public static int getCurrentMonthDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前系统时间的小时数
     *
     * @return
     */
    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前系统时间的分钟数
     *
     * @return
     */
    public static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    /**
     * 获取当前系统时间的秒数
     *
     * @return
     */
    public static int getSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    /**
     * 根据系统默认时区，获取当前时间与time的天数差
     *
     * @param time
     * @return　等于０表示今天，大于０表示今天之前
     */
    public static long getDaySpan(long time) {
        // 系统默认时区，ms
        int tiemzone = TimeZone.getDefault().getRawOffset();
        // １天＝24*60*60*1000ms
        return (System.currentTimeMillis() + tiemzone) / 86400000
                - (time + tiemzone) / 86400000;
    }

    public static boolean isToday(long time) {
        return getDaySpan(time) == 0;
    }

    public static boolean isYestoday(long time) {
        return getDaySpan(time) == 1;
    }

    /**
     * @return 返回当前时间，yyyy-MM-dd HH-mm-ss
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd HH-mm-ss");
    }

    public static String getDate(String format) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }


    /**
     * Date转换成String:yyyy-MM
     *
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN_5_2);
        return sdf.format(date);
    }

    /**
     * Date转换成String:yyyy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String getDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getHzMonth(String month) {
        Map<String, String> map = new HashMap<>();
        map.put("01", "1月");
        map.put("02", "2月");
        map.put("03", "3月");
        map.put("04", "4月");
        map.put("05", "5月");
        map.put("06", "6月");
        map.put("07", "7月");
        map.put("08", "8月");
        map.put("09", "9月");
        map.put("10", "10月");
        map.put("11", "11月");
        map.put("12", "12月");
        Set<Map.Entry<String, String>> seting = map.entrySet();
        for (java.util.Map.Entry<String, String> entry : seting) {
            if (month.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "";
    }

    private static String[][] constellations = {{"摩羯座", "水瓶座"}, {"水瓶座", "双鱼座"}, {"双鱼座", "白羊座"}, {"白羊座", "金牛座"}, {"金牛座", "双子座"}, {"双子座", "巨蟹座"}, {"巨蟹座", "狮子座"},
            {"狮子座", "处女座"}, {"处女座", "天秤座"}, {"天秤座", "天蝎座"}, {"天蝎座", "射手座"}, {"射手座", "摩羯座"}};
    //星座分割时间
    private static int[] date = {20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};

    //星座生成 传进是日期格式为: yyyy-mm-dd
    public static String getConstellations(String birthday) {
        String[] data = birthday.split("-");
        int day = date[Integer.parseInt(data[1]) - 1];
        String[] cl1 = constellations[Integer.parseInt(data[1]) - 1];
        if (Integer.parseInt(data[2]) >= day) {
            return cl1[1];
        } else {
            return cl1[0];
        }
    }

}
