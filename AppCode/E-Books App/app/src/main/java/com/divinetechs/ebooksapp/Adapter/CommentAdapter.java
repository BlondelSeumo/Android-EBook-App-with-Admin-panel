package com.divinetechs.ebooksapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.divinetechs.ebooksapp.Model.CommentModel.Result;
import com.divinetechs.ebooksapp.R;

import java.util.List;
import java.util.Random;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private List<Result> CommentList;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_user_name, txt_comment, txt_date, txt_tag;
        ImageView iv_thumb;

        public MyViewHolder(View view) {
            super(view);
            txt_user_name = (TextView) view.findViewById(R.id.txt_user_name);
            txt_comment = (TextView) view.findViewById(R.id.txt_comment);
            txt_date = (TextView) view.findViewById(R.id.txt_date);
            iv_thumb = (ImageView) view.findViewById(R.id.iv_thumb);
            txt_tag = (TextView) view.findViewById(R.id.txt_tag);
        }
    }


    public CommentAdapter(Context context, List<Result> CommentList) {
        this.CommentList = CommentList;
        this.mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.txt_user_name.setText("" + CommentList.get(position).getFullname());
        holder.txt_comment.setText("" + CommentList.get(position).getComment());
        holder.txt_date.setText("" + CommentList.get(position).getCDate());
        holder.txt_tag.setText("" + CommentList.get(position).getFullname().charAt(0));

        holder.iv_thumb.setBackgroundColor(getRandomColor());

        holder.iv_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", "call");
//                Intent intent = new Intent(mcontext, BookDetails.class);
//                intent.putExtra("ID", CommentList.get(position).getBId());
//                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return CommentList.size();
    }


    public int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

}
