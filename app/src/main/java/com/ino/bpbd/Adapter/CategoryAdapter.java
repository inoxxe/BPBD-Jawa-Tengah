package com.ino.bpbd.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ino.bpbd.CategoryPost;
import com.ino.bpbd.Model.Category;
import com.ino.bpbd.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.myViewHolder> {

    private List<Category> mData;
    private Context mContext;
    private RequestOptions option;

    public CategoryAdapter(Context mContext, List<Category> mData){
        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().placeholder(R.drawable.bencana_logo).error(R.drawable.img_view);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_category,viewGroup,false);
        final myViewHolder viewHolder = new myViewHolder(view);

        //klik kategori
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
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
        myViewHolder.title.setText(mData.get(i).getTitle());
        Glide.with(mContext).load(mData.get(i).getGambar()).apply(option).into(myViewHolder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView img_thumbnail;
        private CardView container;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleCategory);
            img_thumbnail = itemView.findViewById(R.id.imageCategory);
            container = itemView.findViewById(R.id.categoryPost);
        }
    }
}
