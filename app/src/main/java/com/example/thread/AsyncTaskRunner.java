package com.example.thread;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AsyncTaskRunner extends AsyncTask<String, String, String> {
private String resp;
ProgressDialog dialog;
TextView tvResult;
EditText time;
Context context;

    public AsyncTaskRunner( TextView tvResult, EditText time, Context context) {
        this.tvResult = tvResult;
        this.time = time;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
   publishProgress("Sleeing..............");
try {
    int time = Integer.parseInt(params[0]) *1000;
    Thread.sleep(time);
    resp= "sleep for " + params[0]+"Seconds";

}catch (Exception e){
    e.printStackTrace();
    resp=e.getMessage();
}
        return resp;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(dialog.isShowing()){
            dialog.dismiss();

        }
        tvResult.setText(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context,"ProgressDialog", "wait for"+time.getText().toString()+"seconds");
    }

}
