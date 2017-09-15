package com.example.administrator.bignightout;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SeasonalEventTwo extends AppCompatActivity {

    //Declaring:
    Button about;
    Button maps;
    Button cmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seasonaltwoaboutlayout);

        //Initialising the login button with existing button id:
        about = (Button)findViewById(R.id.aboutbtn);
        maps = (Button)findViewById(R.id.mapbtn);
        cmt = (Button)findViewById(R.id.commentbtn);
    }

    public void AboutRedirect(View view) {

        Intent menuLink = new Intent(this, SeasonalEventTwo.class);
        startActivity(menuLink);

    }

    public void MapsRedirect(View view) {

        Intent menu2Link = new Intent(this, SeasonalEventTwoMaps.class);
        startActivity(menu2Link);

    }

    public void CommentsRedirect(View view) {

        Intent menu3Link = new Intent(this, SeasonalEventTwoComment.class);
        startActivity(menu3Link);

    }

    public void MainMenuRedirect(View view) {

        Intent menu3Link = new Intent(this, MenuActivity.class);
        startActivity(menu3Link);

    }

    public void fbClick(View view) {

        //Creating & setting up new intent - Link
        Intent Link = new Intent();
        Link.setAction(Intent.ACTION_VIEW);
        Link.addCategory(Intent.CATEGORY_BROWSABLE);
        //Setting Link intent's URL
        Link.setData(Uri.parse("https://www.facebook.com/CultureNightBelfast/"));
        //Starting intent (redirecting to URL)
        startActivity(Link);

    }

    public void twitClick(View view) {

        //Creating & setting up new intent - Link
        Intent Link = new Intent();
        Link.setAction(Intent.ACTION_VIEW);
        Link.addCategory(Intent.CATEGORY_BROWSABLE);
        //Setting Link intent's URL
        Link.setData(Uri.parse("https://twitter.com/CultureNightBelk"));
        //Starting intent (redirecting to URL)
        startActivity(Link);

    }

    public void instaClick(View view) {

        //Creating & setting up new intent - Link
        Intent Link = new Intent();
        Link.setAction(Intent.ACTION_VIEW);
        Link.addCategory(Intent.CATEGORY_BROWSABLE);
        //Setting Link intent's URL
        Link.setData(Uri.parse("https://www.instagram.com/culturenightbel/"));
        //Starting intent (redirecting to URL)
        startActivity(Link);

    }
}
