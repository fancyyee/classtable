package com.example.timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public Button[] button=new Button[56];//button的集合
    class_table class_table=new class_table();//课表
    public int weeknum;//周数
    Boolean viewid=false;//用来记录屏幕的位置，flase是周一到周五，true是周三到周日
    Boolean coverid=false;//用来表示是否显示不在的课，true为显示
    public static String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();//动态获取文件权限
        path="/storage/emulated/0/Android/com.example.timetable";
        try {
            button_and_table();//初始化
        }
        catch (Exception e){
            new log_out().log_out(e);//输出log
        }
    }
    public void button_and_table(){//初始化按钮和课表周数
        button[11] = findViewById(R.id.button11);//把button放到数组
        button[12] = findViewById(R.id.button12);
        button[13] = findViewById(R.id.button13);
        button[14] = findViewById(R.id.button14);
        button[15] = findViewById(R.id.button15);
        button[21] = findViewById(R.id.button21);
        button[22] = findViewById(R.id.button22);
        button[23] = findViewById(R.id.button23);
        button[24] = findViewById(R.id.button24);
        button[25] = findViewById(R.id.button25);
        button[31] = findViewById(R.id.button31);
        button[32] = findViewById(R.id.button32);
        button[33] = findViewById(R.id.button33);
        button[34] = findViewById(R.id.button34);
        button[35] = findViewById(R.id.button35);
        button[41] = findViewById(R.id.button41);
        button[42] = findViewById(R.id.button42);
        button[43] = findViewById(R.id.button43);
        button[44] = findViewById(R.id.button44);
        button[45] = findViewById(R.id.button45);
        button[51] = findViewById(R.id.button51);
        button[52] = findViewById(R.id.button52);
        button[53] = findViewById(R.id.button53);
        button[54] = findViewById(R.id.button54);
        button[55] = findViewById(R.id.button55);
        try {
            int tableState = class_table.set_class();//初始化课表
            if(tableState==0|tableState==3){
                tableState =class_table.set_class();
                if(tableState==0|tableState==3) {
                    AlertDialog tableAlertDialog = new AlertDialog.Builder(this)
                            .setTitle("It's a bug")//标题
                            .setMessage("It's a bug,code:" + tableState)//内容
                            .create();
                    tableAlertDialog.show();
                }
                if(tableState==4){
                    AlertDialog tableAlertDialog = new AlertDialog.Builder(this)
                            .setTitle("It's a bug")//标题
                            .setMessage("load filed")//内容
                            .create();
                    tableAlertDialog.show();
                }
            }
            if(tableState==1){
                AlertDialog tableAlertDialog=new AlertDialog.Builder(this)
                        .setTitle("初始化完成")//标题
                        .setMessage("请前往\"课表/课表.xls\"导入课表"+tableState)//内容
                        .create();
                tableAlertDialog.show();
            }
        }catch (Exception e){
            new log_out().log_out(e);
        }
        time time=new time();//获取当前时间
        time.gettime(class_table.start_time);
        weeknum=time.week_num;
        try {
            class_on();//设置课表
        }catch (Exception e){
            new log_out().log_out(e);
        }
        TextView textView=findViewById(R.id.textView6);//设置周数
        String text="这是第"+Integer.toString(time.week_num)+"周";
        textView.setText(text);
    }

    //设置课表
    public void class_on(){
        int[] class_posion;//课的代码
        if(!viewid){//根据viewid判断应该读取哪些课
            class_posion= new int[]{11, 12, 13, 14, 15, 21, 22, 23, 24, 25, 31, 32, 33, 34, 35, 41, 42, 43, 44, 45, 51, 52, 53, 54, 55};
        }
        else{
            class_posion= new int[]{31, 32, 33, 34, 35, 41, 42, 43, 44, 45, 51, 52, 53, 54, 55, 61, 62, 63, 64, 65, 71, 72, 73, 74, 75};
        }
        for(int i=0;i<class_posion.length;i++){
            int posion=class_posion[i];//获取单个pision
            Button buttonx;
            if(!viewid){//根据viewid选择button
                buttonx=button[posion];
            }
            else{
                buttonx=button[posion-20];
            }

            if(class_table.title[posion]==null){//判断，如果title为空则设置该按钮看不见
                buttonx.setVisibility(View.INVISIBLE);
            }
            else{
                buttonx.setText(class_table.title[posion]);
                if(class_table.start_week[posion]>weeknum||class_table.over_week[posion]<weeknum){//如果时间不符合则根据coverid设置可见性
                    if(coverid){//根据coverid设置可见性
                        buttonx.setVisibility(View.VISIBLE);
                    }
                    else{
                        buttonx.setVisibility(View.INVISIBLE);
                    }
                    buttonx.setBackgroundColor(Color.parseColor("#9f9f9f"));//设置背景颜色为灰色
                }
                else{//如果时间符合则设置按钮可见并设置背景颜色为绿色
                    buttonx.setVisibility(View.VISIBLE);
                    buttonx.setBackgroundColor(Color.parseColor("#C6EFCE"));
                }
            }
        }
    }
    public void week_num_change(Boolean change){//change是true则是加，是flase则是减少
        if(change){
            weeknum++;
        }
        else{
            weeknum--;
        }
        class_on();//重新设置课表和周数的显示
        TextView textView=findViewById(R.id.textView6);
        String text="这是第"+Integer.toString(weeknum)+"周";
        textView.setText(text);
    }
    public void view_change(){//修改viewID并且重置课表达到翻页的效果，这个函数给翻页按钮
        TextView[] textView={findViewById(R.id.textView1), findViewById(R.id.textView2), findViewById(R.id.textView3), findViewById(R.id.textView4), findViewById(R.id.textView5)};
        if(viewid){
            viewid=false;
            textView[0].setText("周一");
            textView[1].setText("周二");
            textView[2].setText("周三");
            textView[3].setText("周四");
            textView[4].setText("周五");
        }
        else{
            viewid=true;
            textView[0].setText("周三");
            textView[1].setText("周四");
            textView[2].setText("周五");
            textView[3].setText("周六");
            textView[4].setText("周日");
        }
        class_on();
    }
    public void tanchuang(int location){//弹出窗口，int是课的代号
        if(viewid){//当视角处在周三到周日的时候，传来的位置要+20
            location=location+20;
        }
        AlertDialog alertDialog1=new AlertDialog.Builder(this)
                .setTitle(class_table.title[location])//标题
                .setMessage("教师："+class_table.teacher[location]+"\n" +
                        "时间："+Integer.toString(class_table.start_week[location])+"-"+Integer.toString(class_table.over_week[location])+"周\n" +
                        "地点："+class_table.posion[location])//内容
                .create();
        alertDialog1.show();
    }
    public void coverid_changge(){//修改coverid
        if(coverid){
            coverid=false;
        }
        else{
            coverid=true;
        }
        class_on();
    }
    private void getPermission(){//动态获取权限
        String[] permission={Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(MainActivity.this,permission,1);
    }
    public void test(View view){

    }




    //----------------------以下是button的批量界面，以及该class结尾的}----------------------------------------------------
    public void button11(View view){
        tanchuang(11);
    }
    public void button12(View view){
        tanchuang(12);
    }
    public void button13(View view){
        tanchuang(13);
    }
    public void button14(View view){
        tanchuang(14);
    }
    public void button15(View view){
        tanchuang(15);
    }
    public void button21(View view){
        tanchuang(21);
    }
    public void button22(View view){
        tanchuang(22);
    }
    public void button23(View view){
        tanchuang(23);
    }
    public void button24(View view){
        tanchuang(24);
    }
    public void button25(View view){
        tanchuang(25);
    }
    public void button31(View view){
        tanchuang(31);
    }
    public void button32(View view){
        tanchuang(32);
    }
    public void button33(View view){
        tanchuang(33);
    }
    public void button34(View view){
        tanchuang(34);
    }
    public void button35(View view){
        tanchuang(35);
    }
    public void button41(View view){
        tanchuang(41);
    }
    public void button42(View view){
        tanchuang(42);
    }
    public void button43(View view){
        tanchuang(43);
    }
    public void button44(View view){
        tanchuang(44);
    }
    public void button45(View view){
        tanchuang(45);
    }
    public void button51(View view){
        tanchuang(51);
    }
    public void button52(View view){
        tanchuang(52);
    }
    public void button53(View view){
        tanchuang(53);
    }
    public void button54(View view){
        tanchuang(54);
    }
    public void button55(View view){
        tanchuang(55);
    }
    public void button101(View view){//view向左
        if(viewid){//如果课表在右边
            view_change();
        }
    }
    public void button102(View view){//view向右
        if (!viewid) {//如果课表在左边
            view_change();
        }
    }
    public void button103(View view){//周数-
        week_num_change(false);
    }
    public void button104(View view){//周数+\
        week_num_change(true);
    }
    public void switch1(View view){
        coverid_changge();
    }











    //other's code
    //动态获取权限
    //原文链接：https://blog.csdn.net/qq_37832932/article/details/106453911
    //使用方法checkPermission();
    String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};//需要的权限在这里加入
    List<String> mPermissionList = new ArrayList<>();

    // private ImageView welcomeImg = null;
    private static final int PERMISSION_REQUEST = 1;
