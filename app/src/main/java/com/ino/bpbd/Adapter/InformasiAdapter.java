package com.ino.bpbd.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ino.bpbd.Model.Informasi;
import com.ino.bpbd.R;

import java.util.List;

public class InformasiAdapter extends RecyclerView.Adapter<InformasiAdapter.InfoViewHolder> {

    private Context context;

    List<Informasi> infoList;

    public InformasiAdapter(List<Informasi> infoList, Context context){
        this.infoList = infoList;
        this.context = context;
    }


    class InfoViewHolder extends RecyclerView.ViewHolder {
        //Views
        public TextView textViewJudul;


        //Initializing Views
        public InfoViewHolder(View itemView) {
            super(itemView);
            textViewJudul = (TextView) itemView.findViewById(R.id.textjudul);

        }
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_informasi,parent,false);
        InfoViewHolder viewHolder = new InfoViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int postition) {
        Informasi info = infoList.get(postition);
        holder.textViewJudul.setText(infoList.get(postition).getJudul());
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }
}
