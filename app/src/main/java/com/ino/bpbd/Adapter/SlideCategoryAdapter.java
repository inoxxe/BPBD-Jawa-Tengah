package com.ino.bpbd.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ino.bpbd.CategoryPost;
import com.ino.bpbd.Model.Category;
import com.ino.bpbd.R;

import java.util.List;

public class SlideCategoryAdapter extends RecyclerView.Adapter<SlideCategoryAdapter.myViewHolder> {

    private Context mContext;
    private List<Category> mData;
    private View view;

    public SlideCategoryAdapter(Context mContext, List<Category> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mContext).inflate(R.layout.slide_category,viewGroup,false);
        final myViewHolder viewHolder = new myViewHolder(view);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,CategoryPost.class);
                intent.putExtra("title",mData.get(viewHolder.getAdapterPosition()).getTitle());
                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        myViewHolder.button.setText(mData.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        private Button button;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button_slide_category);
        }
    }
}
