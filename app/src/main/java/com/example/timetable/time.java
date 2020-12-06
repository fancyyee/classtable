package com.example.timetable;


import java.util.Date;

public class time {
    public int week_num;
    public void gettime(){
        long time= new Date().getTime()-28800000L;
        class_table class_table=new class_table();
        class_table.set_time();
        long stert_time=class_table.start_time;
        String time2=Long.toString(((time-stert_time)/604800000L)+1);
        week_num=Integer.parseInt(time2);
    }

}
