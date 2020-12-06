package com.example.timetable;

public class class_table {
    long start_time;//学期开始的unix时间
    String[] title=new String[75];//课标题
    int[] start_week=new int[75];//课的开始周数
    int[] over_week=new int[75];//课的结束周数
    String[] posion=new String[75];//上课地点
    String[] teacher=new String[75];//授课教师
    public void set_class(){
        start_time=1598803200L;//获取时间的时候记得倒时差

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
}
