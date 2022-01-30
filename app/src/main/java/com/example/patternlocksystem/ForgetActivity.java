package com.example.patternlocksystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgetActivity extends AppCompatActivity {

    private String u_ques, u_ans,u_name, u_mail, s_pattern;
    public static final String MY_PREFS_NAME = "Pattern_Preference";
    private TextView t1;
    private Button ok,submite;
    private EditText ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        u_name = prefs.getString("u_name", "");
        u_mail = prefs.getString("u_email", "");
        u_ques = prefs.getString("u_ques", "");
        u_ans = prefs.getString("u_ans", "");
        s_pattern = prefs.getString("u_pattern", "");
        t1 = findViewById(R.id.textView5);
        t1.setText(u_ques);
        ans = findViewById(R.id.editTextTextPersonName2);
        ok = findViewById(R.id.button);
        submite= findViewById(R.id.button7);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ans.getText().toString().equals(u_ans)){
                    senEmail();
                    t1.setText("Enter the code send in email");
                    ans.setText("");
                    ans.setHint("Enter Code");
                    ok.setVisibility(View.GONE);
                    submite.setVisibility(View.VISIBLE);

                }
            }
        });

        submite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String pass = ans.getText().toString();
                 if(pass.equals(s_pattern)){
                     startActivity(new Intent(ForgetActivity.this, SetNewActivity.class));
                     finish();
                 }
            }
        });
    }
    private void senEmail() {
        String mEmail = u_mail.toString();
        String mSubject = "Forgot Pattern";
        String mMessage = "Hi "+u_name.toString()+" your code is : "+s_pattern+"\nEnter Code to reset pattern";


        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mEmail, mSubject, mMessage);

        javaMailAPI.execute();
    }
}