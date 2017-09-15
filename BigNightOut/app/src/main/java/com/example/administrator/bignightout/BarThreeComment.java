package com.example.administrator.bignightout;

/**
 * Created by 40083555 on 24/03/2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class BarThreeComment extends AppCompatActivity {

    //Declaring:
    Button about, maps, cmt, leavecmt;
    ListView lv;
    ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> commentList;
    String url = "http://rmulholland15.students.cs.qub.ac.uk/barthree.php";
    String TAG = BarThreeComment.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barthreecommentlayout);

        //Initialising the buttons with existing button id:
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
        Intent menuLink = new Intent(this, BarThree.class);
        startActivity(menuLink);
    }

    public void MapsRedirect(View view) {
        Intent menu2Link = new Intent(this, BarThreeMaps.class);
        startActivity(menu2Link);
    }

    public void CommentsRedirect(View view) {
        Intent menu3Link = new Intent(this, BarThreeComment.class);
        startActivity(menu3Link);
    }

    public void commentClick(View view) {
        Intent menu4Link = new Intent(this, BarThreeLeaveComment.class);
        startActivity(menu4Link);
    }

    private class GetComments extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(BarThreeComment.this);
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
            SimpleAdapter adapter = new SimpleAdapter(BarThreeComment.this, commentList,
                    R.layout.commentlistitem, from, to);
            lv.setAdapter(adapter);

        }

    }

}




