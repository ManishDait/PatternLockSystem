package com.example.patternlocksystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegistrationActivity extends AppCompatActivity {
    private String username, user_mail, question, answer;
    private TextInputEditText user_name, u_email;
    private EditText Question, Answer;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        user_name = findViewById(R.id.u_name);
        u_email = findViewById(R.id.u_email);
        Question = findViewById(R.id.editTextTextPersonName);
        Answer = findViewById(R.id.editTextText);
        submit = findViewById(R.id.button3);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = user_name.getText().toString();
                user_mail = u_email.getText().toString();
                question = Question.getText().toString();
                answer = Answer.getText().toString();
                if(username.isEmpty()||user_mail.isEmpty()||question.isEmpty()||answer.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter the above details to continue", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(RegistrationActivity.this, SetPattern.class);
                    intent.putExtra("u_name", username);
                    intent.putExtra("u_email", user_mail);
                    intent.putExtra("u_ques", question);
                    intent.putExtra("u_ans", answer);
                    startActivity(intent);
                }

            }
        });
    }

}