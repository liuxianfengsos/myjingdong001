package com.myjingdong001;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG ="WelcomeActivity" ;
    private Handler handler;
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                //UploadActivity
                //startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                startActivity(new Intent(WelcomeActivity.this,UploadActivity.class));
                finish();
            }
        };
        handler.postDelayed(runnable,5000);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        //Log.i(TAG,"移除runable");
    }
}