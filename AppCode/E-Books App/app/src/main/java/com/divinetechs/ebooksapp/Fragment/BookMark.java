package com.divinetechs.ebooksapp.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.divinetechs.ebooksapp.Adapter.BookmarkAdapter;
import com.divinetechs.ebooksapp.Model.BookModel.BookModel;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.divinetechs.ebooksapp.Webservice.AppAPI;
import com.divinetechs.ebooksapp.Webservice.BaseURL;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookMark extends Fragment {

    PrefManager prefManager;
    ProgressDialog progressDialog;

    List<com.divinetechs.ebooksapp.Model.BookModel.Result> BookmarkList;
    RecyclerView ry_bookmark;
    BookmarkAdapter bookmarkAdapter;
    LinearLayout ly_dataNotFound, ly_recycle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.bookmarkfragment, container, false);

        ry_bookmark = (RecyclerView) root.findViewById(R.id.ry_bookmark);
        ly_dataNotFound = root.findViewById(R.id.ly_dataNotFound);
        ly_recycle = root.findViewById(R.id.ly_recycle);

        prefManager = new PrefManager(getActivity());

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        allBookmark();

        return root;
    }

    private void allBookmark() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<BookModel> call = bookNPlayAPI.allBookmark(prefManager.getLoginId());
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                if (response.code() == 200) {
                    BookmarkList = new ArrayList<>();
                    BookmarkList = response.body().getResult();
                    Log.e("BookmarkList", "" + BookmarkList.size());
                    if (BookmarkList.size() > 0) {
                        bookmarkAdapter = new BookmarkAdapter(getActivity(), BookmarkList, "ViewAll");
                        ry_bookmark.setHasFixedSize(true);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3,
                                LinearLayoutManager.VERTICAL, false);
                        ry_bookmark.setLayoutManager(gridLayoutManager);
                        ry_bookmark.setItemAnimator(new DefaultItemAnimator());
                        ry_bookmark.setAdapter(bookmarkAdapter);
                        bookmarkAdapter.notifyDataSetChanged();
                        ly_dataNotFound.setVisibility(View.GONE);
                    } else {
                        ly_recycle.setVisibility(View.GONE);
                        ly_dataNotFound.setVisibility(View.VISIBLE);
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                progressDialog.dismiss();
                ry_bookmark.setVisibility(View.GONE);
                ly_dataNotFound.setVisibility(View.VISIBLE);
            }
        });
    }
}

