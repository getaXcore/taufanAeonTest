package com.test.getaxcore.taufanaeontest.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 001910947 on 7/31/2020.
 */

public class BackgroundRestarterBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        context.startService(new Intent(context,BackgroundService.class));
    }
}
