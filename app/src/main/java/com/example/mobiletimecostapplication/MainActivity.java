package com.example.mobiletimecostapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    InputMethodManager imm;
    TCMAUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        db = new DBHandler(this);
        imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);

        logIn = (Button)findViewById(R.id.logIn);
        logIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        username = findViewById(R.id.userNameInput);
                        password = findViewById(R.id.passwordInput);

                        if(db.validUser(username.getText().toString(), password.getText().toString()))
                        {
                            user = db.getUserObj(username.getText().toString(), password.getText().toString());
                            Log.println(Log.DEBUG, "Login", "Successful");
                            Toast.makeText(MainActivity.this, "Hello " + user.getUserName() + "!", Toast.LENGTH_LONG).show();
                        }
                        else
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
