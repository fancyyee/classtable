package com.example.timetable;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class log_out {
    public String string_log_out(Exception e){
        StackTraceElement stackTraceElement= e.getStackTrace()[0];// 得到异常棧的首个元素
        String text="File="+stackTraceElement.getFileName()+"\n"+// 打印文件名
                "Line="+stackTraceElement.getLineNumber()+"\n"+// 打印出错行号
                "Method="+stackTraceElement.getMethodName()+"\n"+// 打印出错方法
                "log="+e.toString()+"\n";
        return  text;
    }
    public void log_out(Exception e){
        StackTraceElement stackTraceElement= e.getStackTrace()[0];// 得到异常棧的首个元素
        String text="File="+stackTraceElement.getFileName()+"\n"+// 打印文件名
        "Line="+stackTraceElement.getLineNumber()+"\n"+// 打印出错行号
        "Method="+stackTraceElement.getMethodName()+"\n"+// 打印出错方法
                "log="+e.toString()+"\n";
        text_out(text);
    }
    public void text_out(String error){
        String filePath = "/storage/emulated/0/文件/";//目录名
        String fileName = "log.txt";//文件名

        //生成文件夹
        File file_dir=new File(filePath);
        if(!file_dir.exists()){
            Boolean file_dir_out=file_dir.mkdir();
        }

        //生成文件
        File file_name=new File(filePath+fileName);
        if(!file_name.exists()){
            try {
                file_name.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //写文件
        //写入后换行
        error=error+"\r\n";
        try {
            if (!file_name.exists()) {
                file_name.getParentFile().mkdirs();
                file_name.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file_name, "rwd");
            raf.seek(file_name.length());
            raf.write(error.getBytes());
            raf.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
