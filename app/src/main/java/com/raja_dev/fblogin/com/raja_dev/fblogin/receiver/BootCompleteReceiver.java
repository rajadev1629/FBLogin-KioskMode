package com.raja_dev.fblogin.com.raja_dev.fblogin.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.raja_dev.fblogin.KioskModeActivity;

/**
 * Created by User on 12/30/2015.
 */

//Start KioskModeActivity whenever device is start from shutdown.

public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context, KioskModeActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }
}
