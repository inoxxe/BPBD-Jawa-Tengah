package com.ino.bpbd.Detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ino.bpbd.DetailActivity;
import com.ino.bpbd.Model.News;
import com.ino.bpbd.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.myViewHolder> {

    private Context mContext;
    private List<News> mData;
    private RequestOptions option;
    private View view;
    private Typeface roboto,robotoBold;
    private Map<String,String> myMap;

    public RelatedNewsAdapter(Context mContext,List<News> mData){
        myMap = new HashMap<String, String>();
        myMap.put("longsor","http://chatbotku.dinus.ac.id/bpbd/longsor.jpg");
        myMap.put("kekeringan","http://chatbotku.dinus.ac.id/bpbd/kekeringan.jpg");
        myMap.put("gempa","http://chatbotku.dinus.ac.id/bpbd/gempa.jpg");
        myMap.put("banjir","http://chatbotku.dinus.ac.id/bpbd/banjir.jpg");
        myMap.put("kebakaran","http://chatbotku.dinus.ac.id/bpbd/kebakaran.jpg");
        myMap.put("angin","http://chatbotku.dinus.ac.id/bpbd/angin.jpg");
        myMap.put("","http://chatbotku.dinus.ac.id/bpbd/longsor.jpg");


        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.noimage).error(R.drawable.noimage);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_news,viewGroup,false);
        final myViewHolder viewHolder = new myViewHolder(view);

        roboto = Typeface.createFromAsset(viewHolder.itemView.getContext().getAssets(), "fonts/robotoregular.ttf");
        robotoBold = Typeface.createFromAsset(viewHolder.itemView.getContext().getAssets(), "fonts/robotobold.ttf");

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DetailActivity.class);
                intent.putExtra("id",mData.get(viewHolder.getAdapterPosition()).getId());
                intent.putExtra("title",mData.get(viewHolder.getAdapterPosition()).getJudul());
                intent.putExtra("content",mData.get(viewHolder.getAdapterPosition()).getIsi());
                intent.putExtra("tag",mData.get(viewHolder.getAdapterPosition()).getTag());
                intent.putExtra("tanggal",mData.get(viewHolder.getAdapterPosition()).getTanggal());
                intent.putExtra("desc",mData.get(viewHolder.getAdapterPosition()).getDescription());
                intent.putExtra("gambar",mData.get(viewHolder.getAdapterPosition()).getGambar());
                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        myViewHolder.dateNews.setText(mData.get(i).getTanggal());
        myViewHolder.dateNews.setTypeface(roboto);

        myViewHolder.titleNews.setText(mData.get(i).getJudul());
        myViewHolder.titleNews.setTypeface(robotoBold);

        myViewHolder.descriptionNews.setText(mData.get(i).getDescription());
        myViewHolder.descriptionNews.setTypeface(roboto);

        Glide.with(mContext).load(myMap.get(mData.get(i).getGambarDefault())).apply(option).into(myViewHolder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView img_thumbnail;
        private TextView titleNews,descriptionNews,dateNews;
        private LinearLayout container;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img_thumbnail = itemView.findViewById(R.id.image_thumbnail);
            titleNews = itemView.findViewById(R.id.titleNews);
            descriptionNews = itemView.findViewById(R.id.descriptionNews);
            dateNews = itemView.findViewById(R.id.dateNews);
            container = itemView.findViewById(R.id.item_container);
        }
    }
}
