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

public class MenuActivity extends AppCompatActivity {

    //Declaring buttons:
    Button menubutton_1;
    Button menubutton_2;
    Button menubutton_3;
    Button menubutton_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Initialising the login button with existing button id:
        menubutton_1 = (Button)findViewById(R.id.menu_1);
        menubutton_2 = (Button)findViewById(R.id.menu_2);
        menubutton_3 = (Button)findViewById(R.id.menu_3);
        menubutton_4 = (Button)findViewById(R.id.menu_4);

    }

    //OnClick method for button 1:
    public void Redirect1(View view) {

        Intent i = new Intent(this, BarsMenu.class);
        startActivity(i);

    }

    public void Redirect2(View view) {

              Intent i2 = new Intent(this, CocktailsMenu.class);
              startActivity(i2);
    }

    public void Redirect3(View view) {

              Intent i3 = new Intent(this, NightclubMenu.class);
              startActivity(i3);
    }

    public void Redirect4(View view) {

              Intent i4 = new Intent(this, CraftBeerMenu.class);
              startActivity(i4);

    }

    public void Redirect5(View view) {

              Intent i5 = new Intent(this, EventsMenu.class);
              startActivity(i5);

    }


}
