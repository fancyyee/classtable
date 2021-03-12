package com.example.timetable;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class time {
    public int week_num;
    public void gettime(long start_time){
        long time= new Date().getTime()-28800000L;
        String time2=Long.toString(((time-start_time)/604800000L)+1);
        week_num=Integer.parseInt(time2);
    }
    public long date_to_unixtime(int year,int month,int day){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        //格式化月份日期为xx的形式
        String beforeMonth="-";
        String beforeDay="-";
        if(month<10){
            beforeMonth=beforeMonth+"0";
        }
        if(day<10){
            beforeDay=beforeDay+"0";
        }

        String dateString=year +beforeMonth+ month +beforeDay+ day;
        try {
            date = simpleDateFormat.parse("2021-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time=date.getTime();
        return time;
    }

}
