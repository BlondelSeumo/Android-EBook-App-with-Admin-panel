package com.divinetechs.ebooksapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.divinetechs.ebooksapp.Activity.BookDetails;
import com.divinetechs.ebooksapp.Model.BookModel.Result;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.squareup.picasso.Picasso.Priority.HIGH;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.MyViewHolder> {

    private List<Result> NewArrivalList;
    Context mcontext;
    PrefManager prefManager;
    String from;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_bookname, txt_view;
        ImageView iv_thumb;
        SimpleRatingBar simpleRatingBar;

        public MyViewHolder(View view) {
            super(view);
            txt_bookname = (TextView) view.findViewById(R.id.txt_bookname);
            iv_thumb = (ImageView) view.findViewById(R.id.iv_thumb);
            txt_view = (TextView) view.findViewById(R.id.txt_view);
            simpleRatingBar = (SimpleRatingBar) view.findViewById(R.id.ratingbar);
        }
    }


    public FeatureAdapter(Context context, List<Result> NewArrivalList, String from) {
        this.NewArrivalList = NewArrivalList;
        this.mcontext = context;
        this.from = from;
        prefManager = new PrefManager(mcontext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (from.equalsIgnoreCase("Home")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.feature_item, parent, false);
            return new FeatureAdapter.MyViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.feature_item2, parent, false);
            return new FeatureAdapter.MyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.txt_view.setText("" + NewArrivalList.get(position).getReadcnt());
        holder.txt_bookname.setText("" + NewArrivalList.get(position).getBTitle());

        Picasso.with(mcontext).load(NewArrivalList.get(position).getBImage()).priority(HIGH).into(holder.iv_thumb);

        holder.simpleRatingBar.setRating(Float.parseFloat(NewArrivalList.get(position).getAvg_rating()));

        holder.iv_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", "call");
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
