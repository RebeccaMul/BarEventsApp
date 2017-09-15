package com.example.administrator.bignightout;

/**
 * Created by 40083555 on 14/03/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CocktailsMenu extends MenuActivity{

    //Declaring buttons:
    Button cocktail1;
    Button cocktail2;
    Button cocktail3;
    Button cocktail4;
    Button cocktail5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cocktailmenulayout);

        //Initialising the button with existing button id:
        cocktail1 = (Button)findViewById(R.id.menu_1);
        cocktail2 = (Button)findViewById(R.id.menu_2);
        cocktail3 = (Button)findViewById(R.id.menu_3);
        cocktail4 = (Button)findViewById(R.id.menu_4);
        cocktail5 = (Button)findViewById(R.id.menu_5);
    }

    //OnClick method for cocktail button 1:
    public void Redirect1(View view) {

        Intent filthys = new Intent(this, CocktailOne.class);
        startActivity(filthys);

    }

    //OnClick method for cocktail button 2:
    public void Redirect2(View view) {

           Intent merchant = new Intent(this, CocktailTwo.class);
           startActivity(merchant);

    }

    //OnClick method for cocktail button 3:
    public void Redirect3(View view) {

        //   Intent whig = new Intent(this, CocktailThree.class);
        //   startActivity(whig);

    }


}
