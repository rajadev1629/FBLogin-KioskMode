package com.raja_dev.fblogin;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

import com.raja_dev.fblogin.com.raja_dev.fblogin.receiver.OnScreenOffReceiver;
import com.raja_dev.fblogin.com.raja_dev.fblogin.services.KioskService;

/**
 * Created by User on 12/30/2015.
 */
public class AppContext extends Application {

    private AppContext instance;
    private PowerManager.WakeLock wakeLock;
    private OnScreenOffReceiver onScreenOffReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerKioskyModeReceiver();

    }

    private void registerKioskyModeReceiver(){
        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        onScreenOffReceiver = new OnScreenOffReceiver();
        registerReceiver(onScreenOffReceiver, filter);
        startKioskService();
    }

    public PowerManager.WakeLock getWakeLock(){
        if(wakeLock == null){
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "wakeup");
            //Use below method to work with latest API.
            //Pass a Activity refrence to the Application class and return a instance of Wakelock.
            /*
            * getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            *
            * void someMethodThatUsesActivity(Activity myActivityReference) {
            * myActivityReference.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            * WindowManager.LayoutParams.FLAG_FULLSCREEN); }
            *
            * */
        }
        return wakeLock;
    }
    private void startKioskService(){
        startService(new Intent(this, KioskService.class));
    }
}
