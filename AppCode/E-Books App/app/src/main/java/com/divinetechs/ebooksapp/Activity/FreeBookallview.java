package com.divinetechs.ebooksapp.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.divinetechs.ebooksapp.Adapter.FreebookAdapter;
import com.divinetechs.ebooksapp.Model.FreeBookModel.FreeBookModel;
import com.divinetechs.ebooksapp.Model.FreeBookModel.Result;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.divinetechs.ebooksapp.Webservice.AppAPI;
import com.divinetechs.ebooksapp.Webservice.BaseURL;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeBookallview extends AppCompatActivity {

    List<Result> freebookList;
    RecyclerView rv_freebook;
    FreebookAdapter freebookAdapter;

    PrefManager prefManager;
    ProgressDialog progressDialog;
    String a_id, a_name, a_image, a_bio;

    TextView toolbar_title, txt_back;
    int number;
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

        setContentView(R.layout.freepaidallview);
        PrefManager.forceRTLIfSupported(getWindow(), FreeBookallview.this);
        prefManager = new PrefManager(FreeBookallview.this);

        progressDialog = new ProgressDialog(FreeBookallview.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        rl_adView = findViewById(R.id.rl_adView);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("" + getResources().getString(R.string.Free_Book));
        rv_freebook = (RecyclerView) findViewById(R.id.rv_free_paid_book);

        txt_back = (TextView) findViewById(R.id.txt_back);
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Free_all_books();

        if (prefManager.getValue("banner_ad").equalsIgnoreCase("yes")) {
            Admob();
            rl_adView.setVisibility(View.VISIBLE);
        } else {
            rl_adView.setVisibility(View.GONE);
        }

    }

    private void Free_all_books() {
        progressDialog.show();
        AppAPI appAPI = BaseURL.getVideoAPI();
        Call<FreeBookModel> call = appAPI.free_paid_booklist("0");
        call.enqueue(new Callback<FreeBookModel>() {
            @Override
            public void onResponse(Call<FreeBookModel> call, Response<FreeBookModel> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    Log.e("free_book_data", "" + response);
                    Log.e("free_book_data", "" + response.body().getResult());
                    freebookList = new ArrayList<>();
                    freebookList = response.body().getResult();
                    freebookAdapter = new FreebookAdapter(FreeBookallview.this, freebookList, "Home");
                    rv_freebook.setHasFixedSize(true);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(FreeBookallview.this, 3, LinearLayoutManager.VERTICAL, false);
                    rv_freebook.setLayoutManager(gridLayoutManager);
                    rv_freebook.setItemAnimator(new DefaultItemAnimator());
                    rv_freebook.setAdapter(freebookAdapter);
                    freebookAdapter.notifyDataSetChanged();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<FreeBookModel> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("free_book_error", "" + t.getMessage());
            }
        });
    }

    public void Admob() {

        try {
            AdView mAdView = new AdView(FreeBookallview.this);
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
