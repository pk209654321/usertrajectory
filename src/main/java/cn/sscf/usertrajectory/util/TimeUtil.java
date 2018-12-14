package cn.sscf.usertrajectory.util;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lenovo on 2018/6/28.
 *
 */
public class TimeUtil {
    //将字符串时间转换成long
    public static long formatString2Long(String format,String time){
        SimpleDateFormat s = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = s.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    //将字符串时间转换成date
    public static Date formatString2Date(String format,String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
       // System.out.println(date);
        return date;
    }

    //将date时间转换成字符串
    public static String getDate2String(String format,Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    //1 minute = 60 seconds
    //1 hour = 60 x 60 = 3600
    //1 day = 3600 x 24 = 86400
    //计算时间差-秒,分,时
    public static long printDifference(String startDate, String endDate,String format,int flag ){
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(format);
        Date end=null;
        Date start=null;
        try {
            start = simpleDateFormat.parse(startDate);
            end = simpleDateFormat.parse(endDate);
            long different = end.getTime() - start.getTime();
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hour =secondsInMilli * 3600;
            long day=minutesInMilli * 86400;
            double elapsedMinutes = different / minutesInMilli;
            if(flag==1) {//day
            	return different/day;
            }else if(flag==2) {//小时
            	return different/hour;
            }else if(flag==3) {//分钟
            	return different/minutesInMilli;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1;
    }

    //将timeStamp转换成Data
    public static String timeStamp2DateStr(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }

    public static Date getNextDate(Date date,int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, num); //今天的时间加一天
        date = calendar.getTime();
        return date;
    }

    public static void main(String[] args) {
    	long formatString2Long = TimeUtil.formatString2Long("yyyy-MM-dd", "2018-09-11");
    	long formatString2Long2 = TimeUtil.formatString2Long("yyyy-MM-dd", "2018-09-12");
        System.out.println(formatString2Long2-formatString2Long);
    }
}
