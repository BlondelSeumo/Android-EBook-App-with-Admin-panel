package com.divinetechs.ebooksapp.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.divinetechs.ebooksapp.Activity.AuthorAllActivity;
import com.divinetechs.ebooksapp.Activity.CategoryViewAll;
import com.divinetechs.ebooksapp.Activity.FeatureItemsViewAll;
import com.divinetechs.ebooksapp.Activity.NewArrivalAll;
import com.divinetechs.ebooksapp.Adapter.AuthorAdapter;
import com.divinetechs.ebooksapp.Adapter.CategoryAdapter;
import com.divinetechs.ebooksapp.Adapter.ContinueReadAdapter;
import com.divinetechs.ebooksapp.Adapter.FeatureAdapter;
import com.divinetechs.ebooksapp.Adapter.NewArrivalAdapter;
import com.divinetechs.ebooksapp.Model.AuthorModel.AuthorModel;
import com.divinetechs.ebooksapp.Model.BookModel.BookModel;
import com.divinetechs.ebooksapp.Model.CategoryModel.CategoryModel;
import com.divinetechs.ebooksapp.Model.CategoryModel.Result;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.divinetechs.ebooksapp.Webservice.AppAPI;
import com.divinetechs.ebooksapp.Webservice.BaseURL;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends Fragment {

    PrefManager prefManager;
    ProgressDialog progressDialog;

    TextView txt_viewall_new_arrival, txt_viewall_category, txt_viewall_item, txt_viewall_author, txt_viewall_continue;

    List<Result> CategoryList_tmp;
    List<Result> CategoryList;
    RecyclerView ry_category;
    CategoryAdapter categoryAdapter;

    List<com.divinetechs.ebooksapp.Model.BookModel.Result> NewArrivalList_tmp;
    List<com.divinetechs.ebooksapp.Model.BookModel.Result> NewArrivalList;
    RecyclerView rv_newarrival;
    NewArrivalAdapter newArrivalAdapter;

    List<com.divinetechs.ebooksapp.Model.BookModel.Result> FeatureList_tmp;
    List<com.divinetechs.ebooksapp.Model.BookModel.Result> FeatureList;
    RecyclerView rv_feature_item;
    FeatureAdapter featureAdapter;

    List<com.divinetechs.ebooksapp.Model.AuthorModel.Result> AuthorList_tmp;
    List<com.divinetechs.ebooksapp.Model.AuthorModel.Result> AuthorList;
    RecyclerView rv_author;
    AuthorAdapter authorAdapter;

    List<com.divinetechs.ebooksapp.Model.BookModel.Result> ContinueList_tmp;
    List<com.divinetechs.ebooksapp.Model.BookModel.Result> ContinueList;
    RecyclerView rv_continue;
    ContinueReadAdapter continueReadAdapter;
    LinearLayout ly_continue;

    private EditText et_search;
    private ImageButton bt_clear;
    ScrollView scrollbar;

    LinearLayout ly_cat, ly_top, ly_new_arrival, ly_author, ly_dataNotFound;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.searchfragment, container, false);

        prefManager = new PrefManager(getActivity());

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        et_search = (EditText) root.findViewById(R.id.et_search);
        bt_clear = (ImageButton) root.findViewById(R.id.bt_clear);

        ly_cat = (LinearLayout) root.findViewById(R.id.ly_cat);
        ly_top = (LinearLayout) root.findViewById(R.id.ly_top);
        ly_new_arrival = (LinearLayout) root.findViewById(R.id.ly_new_arrival);
        ly_author = (LinearLayout) root.findViewById(R.id.ly_author);
        ly_dataNotFound = root.findViewById(R.id.ly_dataNotFound);

        scrollbar = (ScrollView) root.findViewById(R.id.scrollbar);
        ry_category = (RecyclerView) root.findViewById(R.id.ry_category);
        rv_newarrival = (RecyclerView) root.findViewById(R.id.rv_newarrival);
        rv_feature_item = (RecyclerView) root.findViewById(R.id.rv_feature_item);
        rv_author = (RecyclerView) root.findViewById(R.id.rv_author);
        rv_continue = (RecyclerView) root.findViewById(R.id.rv_continue);

        ly_continue = (LinearLayout) root.findViewById(R.id.ly_continue);

        txt_viewall_new_arrival = (TextView) root.findViewById(R.id.txt_viewall_new_arrival);
        txt_viewall_category = (TextView) root.findViewById(R.id.txt_viewall_category);
        txt_viewall_item = (TextView) root.findViewById(R.id.txt_viewall_item);
        txt_viewall_author = (TextView) root.findViewById(R.id.txt_viewall_author);
        txt_viewall_continue = (TextView) root.findViewById(R.id.txt_viewall_continue);

        txt_viewall_new_arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewArrivalAll.class));
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

        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
                scrollbar.setVisibility(View.GONE);
            }
        });

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideKeyboard();
                    searchAction();
                    return true;
                }
                return false;
            }
        });

        Get_Category();
        FeatureItem();
        NewArrival();
        AuthorList();
        ContinueRead();

        return root;
    }


    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void searchAction() {
        scrollbar.setVisibility(View.GONE);

        final String query = et_search.getText().toString().trim();
        if (!query.equals("")) {

//            Category
            CategoryList_tmp = new ArrayList<>();
            for (int i = 0; i < CategoryList.size(); i++) {
                if (CategoryList.get(i).getCatName().toLowerCase().contains(query.toLowerCase())) {
                    CategoryList_tmp.add(CategoryList.get(i));
                    Log.e("CategoryList_tmp", "" + CategoryList_tmp.size());
                }
            }
            if (CategoryList_tmp.size() > 0) {
                categoryAdapter = new CategoryAdapter(getActivity(), CategoryList_tmp, "Home");
                ry_category.setHasFixedSize(true);
                RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false);
                ry_category.setLayoutManager(mLayoutManager3);
                ry_category.setItemAnimator(new DefaultItemAnimator());
                ry_category.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();
                ly_cat.setVisibility(View.VISIBLE);
                ry_category.setVisibility(View.VISIBLE);
            } else {
                ly_cat.setVisibility(View.GONE);
                ry_category.setVisibility(View.GONE);
                ly_dataNotFound.setVisibility(View.VISIBLE);
            }

//            Feature Books
            FeatureList_tmp = new ArrayList<>();
            Log.e("FeatureList", "" + FeatureList.size());
            for (int j = 0; j < FeatureList.size(); j++) {
                if (FeatureList.get(j).getBTitle().toLowerCase().contains(query.toLowerCase())) {
                    FeatureList_tmp.add(FeatureList.get(j));
                    Log.e("FeatureList_tmp", "" + FeatureList_tmp.size());
                } else {
                    Log.e("else", "" + FeatureList.get(j).getBTitle());
                }

            }

            if (FeatureList_tmp.size() > 0) {
                featureAdapter = new FeatureAdapter(getActivity(), FeatureList_tmp, "Home");
                rv_feature_item.setHasFixedSize(true);
                RecyclerView.LayoutManager mLayoutManager_feature = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false);
                rv_feature_item.setLayoutManager(mLayoutManager_feature);
                rv_feature_item.setItemAnimator(new DefaultItemAnimator());
                rv_feature_item.setAdapter(featureAdapter);
                featureAdapter.notifyDataSetChanged();
                ly_top.setVisibility(View.VISIBLE);
                rv_feature_item.setVisibility(View.VISIBLE);
            } else {
                ly_top.setVisibility(View.GONE);
                rv_feature_item.setVisibility(View.GONE);
                ly_dataNotFound.setVisibility(View.VISIBLE);
            }

