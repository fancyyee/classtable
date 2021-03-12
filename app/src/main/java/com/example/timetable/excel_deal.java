package com.example.timetable;

//公用的库
import java.io.File;
import java.io.IOException;
import jxl.Workbook;
//写
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
//读
import jxl.Sheet;
import jxl.read.biff.BiffException;

/**
这个文件的作用是读写excel文件来获取课表
 **/
public class excel_deal {
    public String[][] readExcel(String Filepath){
        String[] time=new String[3];
        String[] title=new String[76];//课标题
        String[] start_week=new String[76];//课的开始周数
        String[] over_week=new String[76];//课的结束周数
        String[] posion=new String[76];//上课地点
        String[] teacher=new String[76];//授课教师
        File xlsFile = new File(Filepath);
        // 获得工作簿对象
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(xlsFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        // 获得所有工作表
        Sheet[] sheets = workbook.getSheets();
        // 遍历工作表
        if (sheets != null){
            Sheet sheet =sheets[0];
            if(sheet.getCell(7,0).getContents()!="年"){
                time= new String[]{
                        sheet.getCell(7, 0).getContents(),
                        sheet.getCell(8, 0).getContents(),
                        sheet.getCell(9, 0).getContents()
                };//对应年月日
                for(int row=1;row<=35;row++){
                    int labelPosion=Integer.parseInt(sheet.getCell(0,row).getContents());
                    title[labelPosion]=sheet.getCell(1,row).getContents();
                    start_week[labelPosion] = sheet.getCell(2, row).getContents();
                    over_week[labelPosion] = sheet.getCell(3, row).getContents();
                    posion[labelPosion]=sheet.getCell(5,row).getContents();
                    teacher[labelPosion]=sheet.getCell(4,row).getContents();
                }
            }
        }
        workbook.close();
        String[][] response={time,title,start_week,over_week,posion,teacher};
        for(int i=0;i<response.length;i++){
            delete_null(response[i]);//删除获取数据中的空数据
        }
        return  response;
    }
    public String[][] readExcel2(String Filepath){//
        String[] time=new String[3];
        String[] title=new String[76];//课标题
        String[] start_week=new String[76];//课的开始周数
        String[] over_week=new String[76];//课的结束周数
        String[] posion=new String[76];//上课地点
        String[] teacher=new String[76];//授课教师
        File xlsFile = new File(Filepath);
        // 获得工作簿对象
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(xlsFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        // 获得所有工作表
        Sheet[] sheets = workbook.getSheets();
        // 遍历工作表
        if (sheets != null){
            if(sheets.length>=2){
                Sheet sheet =sheets[1];
                if(sheet.getCell(7,0).getContents()!="年"){
                    time= new String[]{
                            sheet.getCell(7, 0).getContents(),
                            sheet.getCell(8, 0).getContents(),
                            sheet.getCell(9, 0).getContents()
                    };//对应年月日
                    for(int row=1;row<=35;row++){
                        int labelPosion=Integer.parseInt(sheet.getCell(0,row).getContents());
                        title[labelPosion]=sheet.getCell(1,row).getContents();
                        start_week[labelPosion] = sheet.getCell(2, row).getContents();
                        over_week[labelPosion] = sheet.getCell(3, row).getContents();
                        posion[labelPosion]=sheet.getCell(5,row).getContents();
                        teacher[labelPosion]=sheet.getCell(4,row).getContents();
                    }
                }
            }
        }
        workbook.close();
        String[][] response={time,title,start_week,over_week,posion,teacher};
        for(int i=0;i<response.length;i++){
            delete_null(response[i]);//删除获取数据中的空数据
        }
        return  response;
    }

    public int[] Stringarray_to_intarray(String[] Stringarray){
        int[] response=new int[Stringarray.length];
        for(int i=0;i<Stringarray.length;i++){
            try{
                if(Stringarray[i]!=null){
                    response[i]=Integer.parseInt(Stringarray[i]);
                }
            }catch(Exception e){

            }
        }
        return response;
    }
    public void delete_null(String[] intput){//删除获取数据中的空数据
        for(int i=0;i<intput.length;i++){
            if(intput[i]==""){
                intput[i]=null;
            }
        }
    }







    public void createEmptyExcel(String Filepath){
        String[] title={"课程位置(主键)","课程名称","上课周数","结课周数","教师","地点","开学第一天(请替换)","年","月","日"};


        //生成文件夹
        File file_dir=new File(Filepath);
        if(!file_dir.exists()){
            Boolean file_dir_out=file_dir.mkdir();
        }
        Filepath=Filepath+"课表.xls";
        File xlsFile=new File(Filepath);
        // 创建一个工作簿
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(xlsFile);
            // 创建一个工作表
            WritableSheet sheet = workbook.createSheet("sheet1", 0);
            //添加列标题
            Label label=null;
            for(int i=0;i<title.length;i++){
                label=new Label(i,0,title[i]);//这里的参数是(列,行,内容)
                try {
                    sheet.addCell(label);
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
            String labelData;
            int k=1;
            for (int i=1;i<=7;i++){
                for(int j=1;j<=5;j++){
                    labelData=Integer.toString(i)+Integer.toString(j);
                    label=new Label(0,k,labelData);
                    k++;
                    try {
                        sheet.addCell(label);
                    } catch (WriteException e) {
                        e.printStackTrace();
                    }
                }
            }
            workbook.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
