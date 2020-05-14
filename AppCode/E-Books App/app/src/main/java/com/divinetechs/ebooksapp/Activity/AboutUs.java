package com.divinetechs.ebooksapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.divinetechs.ebooksapp.Webservice.BaseURL;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import static com.squareup.picasso.Picasso.Priority.HIGH;

public class AboutUs extends AppCompatActivity {

    TextView txt_about_us, txt_app_name, txt_company, txt_email, txt_website, txt_mobile;
    TextView toolbar_title;
    Toolbar toolbar;

    LinearLayout ly_back;
    PrefManager prefManager;

    ImageView iv_app_icon;

    RelativeLayout rl_adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            //switch_theme.setChecked(true);
            setTheme(R.style.darktheme);
        } else {
            setTheme(R.style.AppTheme);
            getSupportActionBar().hide();
        }
        setContentView(R.layout.about_us);
        PrefManager.forceRTLIfSupported(getWindow(), AboutUs.this);
        prefManager = new PrefManager(AboutUs.this);

        toolbar = findViewById(R.id.toolbar);

        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.about_us));

        rl_adView = findViewById(R.id.rl_adView);
        txt_app_name = findViewById(R.id.txt_app_name);
        txt_company = findViewById(R.id.txt_company);
        txt_email = findViewById(R.id.txt_email);
        txt_website = findViewById(R.id.txt_website);
        txt_mobile = findViewById(R.id.txt_mobile);
        txt_about_us = findViewById(R.id.txt_about_us);

        iv_app_icon = findViewById(R.id.iv_app_icon);

        ly_back = findViewById(R.id.ly_back);
        ly_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        txt_app_name.setText(prefManager.getValue("app_name"));
        txt_company.setText(prefManager.getValue("Author"));
        txt_email.setText(prefManager.getValue("host_email"));
        txt_website.setText(prefManager.getValue("website"));
        txt_mobile.setText(prefManager.getValue("contact"));
        txt_about_us.setText(prefManager.getValue("app_desripation"));

        Picasso.with(AboutUs.this).load(BaseURL.Image_URL + "" + prefManager.getValue("app_logo")).priority(HIGH).into(iv_app_icon);

        if (prefManager.getValue("banner_ad").equalsIgnoreCase("yes")) {
            Admob();
            rl_adView.setVisibility(View.VISIBLE);
        } else {
            rl_adView.setVisibility(View.GONE);
        }

    }

    public void Admob() {

        try {
            AdView mAdView = new AdView(AboutUs.this);
            mAdView.setAdSize(AdSize.SMART_BANNER);
            mAdView.setAdUnitId(prefManager.getValue("banner_adid"));
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                }

                @Override
                public void onAdClosed() {
                    Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    Log.e("errorcode", "" + errorCode);
                    Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdLeftApplication() {
                    Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdOpened() {
                    super.onAdOpened();
                }
            });
            mAdView.loadAd(adRequest);

            ((RelativeLayout) rl_adView).addView(mAdView);
        } catch (Exception e) {
            Log.e("Exception=>", "" + e.getMessage());
        }
    }
}
