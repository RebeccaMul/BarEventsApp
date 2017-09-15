package com.example.administrator.bignightout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    //declare
    EditText usernameET, passwordET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //connecting to xml views
        usernameET = (EditText)findViewById(R.id.etUsername);
        passwordET = (EditText)findViewById(R.id.etPassword);


    }
    public void OnLogin(View view){



        //variables to get entered data to store
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();

        if(username.equals("") && (password.equals("")))
        {
            //toast login unsuccessful uname pword requested
            // code for first activity
            Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_LONG).show();
        }
        else {


            String type = "Login";

            // context on background worker class
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);

            //pass argument type = params 0 , username = 1, password = 2
            backgroundWorker.execute(type, username, password);


       //     startActivity(new Intent(this, MenuActivity.class));

 }

    }
    public void OpenReg1(View view){
        startActivity(new Intent(this,Register.class));

    }

}
