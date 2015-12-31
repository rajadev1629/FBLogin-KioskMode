package com.raja_dev.fblogin.com.raja_dev.fblogin.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.preference.PreferenceManager;

import com.raja_dev.fblogin.AppContext;

/**
 * Created by User on 12/30/2015.
 */

//Used to detect when we press short power button press (Screen off)
    //Aquire a wake lock when short power button pressed. It can work only if your app is in foregrond

public class OnScreenOffReceiver extends BroadcastReceiver {

    private static final String PREF_KIOSK_MODE = "pref_kiosk_mode";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.ACTION_SCREEN_OFF.equals(intent.getAction())){
            AppContext ctx = (AppContext) context.getApplicationContext();
            if (isKioskyModeActive(ctx)){
                wakeLockDevice(ctx);
            }
        }
    }

    private void wakeLockDevice(AppContext context){
        PowerManager.WakeLock wakeLock = context.getWakeLock();
        if(wakeLock.isHeld()){
            wakeLock.release();
        }
        wakeLock.acquire();
        wakeLock.release();
    }

    private boolean isKioskyModeActive(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(PREF_KIOSK_MODE, false);
    }
}
