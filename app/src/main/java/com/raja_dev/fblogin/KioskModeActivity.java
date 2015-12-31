package com.raja_dev.fblogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/30/2015.
 */
public class KioskModeActivity extends Activity {

    private static final String LOG_KEY = "KIOSKMODE";
    List<Integer> blockedKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        blockedKey = new ArrayList<>();
        blockedKey.add(KeyEvent.KEYCODE_VOLUME_DOWN);
        blockedKey.add(KeyEvent.KEYCODE_VOLUME_UP);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        setContentView(R.layout.facebook_login);
        Log.v(LOG_KEY, " we are on onCreate()");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.v(LOG_KEY, " we are on onBackPressed()");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!hasFocus){
            Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            sendBroadcast(closeDialog);
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (blockedKey.contains(event.getKeyCode())){
            return true;
        } else{
            return false;
        }
    }
}
