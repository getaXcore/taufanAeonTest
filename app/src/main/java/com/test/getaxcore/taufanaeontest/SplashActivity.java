package com.test.getaxcore.taufanaeontest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.test.getaxcore.taufanaeontest.database.PhotosDB;
import com.test.getaxcore.taufanaeontest.model.Photos;
import com.test.getaxcore.taufanaeontest.restapi.ApiClient;
import com.test.getaxcore.taufanaeontest.restapi.ApiInterface;
import com.test.getaxcore.taufanaeontest.service.BackgroundService;

import java.util.List;

import retrofit2.Callback;

public class SplashActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread welcomeThread = new Thread(){
            public void run(){
                try {
                    super.run();
                    sleep(1000); //delay 10 detik
                }catch (Exception e){

                }finally {
                    Intent i = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }


}
