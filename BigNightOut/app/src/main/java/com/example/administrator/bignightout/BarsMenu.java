package com.example.administrator.bignightout;

/**
 * Created by 40083555 on 14/03/2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class BarsMenu extends MenuActivity{

    //Declaring buttons:
    Button bar1;
    Button bar2;
    Button bar3;
    Button bar4;
    Button bar5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barmenulayout);

        //Initialising the login button with existing button id:
        bar1 = (Button)findViewById(R.id.menu_1);
        bar2 = (Button)findViewById(R.id.menu_2);
        bar3 = (Button)findViewById(R.id.menu_3);
        bar4 = (Button)findViewById(R.id.menu_4);
        bar5 = (Button)findViewById(R.id.menu_5);
    }

    //OnClick method for bar button 1:
    public void LaverysRedirect(View view) {

        Intent laverys = new Intent(this, BarOne.class);
        startActivity(laverys);

    }

    //OnClick method for bar button 2:
    public void Redirect2(View view) {

        Intent limelight = new Intent(this, BarTwo.class);
        startActivity(limelight);

    }

    //OnClick method for bar button 3:
    public void Redirect3(View view) {

        Intent cuckoo = new Intent(this, BarThree.class);
        startActivity(cuckoo);

    }


}
