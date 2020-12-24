package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    public Button[] button=new Button[56];//button的集合
    class_table class_table=new class_table();//课表
    Boolean viewid=false;//用来记录屏幕的位置，flase是周一到周五，true是周三到周日
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        button_and_table();//初始化
        Intent intent = getIntent();
        path = intent.getStringExtra(MainActivity.path);
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
        class_table.set_class(path);
        class_on();
    }
    //设置课表
    public void class_on(){
        int[] class_posion;
        if(!viewid){//根据viewid判断应该读取哪些课
            class_posion= new int[]{11, 12, 13, 14, 15, 21, 22, 23, 24, 25, 31, 32, 33, 34, 35, 41, 42, 43, 44, 45, 51, 52, 53, 54, 55};
        }
        else{
            class_posion= new int[]{31, 32, 33, 34, 35, 41, 42, 43, 44, 45, 51, 52, 53, 54, 55, 61, 62, 63, 64, 65, 71, 72, 73, 74, 75};
        }
        for(int i=0;i<class_posion.length;i++){
            int posion=class_posion[i];


            Button buttonx;
            if(!viewid){//根据viewid选择button
                buttonx=button[posion];
            }
            else{
                buttonx=button[posion-20];
            }

            if(class_table.title[posion]!=null){//如果title存在则设置，不存在则清空
                buttonx.setText(class_table.title[posion]);
            }
            else{
                buttonx.setText("");
            }
        }
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
    public String pack_table(){//把课表转化成既定的格式的String
        String response="";
        return response;
    }
    public void button11(View view){    }
    public void button12(View view){    }
    public void button13(View view){    }
    public void button14(View view){    }
    public void button15(View view){    }
    public void button21(View view){    }
    public void button22(View view){    }
    public void button23(View view){    }
    public void button24(View view){    }
    public void button25(View view){    }
    public void button31(View view){    }
    public void button32(View view){    }
    public void button33(View view){    }
    public void button34(View view){    }
    public void button35(View view){    }
    public void button41(View view){    }
    public void button42(View view){    }
    public void button43(View view){    }
    public void button44(View view){    }
    public void button45(View view){    }
    public void button51(View view){    }
    public void button52(View view){    }
    public void button53(View view){    }
    public void button54(View view){    }
    public void button55(View view){    }
    public void button101(View view){//view向左
        if(viewid){
            view_change();
        }
    }
    public void button102(View view){//view向右
        if (!viewid) {
            view_change();
        }
    }
    public void button103(View view){    }//确认
    public void button104(View view){//清空
        class_table=new class_table();//初始化课表
        class_on();
    }
}
/*
time:222222222222222222222
11:标题&教师&week初&week末&地点;
12:
13:
 */