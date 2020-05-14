package com.divinetechs.ebooksapp.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.divinetechs.ebooksapp.Adapter.CommentAdapter;
import com.divinetechs.ebooksapp.Adapter.RelatedAdapter;
import com.divinetechs.ebooksapp.Model.BookModel.BookModel;
import com.divinetechs.ebooksapp.Model.BookModel.Result;
import com.divinetechs.ebooksapp.Model.CommentModel.CommentModel;
import com.divinetechs.ebooksapp.Model.SuccessModel.SuccessModel;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.DownloadEpub;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.divinetechs.ebooksapp.Webservice.AppAPI;
import com.divinetechs.ebooksapp.Webservice.BaseURL;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.squareup.picasso.Picasso.Priority.HIGH;

public class BookDetails extends AppCompatActivity {

    PrefManager prefManager;
    ProgressDialog progressDialog;

    String ID, fcat_id;
    Toolbar toolbar;

    RecyclerView rv_related;
    RelatedAdapter relatedAdapter;
    List<Result> RelatedList;

    List<Result> BookList;

    TextView txt_title, txt_price, txt_by_author, txt_category, txt_descripation,
            txt_read, txt_download_buy, txt_back, txt_bookmark;
    ImageView iv_thumb;
    EditText et_comment;
    LinearLayout ly_send;

    RecyclerView rv_comment;
    List<com.divinetechs.ebooksapp.Model.CommentModel.Result> CommentList;
    CommentAdapter commentAdapter;
    SimpleRatingBar ratingbar;
    private static final String PDF_DIRECTORY = "/Ebooks";

    RelativeLayout rl_adView, rl_native_adView;
    private InterstitialAd interstitial;

    String click_check = "";

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
        setContentView(R.layout.bookdetails);

        prefManager = new PrefManager(BookDetails.this);

        PrefManager.forceRTLIfSupported(getWindow(), BookDetails.this);


        progressDialog = new ProgressDialog(BookDetails.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        ratingbar = (SimpleRatingBar) findViewById(R.id.ratingbar);
        ratingbar.setOnRatingBarChangeListener(new SimpleRatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(SimpleRatingBar simpleRatingBar, float rating, boolean fromUser) {
                Log.e("rating", "" + rating);
                Addrating(rating);
            }
        });

        rl_adView = findViewById(R.id.rl_adView);
        ly_send = findViewById(R.id.txt_send);
        et_comment = findViewById(R.id.et_comment);
        txt_title = findViewById(R.id.txt_title);
        txt_price = findViewById(R.id.txt_price);
        txt_by_author = findViewById(R.id.txt_by_author);
        txt_category = findViewById(R.id.txt_category);
        txt_descripation = findViewById(R.id.txt_descripation);
        iv_thumb = findViewById(R.id.iv_thumb);
        txt_bookmark = findViewById(R.id.txt_bookmark);

