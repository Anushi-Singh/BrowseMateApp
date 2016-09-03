package com.mootarca.browsemate.browsemate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    View fullScreenView;
    TextView tv;
    AlertDialog.Builder adb;
    static final int TIME_OUT=1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
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
            adb.setPositiveButton(R.string.splash_dialog_right,null);
            adb.setNegativeButton(R.string.splash_dialog_left,null);
            adb.show();
        }
    }
}
