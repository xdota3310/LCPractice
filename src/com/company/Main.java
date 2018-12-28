package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if(date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if(dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return cal.getTime();
    }

    //获取本周的结束时间
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return weekEndSta;

    }

    public static void main(String[] args) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date today = new Date();
//        Calendar calStart = Calendar.getInstance();
//        calStart.setTime(today);
//        calStart.set(Calendar.HOUR_OF_DAY, 0);
//        calStart.set(Calendar.MINUTE, 0);
//        calStart.set(Calendar.SECOND, 0);
//        calStart.set(Calendar.MILLISECOND, 0);
//        int dayofweek = calStart.get(Calendar.DAY_OF_WEEK);
//        if(dayofweek == 1) {
//            dayofweek += 7;
//        }
//        calStart.add(Calendar.DATE, 2 - dayofweek);
//        Date start = calStart.getTime();
//
//        Calendar calEnd = Calendar.getInstance();
//        calEnd.setTime(start);
//        calEnd.add(Calendar.DAY_OF_WEEK, 6);
//        calEnd.set(Calendar.HOUR_OF_DAY, 23);
//        calEnd.set(Calendar.MINUTE, 59);
//        calEnd.set(Calendar.SECOND, 59);
//        calEnd.set(Calendar.MILLISECOND, 999);
//        Date end = calEnd.getTime();
//
//
//        System.out.println(simpleDateFormat.format(start));
//        System.out.println(simpleDateFormat.format(end));
//        System.out.println(simpleDateFormat.format(today));
        String title ="(会)123";
        System.out.println(title.substring(0,3));
    }
}