// 检查权限


    private void checkPermission() {
        mPermissionList.clear();
        //判断哪些权限未授予
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        /**
         * 判断是否为空
         */
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(MainActivity.this, permissions, PERMISSION_REQUEST);
        }
    }
    /**
     * 响应授权
     * 这里不管用户是否拒绝，都进入首页，不再重复申请权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST:
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }
    //Android 危险权限目录
    //group:android.permission-group.CONTACTS
    //        permission:android.permission.WRITE_CONTACTS
    //        permission:android.permission.GET_ACCOUNTS
    //        permission:android.permission.READ_CONTACTS
    //
    //group:android.permission-group.PHONE
    //      permission:android.permission.READ_CALL_LOG
    //      permission:android.permission.READ_PHONE_STATE
    //      permission:android.permission.CALL_PHONE
    //      permission:android.permission.WRITE_CALL_LOG
    //      permission:android.permission.USE_SIP
    //      permission:android.permission.PROCESS_OUTGOING_CALLS
    //      permission:com.android.voicemail.permission.ADD_VOICEMAIL
    //
    //group:android.permission-group.CALENDAR
    //      permission:android.permission.READ_CALENDAR
    //      permission:android.permission.WRITE_CALENDAR
    //
    //group:android.permission-group.CAMERA
    //      permission:android.permission.CAMERA
    //
    //group:android.permission-group.SENSORS
    //      permission:android.permission.BODY_SENSORS
    //
    //group:android.permission-group.LOCATION
    //      permission:android.permission.ACCESS_FINE_LOCATION
    //      permission:android.permission.ACCESS_COARSE_LOCATION
    //
    //group:android.permission-group.STORAGE
    //      permission:android.permission.READ_EXTERNAL_STORAGE
    //      permission:android.permission.WRITE_EXTERNAL_STORAGE
    //
    //group:android.permission-group.MICROPHONE
    //      permission:android.permission.RECORD_AUDIO
    //
    //group:android.permission-group.SMS
    //      permission:android.permission.READ_SMS
    //      permission:android.permission.RECEIVE_WAP_PUSH
    //      permission:android.permission.RECEIVE_MMS
    //      permission:android.permission.RECEIVE_SMS
    //      permission:android.permission.SEND_SMS
    //      permission:android.permission.READ_CELL_BROADCASTS
}