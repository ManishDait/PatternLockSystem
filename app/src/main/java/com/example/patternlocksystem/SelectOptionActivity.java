package com.example.patternlocksystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class SelectOptionActivity extends AppCompatActivity {

    private Button  Verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option);
        Verify = findViewById(R.id.button2);

        Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File("/data/data/com.example.patternlocksystem/shared_prefs/Pattern_Preference.xml");
                if (f.exists()) {
                    startActivity(new Intent(SelectOptionActivity.this, PatternVerificationActivity.class));
                }

                else{
                    Toast.makeText(getApplicationContext(), "You don't have any Pattern register, register Pattern to continue", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SelectOptionActivity.this, RegistrationActivity.class));
                }

            }


        });
    }
}