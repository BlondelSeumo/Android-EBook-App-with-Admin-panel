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

public class FreebookAdapter extends RecyclerView.Adapter<FreebookAdapter.MyViewHolder> {
    List<Result> freebookList;
    Context mcontext;
    PrefManager prefManager;
    String from;

    public FreebookAdapter(Context context, List<Result> freebookList, String from) {
        this.freebookList = freebookList;
        this.mcontext = context;
        this.from = from;
        prefManager = new PrefManager(mcontext);
    }

    @NonNull
    @Override
    public FreebookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (from.equalsIgnoreCase("Home")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.newarrival_item, parent, false);
            return new FreebookAdapter.MyViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.newarrival_item2, parent, false);
            return new FreebookAdapter.MyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull FreebookAdapter.MyViewHolder holder, int position) {
        holder.txt_bookname.setText("" + freebookList.get(position).getA_title());

        Picasso.with(mcontext).load(freebookList.get(position).getB_image()).priority(HIGH).into(holder.iv_thumb);

        holder.iv_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", "call");
                Intent intent = new Intent(mcontext, BookDetails.class);
                intent.putExtra("ID", freebookList.get(position).getB_id());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return freebookList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_bookname;
        ImageView iv_thumb;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_bookname = (TextView) itemView.findViewById(R.id.txt_bookname);
            iv_thumb = (ImageView) itemView.findViewById(R.id.iv_thumb);
        }
    }
}
