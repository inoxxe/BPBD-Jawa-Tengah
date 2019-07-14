package com.ino.bpbd.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ino.bpbd.Model.Menu;
import com.ino.bpbd.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private Context context;

    List<Menu> menuList;

    public MenuAdapter(List<Menu> menuList, Context context){
        this.menuList = menuList;
        this.context = context;
    }


    class MenuViewHolder extends RecyclerView.ViewHolder {
        //Views
        public ImageView imageView;
        public TextView textViewMenu;


        //Initializing Views
        public MenuViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.menuimage);
            textViewMenu = (TextView) itemView.findViewById(R.id.textmenu);

        }
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menu,parent,false);
        MenuViewHolder viewHolder = new MenuViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int postition) {
        Menu menu = menuList.get(postition);
        Glide.with(context).load(menuList.get(postition).getGambar()).into(holder.imageView);
        holder.textViewMenu.setText(menuList.get(postition).getJudul());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
