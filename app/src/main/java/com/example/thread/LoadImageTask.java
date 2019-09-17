package com.example.thread;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
    private Listener mListener;
    private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Download image....");
        progressDialog.show();
    }

    public LoadImageTask(Listener listener , Context context){
        mListener = listener;
        progressDialog = new ProgressDialog(context);

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        if (bitmap !=null){
            mListener.onImageLoaded(bitmap);
        }else {
            mListener.Onerror();
        }
    }

    @Override
    protected Bitmap doInBackground(String... paramas) {
        try {
            return BitmapFactory.decodeStream((InputStream)new URL(paramas[0]).getContent());

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
