package com.example.mobiletimecostapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.Console;
import java.security.PublicKey;

import javax.sql.DataSource;

public class MainActivity extends AppCompatActivity {

    Button logIn;
    EditText username;
    EditText password;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHandler(this);
        db.addDummyUser();
        logIn = (Button)findViewById(R.id.logIn);
        logIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        username = findViewById(R.id.userNameInput);
                        password = findViewById(R.id.passwordInput);
                        int valid = db.getUser(username.getText().toString(), password.getText().toString());
                        if(valid == 0)
                        {

                            Intent intent = new Intent(MainActivity.this, register.class);
                            startActivity(intent);
                            Log.println(Log.DEBUG,"Input", username.getText().toString());
                        }

                    }
                }
        );

    }
}
