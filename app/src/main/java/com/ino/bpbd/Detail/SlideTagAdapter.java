package com.ino.bpbd.Detail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ino.bpbd.CategoryPost;
import com.ino.bpbd.Detail.Model.Tag;
import com.ino.bpbd.R;

import java.util.List;

public class SlideTagAdapter extends RecyclerView.Adapter<SlideTagAdapter.myViewHolder> {

    private Context mContext;
    private List<Tag> mData;
    private View view;

    public SlideTagAdapter(Context mContext, List<Tag> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mContext).inflate(R.layout.slide_category,viewGroup,false);
        final myViewHolder viewHolder = new myViewHolder(view);
        viewHolder.slide_tag.setOnClickListener(new View.OnClickListener() {
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
        myViewHolder.slide_tag.setText(mData.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        private Button slide_tag;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            slide_tag = itemView.findViewById(R.id.button_slide_category);
        }
    }
}
