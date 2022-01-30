package com.example.patternlocksystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Vector;

public class PatternVerificationActivity extends AppCompatActivity {
    private Rect image1Rect, image2Rect, image3Rect, image4Rect, image5Rect, image6Rect, image7Rect, image8Rect, image9Rect;
    private ImageView I1, I2, I3, I4, I5, I6, I7, I8, I9;
    private TextView t1, mTextField;
    int attempts = (int) Math.floor(Math.random() * (6 - 2 + 1) + 2);
    long time;
    private Button verify, cancel;
    private String temp, temp2 = "", pattern, u_name, u_mail, s_pattern;
    private Vector<String> vector, vector_main;
    public static final String MY_PREFS_NAME = "Pattern_Preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_verification);
        set_ui();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        u_name = prefs.getString("u_name", "");
        u_mail = prefs.getString("u_email", "");
        s_pattern = prefs.getString("u_pattern", "");
        time = prefs.getLong("TIME", 0);


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vector.add("0");
                temp = vector.get(0);
                for (int i = 0; i < vector.size(); i++) {
                    if (temp != (vector.get(i))) {
                        vector_main.add(temp);
                        temp = vector.get(i);
                    }

                }
                for (int i = 0; i < vector_main.size(); i++) {
                    temp2 = temp2 + vector_main.get(i);

                }
                pattern = temp2;

                if (pattern.equals(s_pattern)) {
                    Toast.makeText(getApplicationContext(), "Verify", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PatternVerificationActivity.this, VerificationSucessfullActivity.class));
                } else {
                    temp2 = "";
                    attempts -= 1;
                    vector_main.clear();
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
                    Toast.makeText(getApplicationContext(), "Wrong Pattern, attempts left " + attempts, Toast.LENGTH_SHORT).show();
                    if (attempts == 0) {
                        verify.setVisibility(View.GONE);
                        cancel.setVisibility(View.GONE);
                        mTextField.setVisibility(View.VISIBLE);

                        senEmail();

                        new CountDownTimer(30000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                mTextField.setText("Try again after: " + millisUntilFinished / 1000);
                            }

                            public void onFinish() {
                                mTextField.setVisibility(View.GONE);
                                verify.setVisibility(View.VISIBLE);
                                cancel.setVisibility(View.VISIBLE);
                                attempts = (int) Math.floor(Math.random() * (6 - 2 + 1) + 2);
                            }

                        }.start();

                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vector.clear();
                vector_main.clear();
                temp2 = "";
                pattern = "";
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

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatternVerificationActivity.this, ForgetActivity.class));
                finish();
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
        verify = findViewById(R.id.button4);
        cancel = findViewById(R.id.button5);
        t1 = findViewById(R.id.textView3);
        mTextField = findViewById(R.id.coundown);
        vector = new Vector<String>();
        vector_main = new Vector<String>();

    }


    private void senEmail() {
        String mEmail = u_mail.toString();
        String mSubject = "Security Alert";
        String mMessage = "Hi "+u_name.toString()+" an invalid login attempt is detected\n";


        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mEmail, mSubject, mMessage);

        javaMailAPI.execute();
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