package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public Button[] button=new Button[56];
    class_table class_table=new class_table();
    Boolean viewid=false;//用来记录屏幕的位置，flase是周一到周五，true是周三到周日
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            button_and_table();//初始化
        }
        catch (Exception e){
            new log_out().log_out(e);
        }
    }
    public void button_and_table(){//初始化按钮和课表
            button[11] = findViewById(R.id.button11);
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
            class_table.set_class();
        class_on1();
    }
    //开始设置周一到周五的课表
    public void class_on1(){
        //周一到周日的课
        int[] class_posion={11,12,13,14,15,21,22,23,24,25,31,32,33,34,35,41,42,43,44,45,51,52,53,54,55};
        //循环开始
        for (int i=0;i<class_posion.length;i++){
            int posion=class_posion[i];//一节课

            //判断是否为空，若是则设置按钮不可见，若不是则设置按钮可见，并安置class_table中的相应title
            if(class_table.title[posion]==null){
                button[posion].setVisibility(View.INVISIBLE);
            }
            else{
                button[posion].setVisibility(View.VISIBLE);
                button[posion].setText(class_table.title[posion]);
            }
        }
    }
    //开始设置周三到周日的课表
    public void class_on2(){
        //周三到周五的课
        int[] class_posion={31,32,33,34,35,41,42,43,44,45,51,52,53,54,55,61,62,63,64,65,71,72,73,74,75};
        for (int i=0;i<class_posion.length;i++){
            int posion=class_posion[i];//一节课
            //判断是否为空，若是则设置按钮不可见，若不是则设置按钮可见，并安置class_table中的相应title
            if(class_table.title[posion]==null){
                button[posion-20].setVisibility(View.INVISIBLE);
            }
            else{
                button[posion-20].setVisibility(View.VISIBLE);
                button[posion-20].setText(class_table.title[posion]);
            }
        }
    }
    public void tanchuang(int location){//弹出窗口，int是课的代号
        if(viewid){//当视角处在周三到周日的时候，传来的位置要+20
            location=location+20;
        }
        AlertDialog alertDialog1=new AlertDialog.Builder(this)
                .setTitle(class_table.title[location])//标题
                .setMessage("教师：+"+class_table.teacher[location]+"\n" +
                        "时间："+Integer.toString(class_table.start_week[location])+"-"+Integer.toString(class_table.over_week[location])+"周\n" +
                        "地点："+class_table.posion[location])//内容
                .create();
        alertDialog1.show();
    }
}