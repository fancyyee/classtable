package com.example.timetable;

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
    public void set_time(String path){
        start_time=1598803200000L;//第一周的第一天早上0时0分
    }
    public void set_class(String path){
        title[11]="军事理论";
        teacher[11]="周剑波";
        start_week[11]=6;
        over_week[11]=17;
        posion[11]="巡天楼二楼报告厅";

        title[12]="民航概论";
        teacher[12]="吴东苏";
        start_week[12]=6;
        over_week[12]=17;
        posion[12]="T1209";

        title[13]="高等数学Ⅱ";
        teacher[13]="方谋耶";
        start_week[13]=6;
        over_week[13]=19;
        posion[13]="T1309";

        title[23]="大学英语Ⅲ";
        teacher[23]="毛红霞";
        start_week[23]=6;
        over_week[23]=19;
        posion[23]="T1503";

        title[32]="高等数学Ⅱ";
        teacher[32]="方谋耶";
        start_week[32]=6;
        over_week[32]=19;
        posion[32]="T1309";

        title[42]="体育";
        teacher[42]="张亚飞";
        start_week[42]=6;
        over_week[42]=18;
        posion[42]="天目湖田径场";

        title[44]="大学生心理健康教育";
        teacher[44]="杨雪花";
        start_week[44]=6;
        over_week[44]=11;
        posion[44]="T1207";

        title[52]="高等数学Ⅱ";
        teacher[52]="方谋耶";
        start_week[52]=6;
        over_week[52]=18;
        posion[52]="T1309";

        title[72]="高等数学Ⅱ";
        teacher[72]="方谋耶";
        start_week[72]=6;
        over_week[72]=18;
        posion[72]="T1309";

    }
    /*这段代码未经测试，是用来把读取的文件和转化成相应的变量的,回头记得再看看
    public void settable() {
        String re="";
        char[] string_char=re.toCharArray();
        for(int i=0;i<string_char.length;i++){
            if(string_char[i]=="t"){
                if(new String(//如果这里的字符为time:
                        string_char[i],string_char[i+1],string_char[i+2],string_char[i+3],string_char[i+4]//可能有问题
                )=="time:"){
                    i=i+5;//把i调到：之后
                    String week_time="";
                    while(true){
                        if(string_char[i]=="\n"){
                            break;
                        }
                        week_time=week_time+string_char[i];
                        i=i+1;
                    }
                    try{
                        start_time=Long.parseLong(week_time);
                    }catch(Exception e){
                        //这里后面想一下怎么处理
                    }
                }
            }
            if(string_char[i]="\n"){
                i=i+1;
                try{
                    int num=Integer.parseInt(new String(string_char[i]+string_char[i+1]));
                    i=i+3;
                    String reme;
                    int type=1;
                    while(true){
                        reme="";//重置状态
                        if(string_char[i]=="\n"){
                            break;
                        }
                        while(true){
                            if(string_char[i]==":"|string_char[i]=="&"){//回头注意一下这里的全半角，目前是半角
                                i=i+1;
                                break;
                            }
                            reme=reme+string_char[i];
                            i=i+1;
                        }
                        switch(type){
                            case 1:
                                title[num]=reme;
                                type=type+1;
                                break;
                            case 2:
                                teacher[num]=reme;
                                type=type+1;
                                break;
                            case 3:
                                start_week=Integer.parseInt(reme);
                                type=type+1;
                                break;
                            case 4:
                                over_week=Integer.parseInt(reme);
                                type=type+1;
                                break;
                            case 5:
                                posion[num]=reme;
                                type=type+1;
                                break;
                        }
                    }
                }catch(Exception e){
                    //
                }
            }
        }
    }
     */
}
