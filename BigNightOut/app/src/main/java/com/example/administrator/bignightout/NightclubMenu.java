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

public class NightclubMenu extends MenuActivity{

    //Declaring buttons:
    Button nightclub1;
    Button nightclub2;
    Button nightclub3;
    Button nightclub4;
    Button nightclub5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nightclubmenulayout);

        //Initialising the button with existing button id:
        nightclub1 = (Button)findViewById(R.id.menu_1);
        nightclub2 = (Button)findViewById(R.id.menu_2);
        nightclub3 = (Button)findViewById(R.id.menu_3);
        nightclub4 = (Button)findViewById(R.id.menu_4);
        nightclub5 = (Button)findViewById(R.id.menu_5);
    }

    //OnClick method for Nightclub button 1:
    public void Redirect1(View view) {

       Intent fly = new Intent(this, NightClubOne.class);
        startActivity(fly);

    }

    //OnClick method for Nightclub button 2:
    public void Redirect2(View view) {

        Intent ollies = new Intent(this, NightClubTwo.class);
        startActivity(ollies);
    }


}
