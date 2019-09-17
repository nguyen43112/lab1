package com.example.thread;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SleepTime extends Activity implements View.OnClickListener {
    private EditText edtTime;
    private Button btnRun;
    private TextView tvResult;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sleeptime);
        edtTime = findViewById(R.id.edtTime);
        btnRun = findViewById(R.id.btnRun);
        tvResult = findViewById(R.id.tvResult);
        btnRun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.btnRun:
        AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(tvResult, edtTime, this);
        String sleeptime = edtTime.getText().toString();
        asyncTaskRunner.execute(sleeptime);
        break;
}

    }
}
