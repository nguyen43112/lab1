package com.example.thread;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class asyncsceen extends Activity implements View.OnClickListener, Listener {

    private TextView Tv;
    private ImageView im;
    private Button button, bt4;
    private ProgressDialog progressDialog;
    private Bitmap bitmap =null;
    public static final String IMAGE_URL ="https://ap.poly.edu.vn/user/ph/PH06778.jpg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        Tv = findViewById(R.id.tv1);
        im= findViewById(R.id.img1);
        button= findViewById(R.id.btn1);
        button.setOnClickListener(this);
        bt4= findViewById(R.id.button3);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openb4();
            }
        });
    }

    private void openb4() {
        Intent intent = new Intent(this, SleepTime.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.btn1:
              new LoadImageTask(this,this).execute(IMAGE_URL);
              break;
      }
    }

    private Bitmap downloadBitmap(String link) {
        try {
            URL url= new URL(link);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private Handler messageHandler = new Handler(){
        public void handleMessage(Message m){
            super.handleMessage(m);
            Bundle bundle = m.getData();
            String  message = bundle.getString("message");
            Tv.setText(message);
            im.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    };

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        im.setImageBitmap(bitmap);
        Tv.setText("image downloaded");
    }

    @Override
    public void Onerror() {
        Tv.setText("error download image");
    }
}




