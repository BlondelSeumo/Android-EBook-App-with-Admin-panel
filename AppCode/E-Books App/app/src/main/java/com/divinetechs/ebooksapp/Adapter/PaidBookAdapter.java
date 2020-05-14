package com.divinetechs.ebooksapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.divinetechs.ebooksapp.Activity.BookDetails;
import com.divinetechs.ebooksapp.Model.FreeBookModel.Result;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.squareup.picasso.Picasso.Priority.HIGH;

public class PaidBookAdapter extends RecyclerView.Adapter<PaidBookAdapter.MyViewHolder> {
    List<Result> paidbookList;
    Context mcontext;
    PrefManager prefManager;
    String from;

    public PaidBookAdapter(Context context, List<Result> paidbookList, String from) {
        this.paidbookList = paidbookList;
        this.mcontext = context;
        this.from = from;
        prefManager = new PrefManager(mcontext);
    }

    @NonNull
    @Override
    public PaidBookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (from.equalsIgnoreCase("Home")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.newarrival_item, parent, false);
            return new PaidBookAdapter.MyViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.newarrival_item2, parent, false);
            return new PaidBookAdapter.MyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PaidBookAdapter.MyViewHolder holder, int position) {
        holder.txt_bookname.setText("" + paidbookList.get(position).getA_title());
        holder.txt_book_price.setText("$" + paidbookList.get(position).getB_price());
        Picasso.with(mcontext).load(paidbookList.get(position).getB_image()).priority(HIGH).into(holder.iv_thumb);

        holder.iv_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", "call");
                Intent intent = new Intent(mcontext, BookDetails.class);
                intent.putExtra("ID", paidbookList.get(position).getB_id());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return paidbookList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_bookname, txt_book_price;
        ImageView iv_thumb;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_bookname = (TextView) itemView.findViewById(R.id.txt_bookname);
            txt_book_price = (TextView) itemView.findViewById(R.id.txt_book_price);
            iv_thumb = (ImageView) itemView.findViewById(R.id.iv_thumb);
        }
    }
}
