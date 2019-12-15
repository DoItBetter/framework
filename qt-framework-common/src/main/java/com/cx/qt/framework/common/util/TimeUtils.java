package com.cx.qt.framework.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class TimeUtils {
    /**
     * 时间转换为时间戳
     * @param time
     * @return
     */
    public static long dateToTimestamp(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(time);
            long ts = date.getTime()/1000;
            return ts;
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 时间戳转换成时间
     * @param time
     * @return
     */
    public static String timestampToDate(long time) {
        if (time < 10000000000L) {
            time = time * 1000;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(time))));
        return sd;
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static long nowData() {
    long data = System.currentTimeMillis();
        return data;
    }

    /**
     * 获取当前时间(精确到毫秒)
     * @return
     */
    public static String nowMsTime() {
        Date currentTime = new Date();// 获取当前时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:S");// 格式化时间
        String dateString = formatter.format(currentTime);// 转换为字符串
        return dateString;
    }

    /**
     * 获取当前时间（精确到秒）
     * @return
     */
    public static String nowTime() {
        Date currentTime = new Date();// 获取当前时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 格式化时间
        String dateString = formatter.format(currentTime);// 转换为字符串
        return dateString;
    }
    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2) //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0) //闰年
                {
                    timeDistance += 366;
                }
                else //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else //不同年
        {
            return day2-day1;
        }
    }

    /**
     * 求一组时间列表中最高连续天数
     * @param dateList
     * @return
     */
    public static int maxContinueDays(List<Date> dateList)
    {
        /** 先升序排列 **/
        Collections.sort(dateList);
        /** 统一格式 **/
        List<Date> mcDays = new ArrayList<>();
        for (Date date : dateList) {
            Calendar calendarTo = Calendar.getInstance();
            calendarTo.setTime(date);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(0);
            c.set(calendarTo.get(Calendar.YEAR), calendarTo.get(Calendar.MONTH),
                    calendarTo.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            mcDays.add(c.getTime());
        }

        List<Integer> cDaysList = new ArrayList<>();
        int count = 0;
        int max = 0;
        cDaysList.add(count);
        if(mcDays.size() != 0) {
            count = 1 ;
            for (int i = mcDays.size() - 1; i > 0; i--) {
                if ((mcDays.get(i).getTime() - mcDays.get(i - 1).getTime()) / (1000 * 60 * 60 * 24) == 1) {
                    count++;
                    cDaysList.add(count);                             /** 将每次循环出来的连续数放到list中 **/
                } else {
                    count = 1;
                }
            }
            max = Collections.max(cDaysList);    /** 求list中的最大值 **/
        }
        return max;
    }
    /**
     * 求一组时间列表中当前连续的天数
     * @param dateList
     * @return
     */
    public static int todayContinueDays(List<Date> dateList)
    {
        /** 先升序排列 **/
        Collections.sort(dateList);
        /** 统一格式 **/
        List<Date> tcDays = new ArrayList<>();
        for (Date date : dateList) {
            Calendar calendarTo = Calendar.getInstance();
            calendarTo.setTime(date);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(0);
            c.set(calendarTo.get(Calendar.YEAR), calendarTo.get(Calendar.MONTH),
                    calendarTo.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            tcDays.add(c.getTime());
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(Calendar.getInstance().getTime());
        int n = 0;
        if(tcDays.size() != 0) {
            Date first = tcDays.get(tcDays.size() - 1);
            try {
                if (format.parse(today).compareTo(first) == 0) {
                    n = 1;
                    for (int m = tcDays.size() - 1; m > 0; m--) {
                        if ((tcDays.get(m).getTime() - tcDays.get(m - 1).getTime()) / (1000 * 60 * 60 * 24) == 1) {
                            n++;
                        } else {
                            break;
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return n;
    }
}
