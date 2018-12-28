package com.company.P2018_11_15;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年11月15日
 */
public class TimePractice {
    public static void p1(){
        LocalDate ld = LocalDate.now();
        System.out.println(ld);
    }

    public static void p2(){
        LocalDate ld = LocalDate.of(2018,11,16);
        System.out.println(ld);
        LocalDate ld1 = ld.plus(2,ChronoUnit.DECADES);
        ld=ld.plus(1,ChronoUnit.DAYS);
        System.out.println(ld1);
        System.out.println(ld);
    }

    public static void p3(){
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(1999,11,15);
        MonthDay date1 = MonthDay.of(date.getMonthValue(),date.getDayOfMonth());
        MonthDay now1 = MonthDay.from(now);
        System.out.println("date1:"+date1);
        System.out.println("now1:"+now1);
        System.out.println(date1.equals(now1));
    }

    public static void p4(){
        LocalTime nowTime = LocalTime.now();
        System.out.println(nowTime);
        LocalTime nowTime1 = nowTime.plusHours(2);
        System.out.println(nowTime1);
    }
    public static void p5(){
        System.out.println(System.currentTimeMillis());
        System.out.println(Instant.now());
    }

    public static void main(String[] args) {
        p1();
        p2();
        p3();
        p4();
        p5();
    }
}
