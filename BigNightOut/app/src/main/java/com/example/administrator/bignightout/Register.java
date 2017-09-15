package com.example.administrator.bignightout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    //define variables
    EditText name, surname,username,age, password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText)findViewById(R.id.et_Name);
        surname = (EditText)findViewById(R.id.et_Surname);
        username = (EditText)findViewById(R.id.etUsername);
        age = (EditText)findViewById(R.id.et_age);
        password = (EditText)findViewById(R.id.etPassword);

    }

    public void OpenReg(View view){


        //variables to get entered data to store
        String str_name = name.getText().toString();
        String str_surname = surname.getText().toString();
        String str_username = username.getText().toString();
        String str_age = age.getText().toString();
        String str_password = password.getText().toString();


        String type = "Register";

        //context on background worker class
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);

        //pass argument type = params 0 , username = 1, password = 2
        backgroundWorker.execute(type, str_name, str_surname, str_username, str_age, str_password);


    }

}
