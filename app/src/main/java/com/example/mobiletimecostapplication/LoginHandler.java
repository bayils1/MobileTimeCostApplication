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

public class LoginHandler extends AppCompatActivity {

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

        //if (!db.validUser("test"))
            db.addDummyUser();

        imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);

        logIn = (Button)findViewById(R.id.logIn);
        logIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                        username = findViewById(R.id.userNameInput);
                        password = findViewById(R.id.passwordInput);
                        Log.println(Log.DEBUG, "LoginHandler", "Successful");

                        if(db.validUser(username.getText().toString()))
                        {
                            Log.println(Log.DEBUG, "reads user", "Successful");
                            user = db.getUserObj(username.getText().toString(), password.getText().toString());

                            Toast.makeText(LoginHandler.this, "Hello " + user.getFullName() + "!", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent intent = new Intent(LoginHandler.this, RegisterHandler.class);
                            startActivity(intent);
                            Log.println(Log.DEBUG,"Input", username.getText().toString());
                        }

                    }
                }
        );

    }
}
