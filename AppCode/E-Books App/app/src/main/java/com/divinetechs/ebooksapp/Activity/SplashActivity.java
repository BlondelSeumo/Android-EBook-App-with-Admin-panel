package com.divinetechs.ebooksapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.divinetechs.ebooksapp.Model.GeneralSettings.GeneralSettings;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.ConnectivityReceiver;
import com.divinetechs.ebooksapp.Utility.MutedVideoView;
import com.divinetechs.ebooksapp.Utility.MyApp;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.divinetechs.ebooksapp.Webservice.AppAPI;
import com.divinetechs.ebooksapp.Webservice.BaseURL;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private PrefManager prefManager;
    Intent mainIntent;

    private boolean ispaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        MyApp.getInstance().initAppLanguage(this);
        setContentView(R.layout.splash);
        PrefManager.forceRTLIfSupported(getWindow(), SplashActivity.this);
        prefManager = new PrefManager(SplashActivity.this);

        checkConnection();

        boolean isConnected = ConnectivityReceiver.isConnected();
        if (isConnected) {
            general_settings();

            MutedVideoView vView = (MutedVideoView) findViewById(R.id.video_view);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                    + R.raw.splash);

            if (vView != null) {
                vView.setVideoURI(video);
                vView.setZOrderOnTop(true);
                vView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
//                        jump();
                    }
                });

                vView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
//                        jump();
                        return false;
                    }
                });
                vView.start();
            } else {
//                jump();
            }
        }
    }


    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
//            message = "Good! Connected to Internet";
//            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;

            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        MyApp.getInstance().setConnectivityListener(this);
        if (ispaused) {
            jump();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ispaused = true;
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    private void general_settings() {
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<GeneralSettings> call = bookNPlayAPI.general_settings();
        call.enqueue(new Callback<GeneralSettings>() {
            @Override
            public void onResponse(Call<GeneralSettings> call, Response<GeneralSettings> response) {
                if (response.code() == 200) {

                    prefManager = new PrefManager(SplashActivity.this);

                    for (int i = 0; i < response.body().getResult().size(); i++) {
                        Log.e("==>", "" + response.body().getResult().get(i).getKey());
                        Log.e("==>", "" + response.body().getResult().get(i).getValue());
                        prefManager.setValue(response.body().getResult().get(i).getKey(), response.body().getResult().get(i).getValue());
                    }


                    if (!prefManager.isFirstTimeLaunch()) {
                        if (prefManager.getLoginId().equalsIgnoreCase("0"))
                            mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                        else
                            mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    } else {
                        Intent mainIntent = new Intent(SplashActivity.this, WelcomeActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }


                }
            }

            @Override
            public void onFailure(Call<GeneralSettings> call, Throwable t) {
            }
        });
    }

    private void jump() {
        if (!prefManager.isFirstTimeLaunch()) {
            if (prefManager.getLoginId().equalsIgnoreCase("0"))
                mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
            else
                mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        } else {
            Intent mainIntent = new Intent(SplashActivity.this, WelcomeActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }




}
