package cn.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
工具类
构造方法私有
成员方法静态
 */
public class DateUtils {
    private DateUtils(){}
    public static String dateToString(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(date);
        return s;
    }
    public static Date stringToDate(String s,String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = sdf.parse(s);
        return d;
    }
    //date计算天数
    public static int getDayDiffer(Date startDate, Date endDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long startDateTime = dateFormat.parse(dateFormat.format(startDate)).getTime();
        long endDateTime = dateFormat.parse(dateFormat.format(endDate)).getTime();
        return (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24));
    }
}




