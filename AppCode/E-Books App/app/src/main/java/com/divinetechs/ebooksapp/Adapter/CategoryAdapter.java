package com.divinetechs.ebooksapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.divinetechs.ebooksapp.Activity.CategoryBookList;
import com.divinetechs.ebooksapp.Model.CategoryModel.Result;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;

import java.util.List;
import java.util.Random;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private List<Result> CategoryList;
    Context mcontext;
    PrefManager prefManager;
    String from;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_bookname, txt_tag;
        ImageView iv_thumb;
        RelativeLayout ry_category;

        public MyViewHolder(View view) {
            super(view);
            txt_bookname = (TextView) view.findViewById(R.id.txt_bookname);
            iv_thumb = (ImageView) view.findViewById(R.id.iv_thumb);
            txt_tag = (TextView) view.findViewById(R.id.txt_tag);
            ry_category = (RelativeLayout) view.findViewById(R.id.ry_category);
        }
    }


    public CategoryAdapter(Context context, List<Result> CategoryList, String from) {
        this.CategoryList = CategoryList;
        this.mcontext = context;
        this.from = from;
        prefManager = new PrefManager(mcontext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (from.equalsIgnoreCase("Home")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_item, parent, false);
            return new CategoryAdapter.MyViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_item2, parent, false);
            return new CategoryAdapter.MyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.txt_bookname.setText("" + CategoryList.get(position).getCatName());
        holder.txt_tag.setText("" + CategoryList.get(position).getCatName().charAt(0));

        if (position == 0) {
            holder.iv_thumb.setBackgroundColor(mcontext.getResources().getColor(R.color.cat_1));
        } else if (position == 1) {
            holder.iv_thumb.setBackgroundColor(mcontext.getResources().getColor(R.color.cat_2));
        } else if (position == 2) {
            holder.iv_thumb.setBackgroundColor(mcontext.getResources().getColor(R.color.cat_3));
        } else if (position == 3) {
            holder.iv_thumb.setBackgroundColor(mcontext.getResources().getColor(R.color.cat_4));
        } else if (position == 4) {
            holder.iv_thumb.setBackgroundColor(mcontext.getResources().getColor(R.color.cat_5));
        } else {
            holder.iv_thumb.setBackgroundColor(getRandomColor());
        }

        holder.txt_bookname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click", "call");
                Intent intent = new Intent(mcontext, CategoryBookList.class);
                intent.putExtra("cat_id", CategoryList.get(position).getCatId());
                intent.putExtra("cat_name", CategoryList.get(position).getCatName());
                intent.putExtra("cat_image", CategoryList.get(position).getCatImage());
                mcontext.startActivity(intent);
            }
        });

        holder.txt_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click", "call");
                Intent intent = new Intent(mcontext, CategoryBookList.class);
                intent.putExtra("cat_id", CategoryList.get(position).getCatId());
                intent.putExtra("cat_name", CategoryList.get(position).getCatName());
                intent.putExtra("cat_image", CategoryList.get(position).getCatImage());
                mcontext.startActivity(intent);
            }
        });

        holder.iv_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", "call");
                Intent intent = new Intent(mcontext, CategoryBookList.class);
                intent.putExtra("cat_id", CategoryList.get(position).getCatId());
                intent.putExtra("cat_name", CategoryList.get(position).getCatName());
                intent.putExtra("cat_image", CategoryList.get(position).getCatImage());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return CategoryList.size();
    }

    public int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

}
