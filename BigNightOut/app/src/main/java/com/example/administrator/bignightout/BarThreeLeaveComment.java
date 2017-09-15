package com.example.administrator.bignightout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 40083555 on 24/03/2017.
 */
public class BarThreeLeaveComment extends AppCompatActivity{

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ImageView add, chosenImage;
    private String userChosenTask, GetName, GetDetail, GetImage, GetPlace;
    private TextView msg, uriPath, deletephoto;

    private EditText name, detail;

    private String uploadUrl = "http://rmulholland15.students.cs.qub.ac.uk/upload.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barthreeleavecomment);

        add = (ImageView) findViewById(R.id.addphoto);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        chosenImage = (ImageView) findViewById(R.id.result);
        msg = (TextView) findViewById(R.id.photomessage);
        deletephoto = (TextView) findViewById(R.id.removephoto);
        uriPath = (TextView) findViewById(R.id.photopath);
        deletephoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        name = (EditText) findViewById(R.id.nameText);
        detail = (EditText) findViewById(R.id.detailText);

    }

    public Uri getUri(Context inContext, Bitmap bmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //bmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), bmap, "Title", null);
        return Uri.parse(path);
    }

    public void submit(View view) {

        GetDataForUpload();
        SendDataToServer(GetName, GetDetail, GetImage, GetPlace);

    }

    public void GetDataForUpload(){

        GetName = name.getText().toString();
        GetDetail = detail.getText().toString();
        GetImage = uriPath.getText().toString();
        GetPlace = "3";
    }

    public void SendDataToServer(final String name, final String detail, final String image, final String place){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String QuickName = name ;
                String QuickDetail = detail;
                String QuickImage = image;
                String QuickPlace = place;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("name", QuickName));
                nameValuePairs.add(new BasicNameValuePair("detail", QuickDetail));
                nameValuePairs.add(new BasicNameValuePair("image", QuickImage));
                nameValuePairs.add(new BasicNameValuePair("place", QuickPlace));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(uploadUrl);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Submit Successfully";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(BarThreeLeaveComment.this, "Comment posted.", Toast.LENGTH_LONG).show();
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, detail, image, place);
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

    //Camera set up starts here:
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {

                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(BarThreeLeaveComment.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(BarThreeLeaveComment.this);
                if (items[item].equals("Take Photo")) {
                    userChosenTask="Take Photo";
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChosenTask="Choose from Library";
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        Bitmap mini = Bitmap.createScaledBitmap(thumbnail, 508, 470, false);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        mini.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        chosenImage.setImageBitmap(mini);
        msg.setVisibility(View.GONE);
        deletephoto.setVisibility(View.VISIBLE);
        add.setVisibility(View.INVISIBLE);
        chosenImage.setVisibility(View.VISIBLE);

        //Getting Uri of the image:
        Uri path = getUri(getApplicationContext(), mini);
        String URI = path.toString();
        uriPath.setText(URI);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Bitmap mini = Bitmap.createScaledBitmap(bm, 508, 470, false);
        chosenImage.setImageBitmap(mini);
        msg.setVisibility(View.GONE);
        deletephoto.setVisibility(View.VISIBLE);
        add.setVisibility(View.INVISIBLE);
        chosenImage.setVisibility(View.VISIBLE);

        //Testing uri code:
        Uri path = getUri(getApplicationContext(), mini);
        String URI = path.toString();
        uriPath.setText(URI);
    }


}



