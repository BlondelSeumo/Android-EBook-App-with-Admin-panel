package com.divinetechs.ebooksapp.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.divinetechs.ebooksapp.Activity.AuthorAllActivity;
import com.divinetechs.ebooksapp.Activity.CategoryViewAll;
import com.divinetechs.ebooksapp.Activity.FeatureItemsViewAll;
import com.divinetechs.ebooksapp.Activity.FreeBookallview;
import com.divinetechs.ebooksapp.Activity.NewArrivalAll;
import com.divinetechs.ebooksapp.Activity.Paidbookallview;
import com.divinetechs.ebooksapp.Adapter.AuthorAdapter;
import com.divinetechs.ebooksapp.Adapter.CategoryAdapter;
import com.divinetechs.ebooksapp.Adapter.ContinueReadAdapter;
import com.divinetechs.ebooksapp.Adapter.FeatureAdapter;
import com.divinetechs.ebooksapp.Adapter.FreebookAdapter;
import com.divinetechs.ebooksapp.Adapter.NewArrivalAdapter;
import com.divinetechs.ebooksapp.Adapter.PaidBookAdapter;
import com.divinetechs.ebooksapp.Model.AuthorModel.AuthorModel;
import com.divinetechs.ebooksapp.Model.BookModel.BookModel;
import com.divinetechs.ebooksapp.Model.CategoryModel.CategoryModel;
import com.divinetechs.ebooksapp.Model.CategoryModel.Result;
import com.divinetechs.ebooksapp.Model.FreeBookModel.FreeBookModel;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.divinetechs.ebooksapp.Webservice.AppAPI;
import com.divinetechs.ebooksapp.Webservice.BaseURL;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    PrefManager prefManager;
    ProgressDialog progressDialog;

    TextView txt_viewall_new_arrival, txt_viewall_category, txt_viewall_item, txt_viewall_author, txt_viewall_continue, txt_viewall_paidbook, txt_viewall_freebook;

    List<Result> CategoryList;
    RecyclerView ry_category;
    CategoryAdapter categoryAdapter;

    List<com.divinetechs.ebooksapp.Model.BookModel.Result> NewArrivalList;
    RecyclerView rv_newarrival;
    NewArrivalAdapter newArrivalAdapter;

    List<com.divinetechs.ebooksapp.Model.FreeBookModel.Result> freebookList;
    RecyclerView rv_freebook;
    FreebookAdapter freebookAdapter;

    List<com.divinetechs.ebooksapp.Model.FreeBookModel.Result> paidbookList;
    RecyclerView rv_paidbook;
    PaidBookAdapter paidBookAdapter;

    List<com.divinetechs.ebooksapp.Model.BookModel.Result> FeatureList;
    RecyclerView rv_feature_item;
    FeatureAdapter featureAdapter;

    List<com.divinetechs.ebooksapp.Model.AuthorModel.Result> AuthorList;
    RecyclerView rv_author;
    AuthorAdapter authorAdapter;

    List<com.divinetechs.ebooksapp.Model.BookModel.Result> ContinueList;
    RecyclerView rv_continue;
    ContinueReadAdapter continueReadAdapter;
    LinearLayout ly_continue, ly_paid_book, ly_free_book, ly_author, ly_New_Arrival_Book, ly_top_reading_Book, ly_category;

    int progress_hide = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            //switch_theme.setChecked(true);
            getActivity().setTheme(R.style.darktheme);
        } else {
            getActivity().setTheme(R.style.AppTheme);
        }
        View root = inflater.inflate(R.layout.homefragment, container, false);

        prefManager = new PrefManager(getActivity());

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        ry_category = (RecyclerView) root.findViewById(R.id.ry_category);
        rv_newarrival = (RecyclerView) root.findViewById(R.id.rv_newarrival);
        rv_feature_item = (RecyclerView) root.findViewById(R.id.rv_feature_item);
        rv_author = (RecyclerView) root.findViewById(R.id.rv_author);
        rv_continue = (RecyclerView) root.findViewById(R.id.rv_continue);
        rv_freebook = root.findViewById(R.id.rv_freebook);
        rv_paidbook = root.findViewById(R.id.rv_paidbook);

        ly_continue = (LinearLayout) root.findViewById(R.id.ly_continue);
        ly_paid_book = root.findViewById(R.id.ly_paid_book);
        ly_free_book = root.findViewById(R.id.ly_free_book);
        ly_author = root.findViewById(R.id.ly_author);
        ly_New_Arrival_Book = root.findViewById(R.id.ly_New_Arrival_Book);
        ly_top_reading_Book = root.findViewById(R.id.ly_top_reading_Book);
        ly_category = root.findViewById(R.id.ly_category);


        txt_viewall_new_arrival = (TextView) root.findViewById(R.id.txt_viewall_new_arrival);
        txt_viewall_category = (TextView) root.findViewById(R.id.txt_viewall_category);
        txt_viewall_item = (TextView) root.findViewById(R.id.txt_viewall_item);
        txt_viewall_author = (TextView) root.findViewById(R.id.txt_viewall_author);
        txt_viewall_continue = (TextView) root.findViewById(R.id.txt_viewall_continue);
        txt_viewall_paidbook = root.findViewById(R.id.txt_viewall_paidbook);
        txt_viewall_freebook = root.findViewById(R.id.txt_viewall_freebook);
        txt_viewall_new_arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewArrivalAll.class));
            }
        });

        txt_viewall_freebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FreeBookallview.class));
            }
        });

        txt_viewall_paidbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Paidbookallview.class));
            }
        });

        txt_viewall_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CategoryViewAll.class));
            }
        });

        txt_viewall_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FeatureItemsViewAll.class));
            }
        });

        txt_viewall_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AuthorAllActivity.class));
            }
        });

        progress_hide = 0;

        Get_Category();
        FeatureItem();
        NewArrival();
        AuthorList();
        ContinueRead();
        freebook();
        paidbook();

        return root;
    }

    private void Get_Category() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<CategoryModel> call = bookNPlayAPI.categorylist();
        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                if (response.code() == 200) {
                    CategoryList = new ArrayList<>();
                    CategoryList = response.body().getResult();
                    Log.e("CategoryList", "" + CategoryList.size());
                    if (CategoryList.size() > 0) {
                        categoryAdapter = new CategoryAdapter(getActivity(), CategoryList, "Home");
                        ry_category.setHasFixedSize(true);
                        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.HORIZONTAL, false);
                        ry_category.setLayoutManager(mLayoutManager3);
                        ry_category.setItemAnimator(new DefaultItemAnimator());
                        ry_category.setAdapter(categoryAdapter);
                        categoryAdapter.notifyDataSetChanged();
                        ly_category.setVisibility(View.VISIBLE);
                        ry_category.setVisibility(View.VISIBLE);
                    } else {
                        ly_category.setVisibility(View.GONE);
                        ry_category.setVisibility(View.GONE);
                    }

                }
                progress_hide++;
                if (progress_hide >= 7)
                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void FeatureItem() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<BookModel> call = bookNPlayAPI.popularbooklist();
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                if (response.code() == 200) {

                    FeatureList = new ArrayList<>();
                    FeatureList = response.body().getResult();
                    Log.e("FeatureList", "" + FeatureList.size());
                    if (FeatureList.size() > 0) {
                        featureAdapter = new FeatureAdapter(getActivity(), FeatureList, "Home");
                        rv_feature_item.setHasFixedSize(true);
                        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.HORIZONTAL, false);
                        rv_feature_item.setLayoutManager(mLayoutManager3);
                        rv_feature_item.setItemAnimator(new DefaultItemAnimator());
                        rv_feature_item.setAdapter(featureAdapter);
                        featureAdapter.notifyDataSetChanged();
                        ly_top_reading_Book.setVisibility(View.VISIBLE);
                        rv_feature_item.setVisibility(View.VISIBLE);
                    } else {
                        ly_top_reading_Book.setVisibility(View.GONE);
                        rv_feature_item.setVisibility(View.GONE);
                    }
                }
                progress_hide++;
                if (progress_hide >= 7)
                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void NewArrival() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<BookModel> call = bookNPlayAPI.newarriaval();
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                if (response.code() == 200) {

                    NewArrivalList = new ArrayList<>();
                    NewArrivalList = response.body().getResult();
                    Log.e("NewArrivalList", "" + NewArrivalList.size());
                    if (NewArrivalList.size() > 0) {
                        newArrivalAdapter = new NewArrivalAdapter(getActivity(), NewArrivalList, "Home");
                        rv_newarrival.setHasFixedSize(true);
                        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.HORIZONTAL, false);
                        rv_newarrival.setLayoutManager(mLayoutManager3);
                        rv_newarrival.setItemAnimator(new DefaultItemAnimator());
                        rv_newarrival.setAdapter(newArrivalAdapter);
                        newArrivalAdapter.notifyDataSetChanged();
                        rv_newarrival.setVisibility(View.VISIBLE);
                        ly_New_Arrival_Book.setVisibility(View.VISIBLE);
                    } else {
                        rv_newarrival.setVisibility(View.GONE);
                        ly_New_Arrival_Book.setVisibility(View.GONE);
                    }
                }
                progress_hide++;
                if (progress_hide >= 7)
                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void AuthorList() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<AuthorModel> call = bookNPlayAPI.autherlist();
        call.enqueue(new Callback<AuthorModel>() {
            @Override
            public void onResponse(Call<AuthorModel> call, Response<AuthorModel> response) {
                if (response.code() == 200) {

                    AuthorList = new ArrayList<>();
                    AuthorList = response.body().getResult();
                    Log.e("AuthorList", "" + AuthorList.size());
                    if (AuthorList.size() > 0) {
                        authorAdapter = new AuthorAdapter(getActivity(), AuthorList);
                        rv_author.setHasFixedSize(true);
                        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.HORIZONTAL, false);
                        rv_author.setLayoutManager(mLayoutManager3);
                        rv_author.setItemAnimator(new DefaultItemAnimator());
                        rv_author.setAdapter(authorAdapter);
                        authorAdapter.notifyDataSetChanged();
                        rv_author.setVisibility(View.VISIBLE);
                        ly_author.setVisibility(View.VISIBLE);
                    } else {
                        rv_author.setVisibility(View.GONE);
                        ly_author.setVisibility(View.GONE);
                    }
                }
                progress_hide++;
                if (progress_hide >= 7)
                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<AuthorModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void ContinueRead() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<BookModel> call = bookNPlayAPI.continue_read(prefManager.getLoginId());
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                if (response.code() == 200) {

                    ContinueList = new ArrayList<>();
                    ContinueList = response.body().getResult();
                    Log.e("ContinueList", "" + ContinueList.size());

                    if (ContinueList.size() > 0) {
                        continueReadAdapter = new ContinueReadAdapter(getActivity(), ContinueList);
                        rv_continue.setHasFixedSize(true);
                        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.HORIZONTAL, false);
                        rv_continue.setLayoutManager(mLayoutManager3);
                        rv_continue.setItemAnimator(new DefaultItemAnimator());
                        rv_continue.setAdapter(continueReadAdapter);
                        continueReadAdapter.notifyDataSetChanged();
                        ly_continue.setVisibility(View.VISIBLE);
                        rv_continue.setVisibility(View.VISIBLE);
                    } else {
                        ly_continue.setVisibility(View.GONE);
                        rv_continue.setVisibility(View.GONE);
                    }
                }
                progress_hide++;
                if (progress_hide >= 7)
                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void freebook() {
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
                    Log.e("freebookList", "" + freebookList.size());
                    if (freebookList.size() > 0) {
                        freebookAdapter = new FreebookAdapter(getActivity(), freebookList, "Home");
                        rv_freebook.setHasFixedSize(true);
                        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.HORIZONTAL, false);
                        rv_freebook.setLayoutManager(mLayoutManager3);
                        rv_freebook.setItemAnimator(new DefaultItemAnimator());
                        rv_freebook.setAdapter(freebookAdapter);
                        freebookAdapter.notifyDataSetChanged();
                        rv_freebook.setVisibility(View.VISIBLE);
                        ly_free_book.setVisibility(View.VISIBLE);
                    } else {
                        rv_freebook.setVisibility(View.GONE);
                        ly_free_book.setVisibility(View.GONE);
                    }
                }
                progress_hide++;
                if (progress_hide >= 7)
                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<FreeBookModel> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("free_book_error", "" + t.getMessage());
            }
        });
    }

    private void paidbook() {
        progressDialog.show();
        AppAPI appAPI = BaseURL.getVideoAPI();
        Call<FreeBookModel> call = appAPI.free_paid_booklist("1");
        call.enqueue(new Callback<FreeBookModel>() {
            @Override
            public void onResponse(Call<FreeBookModel> call, Response<FreeBookModel> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    Log.e("paid_book_data", "" + response.body());
                    Log.e("paid_book_data", "" + response.body().getResult());
                    paidbookList = new ArrayList<>();
                    paidbookList = response.body().getResult();
                    Log.e("paidbookList", "" + paidbookList.size());
                    if (paidbookList.size() > 0) {
                        paidBookAdapter = new PaidBookAdapter(getActivity(), paidbookList, "Home");
                        rv_paidbook.setHasFixedSize(true);
                        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.HORIZONTAL, false);
                        rv_paidbook.setLayoutManager(mLayoutManager3);
                        rv_paidbook.setItemAnimator(new DefaultItemAnimator());
                        rv_paidbook.setAdapter(paidBookAdapter);
                        paidBookAdapter.notifyDataSetChanged();
                        rv_paidbook.setVisibility(View.VISIBLE);
                        ly_paid_book.setVisibility(View.VISIBLE);
                    } else {
                        rv_paidbook.setVisibility(View.GONE);
                        ly_paid_book.setVisibility(View.GONE);
                    }
                }
                progress_hide++;
                if (progress_hide >= 7)
                    progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<FreeBookModel> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("free_book_error", "" + t.getMessage());
            }
        });

    }
}

