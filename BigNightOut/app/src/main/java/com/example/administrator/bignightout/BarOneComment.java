package com.example.administrator.bignightout;

/**
 * Created by 40083555 on 24/03/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BarOneComment extends AppCompatActivity {

    //Declaring:
    Button about, maps, cmt, leavecmt;
    ListView lv;
    ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> commentList;
    String url = "http://rmulholland15.students.cs.qub.ac.uk/comments.php";
    String TAG = BarOneComment.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baronecommentlayout);

        //Rebecca - Initialising the buttons with existing button id:
        about = (Button)findViewById(R.id.aboutbtn);
        maps = (Button)findViewById(R.id.mapbtn);
        cmt = (Button)findViewById(R.id.commentbtn);
        leavecmt = (Button)findViewById(R.id.leavecommentbtn);

        //listview and JSON set up:
        commentList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new GetComments().execute();

    }

    public void AboutRedirect(View view) {
        Intent menuLink = new Intent(this, BarOne.class);
        startActivity(menuLink);
    }

    public void MapsRedirect(View view) {
        Intent menu2Link = new Intent(this, BarOneMaps.class);
        startActivity(menu2Link);
    }

    public void CommentsRedirect(View view) {
        Intent menu3Link = new Intent(this, BarOneComment.class);
        startActivity(menu3Link);
    }

    public void commentClick(View view) {
        Intent menu4Link = new Intent(this, BarOneLeaveComment.class);
        startActivity(menu4Link);
    }

    //Rebecca - Get comment method returns any comments stored for this Venue's Id in the database, using php mySQL and JSON parsing
    private class GetComments extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(BarOneComment.this);
            pDialog.setMessage("Loading Comments...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr == null) {
                Toast.makeText(getApplicationContext(), "There are no comments for this destination.", Toast.LENGTH_LONG).show();
            }

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray commentsreturned = jsonObj.getJSONArray("allcomments");

                    // looping through All Comments
                    for (int i = 0; i < commentsreturned.length(); i++) {
                        JSONObject c = commentsreturned.getJSONObject(i);

                        String imgpath = c.getString("imgpath");
                        String name = c.getString("name");
                        String detail = c.getString("detail");

                        // tmp hash map for single comment
                        HashMap<String, String> singlecomment = new HashMap<>();

                        // adding each child node to HashMap key => value
                        singlecomment.put("imgpath", imgpath);
                        singlecomment.put("name", name);
                        singlecomment.put("detail", detail);

                        // adding the single comment to comment list array
                        commentList.add(singlecomment);

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Couldn't get json from server.", Toast.LENGTH_LONG).show();
                    }
                });

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //    Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            String[] from = {"name", "detail", "imgpath"};
            int[] to = {R.id.name, R.id.detail, R.id.profilepic};

            //Updating parsed JSON data into ListView
            SimpleAdapter adapter = new SimpleAdapter(BarOneComment.this, commentList,
                    R.layout.commentlistitem, from, to);
            lv.setAdapter(adapter);

        }

    }

}




