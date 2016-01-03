package com.raja_dev.fblogin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.pkmmte.view.CircularImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by User on 12/29/2015.
 */
public class FBLogin extends Activity {
    CircularImageView mCircularImageView;
    TextView txtName, txtDOB;
    LoginButton mLoginButton;
    CallbackManager mCallbackManager;
    private static String logString = "FACEBOOK LOGIN :: ";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook_login);
        //Initialise facebook SDK
        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

        mCircularImageView = (CircularImageView) findViewById(R.id.imageView);
        txtName = (TextView) findViewById(R.id.txt_name);
        txtDOB = (TextView) findViewById(R.id.txt_dob);
        txtName.setText("this is sample text");
        mLoginButton = (LoginButton) findViewById(R.id.login_button);
        mLoginButton.setReadPermissions("user_friends");


        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                updateUI();

            }

            @Override
            public void onCancel() {
                Log.v(logString,"Login cancle");

            }

            @Override
            public void onError(FacebookException error) {

                Log.e(logString, "error");
                error.printStackTrace();
            }
        });





        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "FBLogin Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.raja_dev.fblogin/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "FBLogin Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.raja_dev.fblogin/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI(){
        Profile profile = Profile.getCurrentProfile();

        txtName.setText(profile.getName());
          new FbImageAsync().execute(profile.getId());
    }

   class FbImageAsync extends AsyncTask<String, Void, Bitmap>{

       @Override
       protected Bitmap doInBackground(String... params) {
           try {
               Log.v(logString, params[0]);
               URL fbImageUrl = new URL("https://graph.facebook.com/" +params[0] + "/picture?type=large");
               Bitmap bitmap = BitmapFactory.decodeStream(fbImageUrl.openConnection().getInputStream());
               return bitmap;
           } catch (MalformedURLException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
           return  null;
       }

       @Override
       protected void onPostExecute(Bitmap bitmap) {
           super.onPostExecute(bitmap);
           if (bitmap != null){

               mCircularImageView.setImageBitmap(bitmap);
           }

       }
   }
}
