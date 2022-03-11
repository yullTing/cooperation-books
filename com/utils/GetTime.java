package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * 获取时间
 * */
public class GetTime {
    /*public static void main(String[] args) {
//        System.out.println(dateTime());
        System.out.println(getDiffExpNow("2021-10-08", "2021-12-09"));
//        System.out.println(getTimeAsID());
    }*/

    /*
    * 获取当前日期：年月日
    * */
    public static String dateTime() {
        String s;
        //创建日期对象
        Date d = new Date();//当前系统时间
        // 使用给定的模式：yyyy年MM月dd日 HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //日期格式化成日期/时间字符串
        s = sdf.format(d);
        return s;
    }

    /*
    * 获取当前精确时间：年月日 时分秒
    * */
    public static String preciseTime() {
        String s;
        //创建日期对象
        Date d = new Date();//当前系统时间
        // 使用给定的模式：yyyy年MM月dd日 HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //日期格式化成日期/时间字符串
        s = sdf.format(d);
        return s;
    }

    /*
    * 使用当前时间的年月日时分秒作为读者唯一编号
    * */
    public static String getTimeAsID(){
        String result;
        //创建日期对象
        Date d = new Date();//当前系统时间
        // 使用给定的模式：yyyy年MM月dd日 HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //日期格式化成日期/时间字符串
        result = sdf.format(d);
        return result;
    }

    /*
    * 获取预计还书时间 = 当前时间 + 最大借书天数
    * */
    public static String NowDaysAdd(String nowDay, int maxDaysReturn) {
        String result;

        String[] split = nowDay.split("-");
        /*for (int i=0; i<split.length; i++){
            System.out.println(i+","+split[i]);
        }*/

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));

        //nowDay往后推maxDaysReturn天
        calendar.add(Calendar.DATE, maxDaysReturn);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        result = year + "-" + month + "-" + date;
        return result;
    }

    /*
    * 获取预计还书时间 和 实际还书时间 的差值：预计还书时间 - 实际还书实际
    * */
    public static int getDiffExpNow(String expectReturn, String nowDay) {
        int result = 0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(nowDay));
            long time1 = cal.getTimeInMillis();

            cal.setTime(sdf.parse(expectReturn));
            long time2 = cal.getTimeInMillis();

            String between_days = String.valueOf((time2 - time1) / (1000 * 3600 * 24));
            result = Integer.parseInt(between_days);
//            long between_days = (time2 - time1) / (1000 * 3600 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