//            New Arrival
            NewArrivalList_tmp = new ArrayList<>();
            for (int k = 0; k < NewArrivalList.size(); k++) {
                if (NewArrivalList.get(k).getBDescription().toLowerCase().contains(query.toLowerCase())) {
                    NewArrivalList_tmp.add(NewArrivalList.get(k));
                    Log.e("NewArrivalList_tmp", "" + NewArrivalList_tmp.size());
                }
            }

            if (NewArrivalList_tmp.size() > 0) {
                newArrivalAdapter = new NewArrivalAdapter(getActivity(), NewArrivalList_tmp, "Home");
                rv_newarrival.setHasFixedSize(true);
                RecyclerView.LayoutManager mLayoutManager_arri = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false);
                rv_newarrival.setLayoutManager(mLayoutManager_arri);
                rv_newarrival.setItemAnimator(new DefaultItemAnimator());
                rv_newarrival.setAdapter(newArrivalAdapter);
                newArrivalAdapter.notifyDataSetChanged();
                rv_newarrival.setVisibility(View.VISIBLE);
                ly_new_arrival.setVisibility(View.VISIBLE);
            } else {
                rv_newarrival.setVisibility(View.GONE);
                ly_new_arrival.setVisibility(View.GONE);
                ly_dataNotFound.setVisibility(View.VISIBLE);
            }


