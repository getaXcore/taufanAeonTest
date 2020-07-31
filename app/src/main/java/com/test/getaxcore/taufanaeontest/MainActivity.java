package com.test.getaxcore.taufanaeontest;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.test.getaxcore.taufanaeontest.service.BackgroundService;

public class MainActivity extends Activity {

    private BackgroundService backgroundService;
    private Context context = this;
    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundService = new BackgroundService(context);
        serviceIntent = new Intent(context,backgroundService.getClass());
        if (!isRunningService(backgroundService.getClass())){
            startService(serviceIntent);
            Toast.makeText(getApplicationContext(),"Background service is running...",Toast.LENGTH_LONG).show();
        }

    }

    private boolean isRunningService(Class<?> serviceClass){
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
            if (serviceClass.getName().equals(service.service.getClassName())){
                Log.i("isMyServiceRunning?", true+"");
                Toast.makeText(getApplicationContext(),"Background service is running...",Toast.LENGTH_LONG).show();
                return true;
            }
        }

        return false;
    }

}
