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

import com.divinetechs.ebooksapp.Adapter.AuthorBookAdapter;
import com.divinetechs.ebooksapp.Model.BookModel.BookModel;
import com.divinetechs.ebooksapp.Model.BookModel.Result;
import com.divinetechs.ebooksapp.Model.ReadDowncntModel.ReadDowncntModel;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.divinetechs.ebooksapp.Webservice.AppAPI;
import com.divinetechs.ebooksapp.Webservice.BaseURL;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.squareup.picasso.Picasso.Priority.HIGH;

public class AuthorBookList extends AppCompatActivity {

    RecyclerView rv_booklist;
    List<Result> BookList;
    AuthorBookAdapter authorBookAdapter;

    PrefManager prefManager;
    ProgressDialog progressDialog;
    String a_id, a_name, a_image, a_bio, a_address;

    TextView toolbar_title, txt_back, txt_author_book, txt_author_name, txt_author_location, txt_books_total;
    CircularImageView iv_thumb;
    TextView txt_view_book, txt_download_book;
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
        setContentView(R.layout.authorbooklist);
        PrefManager.forceRTLIfSupported(getWindow(), AuthorBookList.this);
        prefManager = new PrefManager(AuthorBookList.this);

        progressDialog = new ProgressDialog(AuthorBookList.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        rl_adView = findViewById(R.id.rl_adView);
        txt_author_book = (TextView) findViewById(R.id.txt_author_book);
        txt_author_name = (TextView) findViewById(R.id.txt_author_name);
        txt_author_location = (TextView) findViewById(R.id.txt_author_location);
        txt_books_total = (TextView) findViewById(R.id.txt_books_total);

        rv_booklist = (RecyclerView) findViewById(R.id.rv_booklist);
        iv_thumb = (CircularImageView) findViewById(R.id.image);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);

        txt_view_book = (TextView) findViewById(R.id.txt_view_book);
        txt_download_book = (TextView) findViewById(R.id.txt_download_book);

        txt_back = (TextView) findViewById(R.id.txt_back);
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            a_id = bundle.getString("a_id");
            a_name = bundle.getString("a_name");
            a_image = bundle.getString("a_image");
            a_bio = bundle.getString("a_bio");
            a_address = bundle.getString("a_address");

            Log.e("a_id", "" + a_id);
            Log.e("a_address", "" + a_address);

            toolbar_title.setText("" + a_name);
            txt_author_name.setText("" + a_name);
            txt_author_book.setText("" + a_name + "'s " + getResources().getString(R.string.Books));
            txt_author_location.setText("" + a_address);

            Picasso.with(AuthorBookList.this).load(a_image).priority(HIGH).into(iv_thumb);

            books_by_author();
            read_download_by_author();

        }

        if (prefManager.getValue("banner_ad").equalsIgnoreCase("yes")) {
            Admob();
            rl_adView.setVisibility(View.VISIBLE);
        } else {
            rl_adView.setVisibility(View.GONE);

        }

    }

    private void books_by_author() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<BookModel> call = bookNPlayAPI.books_by_author(a_id);
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                if (response.code() == 200) {

                    BookList = new ArrayList<>();
                    BookList = response.body().getResult();
                    Log.e("BookList", "" + BookList.size());

//                    Total Book count show
                    txt_books_total.setText("" + BookList.size());

                    authorBookAdapter = new AuthorBookAdapter(AuthorBookList.this, BookList);
                    rv_booklist.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(AuthorBookList.this,
                            LinearLayoutManager.HORIZONTAL, false);
//                    rv_booklist.setLayoutManager(mLayoutManager3);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(AuthorBookList.this, 3, LinearLayoutManager.VERTICAL, false);
                    rv_booklist.setLayoutManager(gridLayoutManager);
                    rv_booklist.setItemAnimator(new DefaultItemAnimator());
                    rv_booklist.setAdapter(authorBookAdapter);
                    authorBookAdapter.notifyDataSetChanged();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void read_download_by_author() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<ReadDowncntModel> call = bookNPlayAPI.readcnt_by_author(a_id);
        call.enqueue(new Callback<ReadDowncntModel>() {
            @Override
            public void onResponse(Call<ReadDowncntModel> call, Response<ReadDowncntModel> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus() == 200) {
                        txt_view_book.setText("" + response.body().getResult().get(0).getReadcount());
                        txt_download_book.setText("" + response.body().getResult().get(0).getDownload());
                    } else {
                        txt_view_book.setText("0");
                        txt_download_book.setText("0");
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ReadDowncntModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }


    public void Admob() {

        try {
            AdView mAdView = new AdView(AuthorBookList.this);
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