        rv_comment = findViewById(R.id.rv_comment);
        rv_comment.setNestedScrollingEnabled(false);
        rv_related = findViewById(R.id.rv_related);
        txt_read = findViewById(R.id.txt_read);
        txt_download_buy = findViewById(R.id.txt_download_buy);
        txt_back = findViewById(R.id.txt_back);
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Book Details");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        // setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ID = bundle.getString("ID");
            Log.e("ID", "" + ID);
//            BookDetails();
        }

        ly_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_comment.getText().toString().length() > 0) {
                    AddComments("" + et_comment.getText().toString());
                }
            }
        });

        txt_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prefManager.isNetworkAvailable(BookDetails.this)) {
                    if (!prefManager.getLoginId().equalsIgnoreCase("0")) {
                        AddBookMark();
                    } else {
                        startActivity(new Intent(BookDetails.this, LoginActivity.class));
                    }
                } else {
                    Toast.makeText(BookDetails.this, getResources().getString(R.string.internet_connection), Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_by_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetails.this, AuthorBookList.class);
                intent.putExtra("a_id", BookList.get(0).getAId());
                intent.putExtra("a_name", BookList.get(0).getATitle());
                intent.putExtra("a_bio", BookList.get(0).getABio());
                intent.putExtra("a_image", BookList.get(0).getAImage());
                startActivity(intent);
            }
        });

        txt_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (prefManager.getValue("interstital_ad").equalsIgnoreCase("yes") &&
                            interstitial.isLoaded()) {
                        click_check = "Read";
                        interstitial.show();
                    } else {
                        ReadBook();
                    }
                } catch (Exception e) {
                    Log.e("Exception", "" + e.getMessage());
                }
            }
        });

        txt_download_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (prefManager.getValue("interstital_ad").equalsIgnoreCase("yes")
                        && interstitial.isLoaded()) {
                    click_check = "download";
                    interstitial.show();
                } else {
                    DownloadBook();
                }
            }
        });

        Log.e("banner_ad", "" + prefManager.getValue("banner_ad"));
        if (prefManager.getValue("banner_ad").equalsIgnoreCase("yes")) {
            Admob();
            rl_adView.setVisibility(View.VISIBLE);
        } else {
            rl_adView.setVisibility(View.GONE);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        BookDetails();

        Log.e("interstital_ad", "" + prefManager.getValue("interstital_ad"));
        if (prefManager.getValue("interstital_ad").equalsIgnoreCase("yes")) {
            rewardAds();
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("interstital_ad", "" + prefManager.getValue("interstital_ad"));
        if (prefManager.getValue("interstital_ad").equalsIgnoreCase("yes")) {
            rewardAds();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("ID", "" + ID);
        Log.e("Save-ID", "" + ID);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ID = savedInstanceState.getString("ID");
        Log.e("Restore-ID", "" + ID);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    private void BookDetails() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<BookModel> call = bookNPlayAPI.bookdetails(ID, prefManager.getLoginId());
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                if (response.code() == 200) {

                    Log.e("bookdetails", "" + response.body());
                    if (response.body().getResult().size() > 0) {

                        BookList = response.body().getResult();
                        fcat_id = response.body().getResult().get(0).getFcatId();

                        Log.e("fcat_id", "" + fcat_id);
                        //prefManager.setValue("price",""+);
                        txt_title.setText("" + BookList.get(0).getBTitle());
                        txt_price.setText("$" + BookList.get(0).getBPrice());
                        txt_by_author.setText("By " + BookList.get(0).getATitle());
                        txt_category.setText("" + BookList.get(0).getCatName());
                        txt_descripation.setText("" + BookList.get(0).getBDescription());

                        ratingbar.setRating(Float.parseFloat(BookList.get(0).getAvg_rating()));

                        if (BookList.get(0).getIsPaid().equalsIgnoreCase("1")) {
                            txt_download_buy.setText("$" + BookList.get(0).getBPrice());
                            txt_read.setText("Sample Book");
                        } else {
                            txt_read.setText("Read");
                            txt_download_buy.setText("Download");
                        }

                        Picasso.with(BookDetails.this).load(BookList.get(0).getBImage()).priority(HIGH).into(iv_thumb);

                        Related_Item();
                        Comments();
                        CheckBookMark();
                    }

                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void Related_Item() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<BookModel> call = bookNPlayAPI.related_item(fcat_id);
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                if (response.code() == 200) {

                    Log.e("Related_Item", "" + response);

                    RelatedList = new ArrayList<>();
                    RelatedList = response.body().getResult();
                    Log.e("Related_Item", "" + RelatedList.size());

                    relatedAdapter = new RelatedAdapter(BookDetails.this, RelatedList);
                    rv_related.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(BookDetails.this,
                            LinearLayoutManager.HORIZONTAL, false);
                    rv_related.setLayoutManager(mLayoutManager3);
                    rv_related.setItemAnimator(new DefaultItemAnimator());
                    rv_related.setAdapter(relatedAdapter);
                    relatedAdapter.notifyDataSetChanged();

                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void AddComments(String comment) {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<SuccessModel> call = bookNPlayAPI.add_comment(ID,
                prefManager.getLoginId(), comment);
        call.enqueue(new Callback<SuccessModel>() {
            @Override
            public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {
                progressDialog.dismiss();
                Log.e("Add Comments", "" + response.body().getMessage());
                Toast.makeText(BookDetails.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                et_comment.setText("");
                BookDetails();
            }

            @Override
            public void onFailure(Call<SuccessModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void Comments() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<CommentModel> call = bookNPlayAPI.view_comment(ID);
        call.enqueue(new Callback<CommentModel>() {
            @Override
            public void onResponse(Call<CommentModel> call, Response<CommentModel> response) {
                if (response.code() == 200) {

                    Log.e("Related_Item", "" + response);

                    CommentList = new ArrayList<>();
                    CommentList = response.body().getResult();
                    Log.e("CommentList", "" + CommentList.size());

                    commentAdapter = new CommentAdapter(BookDetails.this, CommentList);
                    rv_comment.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(BookDetails.this,
                            LinearLayoutManager.VERTICAL, false);
                    rv_comment.setLayoutManager(mLayoutManager3);
                    rv_comment.setItemAnimator(new DefaultItemAnimator());
                    rv_comment.setAdapter(commentAdapter);
                    commentAdapter.notifyDataSetChanged();

                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CommentModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void AddBookMark() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<SuccessModel> call = bookNPlayAPI.add_bookmark(prefManager.getLoginId(), ID);
        call.enqueue(new Callback<SuccessModel>() {
            @Override
            public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {
                if (response.code() == 200) {
                    Log.e("AddBookmark", "" + response);
                    Toast.makeText(BookDetails.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if (response.body().getStatus() == 200) {
                        txt_bookmark.setBackground(getResources().getDrawable(R.drawable.ic_bookmark_fill));
                    } else {
                        txt_bookmark.setBackground(getResources().getDrawable(R.drawable.ic_bookmark));
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<SuccessModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void CheckBookMark() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<SuccessModel> call = bookNPlayAPI.checkbookmark(prefManager.getLoginId(), ID);
        call.enqueue(new Callback<SuccessModel>() {
            @Override
            public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {
                if (response.code() == 200) {
                    Log.e("CheckBookMark", "" + response);
                    if (response.body().getStatus() == 200) {
                        txt_bookmark.setBackground(getResources().getDrawable(R.drawable.ic_bookmark_fill));
                    } else {
                        txt_bookmark.setBackground(getResources().getDrawable(R.drawable.ic_bookmark));
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<SuccessModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    public class DownloadBook extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        URL myFileUrl;
        String option;
        File file;

        DownloadBook(String option) {
            this.option = option;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(BookDetails.this, AlertDialog.THEME_HOLO_LIGHT);
            pDialog.setMessage(getResources().getString(R.string.downloading));
            pDialog.setIndeterminate(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                myFileUrl = new URL(args[0]);
                String path = myFileUrl.getPath();
                String fileName = path.substring(path.lastIndexOf('/') + 1);
                //   File dir = new File(Environment.getExternalStorageDirectory() + "/" + getString(R.string.app_name) + "/");
                File dir = new File(Environment.getExternalStorageDirectory() + "" + PDF_DIRECTORY);
//                dir.mkdirs();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                file = new File(dir, fileName);

                if (!file.exists()) {
                    HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    FileOutputStream fos = new FileOutputStream(file);
                    byte data[] = new byte[4096];
                    int count;
                    while ((count = is.read(data)) != -1) {
                        if (isCancelled()) {
                            is.close();
                            return null;
                        }
                        fos.write(data, 0, count);
                    }
                    fos.flush();
                    fos.close();

                    if (option.equals("save")) {
                        MediaScannerConnection.scanFile(BookDetails.this, new String[]{file.getAbsolutePath()},
                                null,
                                new MediaScannerConnection.OnScanCompletedListener() {
                                    @Override
                                    public void onScanCompleted(String path, Uri uri) {

                                    }
                                });
                    }
                    return "1";
                } else {
                    return "2";
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception", "" + e.getMessage());
                return "0";
            }
        }

        @Override
        protected void onPostExecute(String args) {
            if (args.equals("1") || args.equals("2")) {
                switch (option) {
                    case "save":
                        if (args.equals("2")) {
                            Toast.makeText(BookDetails.this, "Already Download", Toast.LENGTH_SHORT).show();
                        } else {
                            AddDownload();
                            Toast.makeText(BookDetails.this, "Book Download success", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            } else {
                Toast.makeText(BookDetails.this, "Please try again.", Toast.LENGTH_SHORT).show();
            }
            pDialog.dismiss();
        }
    }

    private void Addrating(Float aFloat) {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<SuccessModel> call = bookNPlayAPI.give_rating(prefManager.getLoginId(), ID, "" + aFloat);
        call.enqueue(new Callback<SuccessModel>() {
            @Override
            public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {
                if (response.code() == 200) {
                    Log.e("AddRating", "" + response);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<SuccessModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void DownloadBook() {

        if (prefManager.isNetworkAvailable(BookDetails.this)) {
            if (!prefManager.getLoginId().equalsIgnoreCase("0")) {
                if (BookList.get(0).getIsPaid().equalsIgnoreCase("1")) {

                    Intent intent = new Intent(BookDetails.this, AllPaymentActivity.class);
                    intent.putExtra("bookprice", "" + BookList.get(0).getBPrice());
                    intent.putExtra("bookid", "" + BookList.get(0).getBId());
                    intent.putExtra("booktitle", "" + BookList.get(0).getBTitle());
                    intent.putExtra("bookdesc", "" + BookList.get(0).getBDescription());
                    intent.putExtra("bookdate", "" + BookList.get(0).getBDate());
                    startActivity(intent);
                } else {
                    if (BookList.get(0).getBUrl().contains(".epub")) {
                        new DownloadBook("save").execute(BookList.get(0).getBUrl());
                    } else {
                        new DownloadBook("save").execute(BookList.get(0).getBUrl());
                    }
                }
            } else {
                startActivity(new Intent(BookDetails.this, LoginActivity.class));
            }
        } else {
            Toast.makeText(BookDetails.this, getResources().getString(R.string.internet_connection), Toast.LENGTH_SHORT).show();
        }
    }

    private void ReadBook() {
        try {
            if (prefManager.isNetworkAvailable(BookDetails.this)) {
                Log.e("url_data", "" + BookList.get(0).getBUrl().contains(".epub"));
                if (BookList.get(0).getBUrl().contains(".epub")) {
                    if (txt_read.getText().toString().equalsIgnoreCase("Sample Book")) {
                        if (prefManager.getLoginId().equalsIgnoreCase("0")) {
                            startActivity(new Intent(BookDetails.this, LoginActivity.class));
                        } else {
                            DownloadEpub downloadEpub = new DownloadEpub(BookDetails.this);
                            Log.e("path_pr", "" + BookList.get(0).getSampleBUrl());
                            Log.e("path_pr_id", "" + BookList.get(0).getBId());
                            downloadEpub.pathEpub(BaseURL.pdf_URL + BookList.get(0).getSampleBUrl(), BookList.get(0).getBId());
                        }
                    } else {

                        Log.e("==>", "" + BookList.get(0).getBUrl());
                        Log.e("==>ID", "" + BookList.get(0).getBId());

                        if (!prefManager.getLoginId().equalsIgnoreCase("0")) {
                            DownloadEpub downloadEpub = new DownloadEpub(BookDetails.this);
                            downloadEpub.pathEpub(BookList.get(0).getBUrl(), BookList.get(0).getBId());
                        } else {
                            startActivity(new Intent(BookDetails.this, LoginActivity.class));
                        }

                    }
                } else if (BookList.get(0).getBUrl().contains(".pdf")) {
                    if (txt_read.getText().toString().equalsIgnoreCase("Sample Book")) {
                        startActivity(new Intent(BookDetails.this, PDFShow.class)
                                .putExtra("link", BookList.get(0).getSampleBUrl())
                                .putExtra("toolbarTitle", BookList.get(0).getBTitle())
                                .putExtra("type", "link"));
                    } else {
                        startActivity(new Intent(BookDetails.this, PDFShow.class)
                                .putExtra("link", BookList.get(0).getBUrl())
                                .putExtra("toolbarTitle", BookList.get(0).getBTitle())
                                .putExtra("type", "link"));
                    }
                }
            } else {
                Toast.makeText(BookDetails.this, getResources().getString(R.string.internet_connection), Toast.LENGTH_SHORT).show();
            }
        } catch (
                Exception e) {
            Log.e("Exception-Read", "" + e.getMessage());
        }
    }

    public void Admob() {

        try {
            AdView mAdView = new AdView(BookDetails.this);
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

    private void rewardAds() {
        interstitial = new InterstitialAd(BookDetails.this);
        interstitial.setAdUnitId(prefManager.getValue("interstital_adid"));
        interstitial.loadAd(new AdRequest.Builder().build());
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.e("OnLoaded", "Onload ads");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e("onAdFailedToLoad2=>", "" + errorCode);
            }

            @Override
            public void onAdOpened() {
            }

            @Override
            public void onAdLeftApplication() {
            }

            @Override
            public void onAdClosed() {
                if (click_check.equalsIgnoreCase("download")) {
                    DownloadBook();
                } else if (click_check.equalsIgnoreCase("Read")) {
                    ReadBook();
                }
            }
        });
    }

    private void AddDownload() {
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<SuccessModel> call = bookNPlayAPI.add_download(prefManager.getLoginId(), ID);
        call.enqueue(new Callback<SuccessModel>() {
            @Override
            public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {
                if (response.code() == 200) {
                    Log.e("AddBookmark", "" + response);
                }
            }

            @Override
            public void onFailure(Call<SuccessModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}
