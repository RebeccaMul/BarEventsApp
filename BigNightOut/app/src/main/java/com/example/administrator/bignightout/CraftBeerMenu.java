package com.example.administrator.bignightout;

/**
 * Created by 40083555 on 14/03/2017.
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class CraftBeerMenu extends MenuActivity{

    //Declaring buttons:
    Button craft1;
    Button craft2;
    Button craft3;
    Button craft4;
    Button craft5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.craftbeermenulayout);

        //Initialising the button with existing button id:
        craft1 = (Button)findViewById(R.id.menu_1);
        craft2 = (Button)findViewById(R.id.menu_2);
        craft3 = (Button)findViewById(R.id.menu_3);
        craft4 = (Button)findViewById(R.id.menu_4);
        craft5 = (Button)findViewById(R.id.menu_5);
    }

    //OnClick method for craft beer button 1:
    public void Redirect1(View view) {

        Intent brewbot = new Intent(this, CraftOne.class);
        startActivity(brewbot);

    }

    //OnClick method for craft beer button 2:
    public void Redirect2(View view) {


        Intent garrick = new Intent(this, CraftTwo.class);
        startActivity(garrick);

    }

}