//            Author
            AuthorList_tmp = new ArrayList<>();
            for (int i = 0; i < AuthorList.size(); i++) {
                if (AuthorList.get(i).getATitle().toLowerCase().contains(query.toLowerCase())) {
                    AuthorList_tmp.add(AuthorList.get(i));
                    Log.e("AuthorList_tmp", "" + AuthorList_tmp.size());
                }
            }

            if (AuthorList_tmp.size() > 0) {
                authorAdapter = new AuthorAdapter(getActivity(), AuthorList_tmp);
                rv_author.setHasFixedSize(true);
                RecyclerView.LayoutManager mLayoutManager_author = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false);
                rv_author.setLayoutManager(mLayoutManager_author);
                rv_author.setItemAnimator(new DefaultItemAnimator());
                rv_author.setAdapter(authorAdapter);
                authorAdapter.notifyDataSetChanged();
                ly_author.setVisibility(View.VISIBLE);
                rv_author.setVisibility(View.VISIBLE);
            } else {
                ly_author.setVisibility(View.GONE);
                rv_author.setVisibility(View.GONE);
                ly_dataNotFound.setVisibility(View.VISIBLE);
            }

            scrollbar.setVisibility(View.VISIBLE);

        } else {
            Toast.makeText(getActivity(), "Please fill search input", Toast.LENGTH_SHORT).show();
            ly_dataNotFound.setVisibility(View.VISIBLE);
        }
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
                    categoryAdapter = new CategoryAdapter(getActivity(), CategoryList, "Home");
                    ry_category.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.HORIZONTAL, false);
                    ry_category.setLayoutManager(mLayoutManager3);
                    ry_category.setItemAnimator(new DefaultItemAnimator());
                    ry_category.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();

                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
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

                    newArrivalAdapter = new NewArrivalAdapter(getActivity(), NewArrivalList, "Home");
                    rv_newarrival.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.HORIZONTAL, false);
                    rv_newarrival.setLayoutManager(mLayoutManager3);
                    rv_newarrival.setItemAnimator(new DefaultItemAnimator());
                    rv_newarrival.setAdapter(newArrivalAdapter);
                    newArrivalAdapter.notifyDataSetChanged();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
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

                    featureAdapter = new FeatureAdapter(getActivity(), FeatureList, "Home");
                    rv_feature_item.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.HORIZONTAL, false);
                    rv_feature_item.setLayoutManager(mLayoutManager3);
                    rv_feature_item.setItemAnimator(new DefaultItemAnimator());
                    rv_feature_item.setAdapter(featureAdapter);
                    featureAdapter.notifyDataSetChanged();
                }
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

                    authorAdapter = new AuthorAdapter(getActivity(), AuthorList);
                    rv_author.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.HORIZONTAL, false);
                    rv_author.setLayoutManager(mLayoutManager3);
                    rv_author.setItemAnimator(new DefaultItemAnimator());
                    rv_author.setAdapter(authorAdapter);
                    authorAdapter.notifyDataSetChanged();
                }
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
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}

