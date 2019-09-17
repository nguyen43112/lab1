package com.example.thread;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
import android.os.Handler;
        import android.os.Message;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView Tv;
    private ImageView im;
    private Button button, bt1;
    private ProgressDialog progressDialog;
    private String url = "https://ap.poly.edu.vn/user/ph/PH06741.jpg";
    private Bitmap bitmap =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tv = findViewById(R.id.tv);
        im= findViewById(R.id.img);
        button= findViewById(R.id.btn);
        button.setOnClickListener(this);
        bt1 = findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openasynceen();
            }
        });
    }

    private void openasynceen() {
        Intent intent = new Intent(this, asyncsceen.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
       progressDialog = ProgressDialog.show(MainActivity.this,"","Dowload......");
       Runnable aRunnable = new Runnable() {
           @Override
           public void run() {
               bitmap = downloadBitmap(url);
               Message m =messageHandler.obtainMessage();
               Bundle bundle = new Bundle();
               String ThreadMessage = "anh tai xuong";
               bundle.putString("message",ThreadMessage);
               m.setData(bundle);
               messageHandler.sendMessage(m);


           }
       };
       Thread athread = new Thread(aRunnable);
       athread.start();
    }

    private Bitmap downloadBitmap(String link) {
        try {
            URL url= new URL(link);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap =BitmapFactory.decodeStream(inputStream);
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

    }

