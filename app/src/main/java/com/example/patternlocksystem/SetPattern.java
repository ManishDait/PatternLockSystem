package com.example.patternlocksystem;

import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SetPattern extends AppCompatActivity {
    private Rect image1Rect,image2Rect, image3Rect, image4Rect,image5Rect, image6Rect, image7Rect,image8Rect, image9Rect;
    private ImageView I1, I2, I3, I4, I5, I6, I7, I8, I9;
    private Button ok, cancel;
    public static final String MY_PREFS_NAME= "Pattern_Preference";
    private String temp, temp2="",pattern,u_name, u_mail, u_ques, u_ans;
    private Vector<String> vector, vector_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pattern);
        set_ui();
        Bundle extra = getIntent().getExtras();
        u_name = extra.getString("u_name");
        u_mail = extra.getString("u_email");
        u_ques = extra.getString("u_ques");
        u_ans = extra.getString("u_ans");

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vector.add("0");
                temp = vector.get(0);
                for(int i=0; i<vector.size(); i++){
                  if(temp != (vector.get(i))){
                      vector_main.add(temp);
                      temp = vector.get(i);
                  }

                }
                for(int i = 0; i< vector_main.size(); i++){
                    temp2 = temp2 + vector_main.get(i);

                }
                pattern = temp2;
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("u_name", u_name);
                editor.putString("u_email", u_mail);
                editor.putString("u_ques", u_ques);
                editor.putString("u_ans", u_ans);
                editor.putString("u_name", u_name);
                editor.putString("u_pattern", pattern);
                editor.apply();

                startActivity(new Intent(SetPattern.this, PatternVerificationActivity.class));
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vector.clear();
                I1.setImageResource(R.drawable.ic_baseline_adjust_24);
                I2.setImageResource(R.drawable.ic_baseline_adjust_24);
                I3.setImageResource(R.drawable.ic_baseline_adjust_24);
                I4.setImageResource(R.drawable.ic_baseline_adjust_24);
                I5.setImageResource(R.drawable.ic_baseline_adjust_24);
                I6.setImageResource(R.drawable.ic_baseline_adjust_24);
                I7.setImageResource(R.drawable.ic_baseline_adjust_24);
                I8.setImageResource(R.drawable.ic_baseline_adjust_24);
                I9.setImageResource(R.drawable.ic_baseline_adjust_24);

            }
        });


    }

    private void set_ui() {
        I1 = findViewById(R.id.point1);
        I2 = findViewById(R.id.point2);
        I3 = findViewById(R.id.point3);
        I4 = findViewById(R.id.point4);
        I5 = findViewById(R.id.point5);
        I6 = findViewById(R.id.point6);
        I7 = findViewById(R.id.point7);
        I8 = findViewById(R.id.point8);
        I9 = findViewById(R.id.point9);
        ok = findViewById(R.id.button4);
        cancel = findViewById(R.id.button5);
        vector = new Vector<String>();
        vector_main = new Vector<String>();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (image1Rect == null) {
            image1Rect = new Rect();
            I1.getGlobalVisibleRect(image1Rect);
        }
        int x= (int) event.getX();
        int y=(int) event.getY();
        if(image1Rect.contains(x, y)){
            vector.add("1");
            I1.setImageResource(R.drawable.ic_baseline_adjust_check_24);
        }

        if (image2Rect == null) {
            image2Rect = new Rect();
            I2.getGlobalVisibleRect(image2Rect);
        }
        int i= (int) event.getX();
        int j=(int) event.getY();
        if(image2Rect.contains(x, y)){
            vector.add("2");
            I2.setImageResource(R.drawable.ic_baseline_adjust_check_24);
        }
        if (image3Rect == null) {
            image3Rect = new Rect();
            I3.getGlobalVisibleRect(image3Rect);
        }
        int a= (int) event.getX();
        int b=(int) event.getY();
        if(image3Rect.contains(x, y)){
            vector.add("3");
            I3.setImageResource(R.drawable.ic_baseline_adjust_check_24);
        }

        if (image4Rect == null) {
            image4Rect = new Rect();
            I4.getGlobalVisibleRect(image4Rect);
        }
        int c= (int) event.getX();
        int d=(int) event.getY();
        if(image4Rect.contains(x, y)){
            vector.add("4");
            I4.setImageResource(R.drawable.ic_baseline_adjust_check_24);
        }

        if (image5Rect == null) {
            image5Rect = new Rect();
            I5.getGlobalVisibleRect(image5Rect);
        }
        int e= (int) event.getX();
        int f=(int) event.getY();
        if(image5Rect.contains(x, y)){
            vector.add("5");
            I5.setImageResource(R.drawable.ic_baseline_adjust_check_24);
        }

        if (image6Rect == null) {
            image6Rect = new Rect();
            I6.getGlobalVisibleRect(image6Rect);
        }
        int g= (int) event.getX();
        int h=(int) event.getY();
        if(image6Rect.contains(x, y)){
            vector.add("6");
            I6.setImageResource(R.drawable.ic_baseline_adjust_check_24);
        }
        if (image7Rect == null) {
            image7Rect = new Rect();
            I7.getGlobalVisibleRect(image7Rect);
        }
        int k= (int) event.getX();
        int l=(int) event.getY();
        if(image7Rect.contains(x, y)){
            vector.add("7");
            I7.setImageResource(R.drawable.ic_baseline_adjust_check_24);
        }

        if (image8Rect == null) {
            image8Rect = new Rect();
            I8.getGlobalVisibleRect(image8Rect);
        }
        int m= (int) event.getX();
        int n=(int) event.getY();
        if(image8Rect.contains(x, y)){
            vector.add("8");
            I8.setImageResource(R.drawable.ic_baseline_adjust_check_24);
        }
        if (image9Rect == null) {
            image9Rect = new Rect();
            I9.getGlobalVisibleRect(image9Rect);
        }
        int o= (int) event.getX();
        int p=(int) event.getY();
        if(image9Rect.contains(x, y)){
            vector.add("9");
            I9.setImageResource(R.drawable.ic_baseline_adjust_check_24);
        }


        return true;
    }

}
