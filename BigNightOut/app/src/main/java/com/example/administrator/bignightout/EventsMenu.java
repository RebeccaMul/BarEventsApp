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

public class EventsMenu extends MenuActivity{

    //Declaring buttons:
    Button event1;
    Button event2;
    Button event3;
    Button event4;
    Button event5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventmenulayout);

        //Initialising the button with existing button id:
        event1 = (Button)findViewById(R.id.menu_1);
        event2 = (Button)findViewById(R.id.menu_2);
        event3 = (Button)findViewById(R.id.menu_3);
        event4 = (Button)findViewById(R.id.menu_4);
        event5 = (Button)findViewById(R.id.menu_5);
    }

    //OnClick method for event button 1:
    public void ChristmasMarketRedirect(View view) {

       Intent christmasMarkets = new Intent(this, SeasonalEventOne.class);
       startActivity(christmasMarkets);

    }

    //OnClick method for event button 2:
    public void CultureNightRedirect(View view) {

           Intent cultureNight = new Intent(this, SeasonalEventTwo.class);
           startActivity(cultureNight);

    }


}
