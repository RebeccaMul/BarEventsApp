package com.example.administrator.bignightout;

/**
 * Created by 40083555 on 20/04/2017.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BarTwo extends AppCompatActivity {

    //Declaring:
    Button about;
    Button maps;
    Button cmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bartwoaboutlayout);

        //Initialising the login button with existing button id:
        about = (Button)findViewById(R.id.aboutbtn);
        maps = (Button)findViewById(R.id.mapbtn);
        cmt = (Button)findViewById(R.id.commentbtn);
    }

    public void AboutRedirect(View view) {

        Intent menuLink = new Intent(this, BarTwo.class);
        startActivity(menuLink);

    }

    public void MapsRedirect(View view) {

        Intent menu2Link = new Intent(this, BarTwoMaps.class);
        startActivity(menu2Link);

    }

    public void CommentsRedirect(View view) {

        Intent menu3Link = new Intent(this, BarTwoComment.class);
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
        Link.setData(Uri.parse("https://www.facebook.com/limelightbelfast"));
        //Starting intent (redirecting to URL)
        startActivity(Link);

    }

    public void twitClick(View view) {

        //Creating & setting up new intent - Link
        Intent Link = new Intent();
        Link.setAction(Intent.ACTION_VIEW);
        Link.addCategory(Intent.CATEGORY_BROWSABLE);
        //Setting Link intent's URL
        Link.setData(Uri.parse("https://twitter.com/LimelightNI"));
        //Starting intent (redirecting to URL)
        startActivity(Link);

    }

    public void instaClick(View view) {

        //Creating & setting up new intent - Link
        Intent Link = new Intent();
        Link.setAction(Intent.ACTION_VIEW);
        Link.addCategory(Intent.CATEGORY_BROWSABLE);
        //Setting Link intent's URL
        Link.setData(Uri.parse("https://www.instagram.com/limelight.belfast/"));
        //Starting intent (redirecting to URL)
        startActivity(Link);

    }
}

