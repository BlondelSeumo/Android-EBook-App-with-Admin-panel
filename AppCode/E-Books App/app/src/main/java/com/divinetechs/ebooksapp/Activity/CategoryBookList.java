package com.divinetechs.ebooksapp.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.divinetechs.ebooksapp.Adapter.CategoryBookAdapter;
import com.divinetechs.ebooksapp.Model.BookModel.BookModel;
import com.divinetechs.ebooksapp.Model.BookModel.Result;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.divinetechs.ebooksapp.Webservice.AppAPI;
import com.divinetechs.ebooksapp.Webservice.BaseURL;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryBookList extends AppCompatActivity {

    RecyclerView rv_booklist;
    List<Result> BookList;
    CategoryBookAdapter categoryBookAdapter;

    PrefManager prefManager;
    ProgressDialog progressDialog;
    String cat_id, cat_name, cat_image;

    TextView toolbar_title, txt_back;
    RoundedImageView iv_thumb;
    LinearLayout ly_no_data;
    TextView txt_no_record;
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
        setContentView(R.layout.categorybooklist);
        PrefManager.forceRTLIfSupported(getWindow(), CategoryBookList.this);
        prefManager = new PrefManager(CategoryBookList.this);

        progressDialog = new ProgressDialog(CategoryBookList.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        rl_adView = findViewById(R.id.rl_adView);

        ly_no_data = (LinearLayout) findViewById(R.id.ly_no_data);
        txt_no_record = (TextView) findViewById(R.id.txt_no_record);

        rv_booklist = (RecyclerView) findViewById(R.id.rv_booklist);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);

        txt_back = (TextView) findViewById(R.id.txt_back);
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            cat_id = bundle.getString("cat_id");
            cat_name = bundle.getString("cat_name");
            cat_image = bundle.getString("cat_image");
            Log.e("cat_id", "" + cat_id);
            toolbar_title.setText("" + cat_name);
            books_by_category();
        }

        if (prefManager.getValue("banner_ad").equalsIgnoreCase("yes")) {
            Admob();
            rl_adView.setVisibility(View.VISIBLE);
        } else {
            rl_adView.setVisibility(View.GONE);

        }

    }

    private void books_by_category() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<BookModel> call = bookNPlayAPI.books_by_category(cat_id);
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                if (response.code() == 200) {

                    BookList = new ArrayList<>();
                    BookList = response.body().getResult();
                    Log.e("BookList", "" + BookList.size());

                    if (BookList.size() > 0) {
                        categoryBookAdapter = new CategoryBookAdapter(CategoryBookList.this, BookList);
                        rv_booklist.setHasFixedSize(true);
                        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(CategoryBookList.this,
                                LinearLayoutManager.HORIZONTAL, false);
//                    rv_booklist.setLayoutManager(mLayoutManager3);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(CategoryBookList.this, 3, LinearLayoutManager.VERTICAL, false);
                        rv_booklist.setLayoutManager(gridLayoutManager);
                        rv_booklist.setItemAnimator(new DefaultItemAnimator());
                        rv_booklist.setAdapter(categoryBookAdapter);
                        categoryBookAdapter.notifyDataSetChanged();

                        ly_no_data.setVisibility(View.GONE);
                        rv_booklist.setVisibility(View.VISIBLE);

                    } else {
                        txt_no_record.setText("" + Html.fromHtml(response.body().getMessage()));
                        ly_no_data.setVisibility(View.VISIBLE);
                        rv_booklist.setVisibility(View.GONE);
                    }
                } else {
                    txt_no_record.setText("" + Html.fromHtml(response.body().getMessage()));
                    ly_no_data.setVisibility(View.VISIBLE);
                    rv_booklist.setVisibility(View.GONE);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public void Admob() {

        try {
            AdView mAdView = new AdView(CategoryBookList.this);
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
