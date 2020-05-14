package com.divinetechs.ebooksapp.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.divinetechs.ebooksapp.Activity.BookDetails;
import com.divinetechs.ebooksapp.Model.BookModel.Result;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.squareup.picasso.Picasso.Priority.HIGH;

public class CategoryBookAdapter extends RecyclerView.Adapter<CategoryBookAdapter.MyViewHolder> {

    private List<Result> NewArrivalList;
    Context mcontext;
    PrefManager prefManager;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_bookname, txt_view;
        ImageView iv_thumb;

        public MyViewHolder(View view) {
            super(view);
            txt_bookname = (TextView) view.findViewById(R.id.txt_bookname);
            iv_thumb = (ImageView) view.findViewById(R.id.iv_thumb);
            txt_view = (TextView) view.findViewById(R.id.txt_view);
        }
    }


    public CategoryBookAdapter(Context context, List<Result> NewArrivalList) {
        this.NewArrivalList = NewArrivalList;
        this.mcontext = context;
        prefManager = new PrefManager(mcontext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categorybook_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.txt_view.setText("" + NewArrivalList.get(position).getReadcnt());
        holder.txt_bookname.setText("" + NewArrivalList.get(position).getBTitle());

        Picasso.with(mcontext).load(NewArrivalList.get(position).getBImage()).priority(HIGH).into(holder.iv_thumb);

        holder.iv_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", "call");
//                PrefManager prefManager = new PrefManager(mcontext);
//                prefManager.WallpaperList = new ArrayList<>();
//                prefManager.WallpaperList = LatestList;
//
                Intent intent = new Intent(mcontext, BookDetails.class);
                intent.putExtra("ID", NewArrivalList.get(position).getBId());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return NewArrivalList.size();
    }

}
