package com.ino.bpbd.Adapter;

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
import com.ino.bpbd.Model.Slide;
import com.ino.bpbd.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.myViewHolder> {

    private Context mContext;
    private List<Slide> mData;
    private RequestOptions option;
    private View view;
    private Typeface robotoMedium;
    private Map<String,String> myMap;

    public SlideAdapter(Context mContext, List<Slide> mData){
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
        view = LayoutInflater.from(mContext).inflate(R.layout.item_slide,viewGroup,false);
        final myViewHolder viewHolder = new myViewHolder(view);
        robotoMedium = Typeface.createFromAsset(viewHolder.itemView.getContext().getAssets(),"fonts/robotomedium.ttf");

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
                intent.putExtra("default",mData.get(viewHolder.getAdapterPosition()).getGambarDefault());
                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        myViewHolder.text_slide.setText(mData.get(i).getJudul());
        myViewHolder.text_slide.setTypeface(robotoMedium);

        Glide.with(mContext).load(myMap.get(mData.get(i).getGambarDefault())).apply(option).into(myViewHolder.img_slide);
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        private TextView text_slide;
        private RoundedImageView img_slide;
        private LinearLayout container;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            text_slide = itemView.findViewById(R.id.text_slide);
            img_slide = itemView.findViewById(R.id.img_slide);
            container = itemView.findViewById(R.id.slide_container);
        }
    }
}
