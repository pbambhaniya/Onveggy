package com.multipz.onveggy.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.multipz.onveggy.R;
import com.multipz.onveggy.Util.Config;
import com.multipz.onveggy.Util.Shared;

public class SplashActivity extends AppCompatActivity {

    Shared shared;
    Context context;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        context = this;
        shared = new Shared(context);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    if (shared.getBoolean(Config.isLogin, false)) {
                        intent = new Intent(SplashActivity.this, DrawerActivity.class);
                    }else {
                        intent = new Intent(SplashActivity.this,
                                WelcomeActivity.class);
                    }
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}
