package com.example.timetable;

import java.io.File;
import java.text.SimpleDateFormat;

public class class_table {
    long start_time;//学期开始的unix时间
    String[] title=new String[76];//课标题
    int[] start_week=new int[76];//课的开始周数
    int[] over_week=new int[76];//课的结束周数
    String[] posion=new String[76];//上课地点
    String[] teacher=new String[76];//授课教师
    public String read_table(String path){
        String place=path+"/config.txt";
        //先等待写的更新
        String response="";
        return response;
    }
    public int set_class(){
        int response=0;
        //0表示初始状态意为出现bug
        //1表示初始化成功
        //2表示导入成功
        //3表示导入失败，出现bug
        //4表示读取失败
        File file =new File("/storage/emulated/0/课表/课表.xls");
        if(!file.exists()){
            try{
                excel_deal excel_deal=new excel_deal();
                excel_deal.createEmptyExcel("/storage/emulated/0/课表/");
                response=1;
            }
            catch (Exception e){
                new log_out().log_out(e);
            }
        }
        else {
            try {
                String[][] excel = new excel_deal().readExcel("/storage/emulated/0/课表/课表.xls");//内容是time,title,start_week,over_week,posion,teacher
                //处理时间
                start_time = new time().date_to_unixtime(
                        Integer.parseInt(excel[0][0]),
                        Integer.parseInt(excel[0][1]),
                        Integer.parseInt(excel[0][2]));
                //处理其他数据
                title = excel[1];
                start_week = new excel_deal().Stringarray_to_intarray(excel[2]);
                over_week = new excel_deal().Stringarray_to_intarray(excel[3]);
                posion = excel[4];
                teacher = excel[5];
                response = 2;
                for(int i=0;i<excel.length;i++){
                    if(excel[i]==null){
                        response=5;
                        break;
                    }
                }
            }catch (Exception e){
                new log_out().log_out(e);
                response=3;
            }
        }
        return response;
    }

    public int set_class2(){
        int response=0;
        //0表示初始状态意为出现bug
        //1表示初始化成功
        //2表示导入成功
        //3表示导入失败，出现bug
        //4表示读取失败
        File file =new File("/storage/emulated/0/课表/课表.xls");
        if(!file.exists()){
            try{
                excel_deal excel_deal=new excel_deal();
                excel_deal.createEmptyExcel("/storage/emulated/0/课表/");
                response=1;
            }
            catch (Exception e){
                new log_out().log_out(e);
            }
        }
        else {
            try {
                String[][] excel = new excel_deal().readExcel2("/storage/emulated/0/课表/课表.xls");//内容是time,title,start_week,over_week,posion,teacher
                //处理时间
                start_time = new time().date_to_unixtime(
                        Integer.parseInt(excel[0][0]),
                        Integer.parseInt(excel[0][1]),
                        Integer.parseInt(excel[0][2]));
                //处理其他数据
                title = excel[1];
                start_week = new excel_deal().Stringarray_to_intarray(excel[2]);
                over_week = new excel_deal().Stringarray_to_intarray(excel[3]);
                posion = excel[4];
                teacher = excel[5];
                response = 2;
                for(int i=0;i<excel.length;i++){
                    if(excel[i]==null){
                        response=5;
                        break;
                    }
                }
            }catch (Exception e){
                new log_out().log_out(e);
                response=3;
            }
        }
        return response;
    }
}
