package com.mootarca.browsemate.browsemate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    View fullScreenView;
    TextView tv;
    AlertDialog.Builder adb;
    static final int TIME_OUT=1500;
    View view;
    FirebaseAuth fa;
    FirebaseUser fu;
    GoogleApiClient gac;
    String username,photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        view=findViewById(R.id.brand_name);
        fullScreenView=findViewById(R.id.brand_name);
        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/ARDESTINE.ttf");
        tv=(TextView)findViewById(R.id.brand_name);
        tv.setTypeface(tf);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                connectToNetwork();
            }
        },TIME_OUT);
    }

    /*This code enables Sticky Immersive screen without clearing flags on swipe.
        * */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {

            fullScreenView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }
    public boolean isNetConnected(){
        ConnectivityManager cm =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
    public void connectToNetwork(){
        boolean isNetworkAvailable=isNetConnected();
            if (!isNetworkAvailable){
                adb=new AlertDialog.Builder(this);
                adb.setMessage(R.string.splash_dialog_msg);
                adb.setTitle(R.string.splash_dialog_title);
                adb.setIcon(R.mipmap.app_icon);
                adb.setPositiveButton(R.string.splash_dialog_right, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_SETTINGS));
                    }
                });
                adb.setNegativeButton(R.string.splash_dialog_left, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        handlingWifi();
                    }
                });
                adb.show();
            }
        else {
                fa=FirebaseAuth.getInstance();
                fu=fa.getCurrentUser();
                GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
                gac=new GoogleApiClient.Builder(this).enableAutoManage(this,this)
                        .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
                if (fu==null) {
                    startActivity(new Intent(SplashScreen.this, SignInActivity.class));
                }
                else {
                    startActivity(new Intent(SplashScreen.this,HomeActivity.class));
                }
                finish();
            }
    }
    public void handlingWifi(){
        WifiManager wm=(WifiManager)getSystemService(WIFI_SERVICE);
        if (wm.isWifiEnabled()){
            Snackbar.make(view,R.string.not_wifi_snack, Snackbar.LENGTH_LONG)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Settings.ACTION_SETTINGS));
                        }
                    }).show();
        }
        else {
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
