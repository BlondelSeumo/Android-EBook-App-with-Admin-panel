package com.divinetechs.ebooksapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.divinetechs.ebooksapp.Activity.AuthorBookList;
import com.divinetechs.ebooksapp.Model.AuthorModel.Result;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.squareup.picasso.Picasso.Priority.HIGH;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.MyViewHolder> {

    private List<Result> AuthorList;
    Context mcontext;
    PrefManager prefManager;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_bookname;
        RoundedImageView iv_thumb;

        public MyViewHolder(View view) {
            super(view);
            txt_bookname = (TextView) view.findViewById(R.id.txt_bookname);
            iv_thumb = (RoundedImageView) view.findViewById(R.id.iv_thumb);
        }
    }


    public AuthorAdapter(Context context, List<Result> AuthorList) {
        this.AuthorList = AuthorList;
        this.mcontext = context;
        prefManager = new PrefManager(mcontext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.author_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.txt_bookname.setText("" + AuthorList.get(position).getATitle());

        Picasso.with(mcontext).load(AuthorList.get(position).getAImage()).priority(HIGH).into(holder.iv_thumb);

        holder.iv_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", "call");
//                PrefManager prefManager = new PrefManager(mcontext);
//                prefManager.WallpaperList = new ArrayList<>();
//                prefManager.WallpaperList = LatestList;
//
                Intent intent = new Intent(mcontext, AuthorBookList.class);
                intent.putExtra("a_id", AuthorList.get(position).getAId());
                intent.putExtra("a_name", AuthorList.get(position).getATitle());
                intent.putExtra("a_bio", AuthorList.get(position).getABio());
                intent.putExtra("a_image", AuthorList.get(position).getAImage());
                intent.putExtra("a_address", AuthorList.get(position).getaAddress());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return AuthorList.size();
    }

}
