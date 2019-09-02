package com.example.mobiletimecostapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterHandler extends AppCompatActivity {

    Button register;
    EditText username;
    EditText password;
    EditText expensesCost;
    EditText weeklyIncome;
    EditText fullName;
    CheckBox studentLoan ;
    DBHandler db;
    InputMethodManager imm;
    TCMAUser user;

    private boolean ValidInput(EditText fields[])
    {
        for (EditText element : fields) {
            String temp = element.getText().toString();
            if (temp.isEmpty())
                return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBHandler(this);
        register = (Button)findViewById(R.id.Register);
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        studentLoan = findViewById(R.id.studentLoanInput);
                        expensesCost = findViewById(R.id.WeeklyExpensesInput);
                        weeklyIncome = findViewById(R.id.weeklyIncomeInput);
                        username = findViewById(R.id.userNameInput);
                        password = findViewById(R.id.passwordInput);
                        fullName = findViewById(R.id.fullNameInput);

                        EditText inputs[] = new EditText[]{username,fullName, password, expensesCost, weeklyIncome};

                        if (!ValidInput(inputs))
                            Toast.makeText(RegisterHandler.this, "Error: Input is missing!", Toast.LENGTH_LONG).show();
                        else {
                            if (db.validUser(username.getText().toString())) {
                                Toast.makeText(RegisterHandler.this, "Error: username already exists!", Toast.LENGTH_LONG).show();
                            } else if (!db.validUser(username.getText().toString())){
                                user = new TCMAUser();
                                user.setFullName(fullName.getText().toString());
                                user.setWeeklyExpenses(Double.parseDouble((expensesCost.getText().toString())));
                                user.setWeeklyIncome(Double.parseDouble((weeklyIncome.getText().toString())));
                                user.setUserName(username.getText().toString());
                                user.setPassword(password.getText().toString());
                                user.setStudentLoanFlag(studentLoan.isChecked());
                                user.calculateAnnualIncome();

                                if(db.addTCMAUser(user))
                                {
                                    Toast.makeText(RegisterHandler.this, "Register Successful", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(RegisterHandler.this, "Register Unsuccessful", Toast.LENGTH_LONG).show();
                                }

                                System.out.println(user.getWeeklyIncomeAfterTax() + user.getTCMAUserID() + user.getFullName());
                            }
                        }

                        }
                }
        );
    }
}
