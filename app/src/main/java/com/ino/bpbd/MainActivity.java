package com.ino.bpbd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;

import com.ino.bpbd.Adapter.MenuAdapter;
import com.ino.bpbd.Model.Menu;
import com.ino.bpbd.RecyclerTouchListener.ClickListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Adapter adapter;
    private LayoutManager layoutManager;
    /* access modifiers changed from: private */
    public ArrayList<Menu> menuList;
    private RecyclerView recyclerView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("PPID Informasi Publik");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(this.layoutManager);
        recyclerView.setHasFixedSize(true);
        addmenu();
        this.adapter = new MenuAdapter(this.menuList, this);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), this.recyclerView, new ClickListener() {
            public void onLongClick(View view, int i) {
            }

            public void onClick(View view, int i) {
                String str = ((Menu) MainActivity.this.menuList.get(i)).getJudul().toString();
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), InformasiActivity.class);
                intent.putExtra(Config.KATEGORI, str);
                startActivity(intent);
            }
        }));
    }

    private void addmenu() {
        int[] iArr = {R.drawable.ic_info};
        this.menuList = new ArrayList<>();
        this.menuList.add(new Menu(iArr[0], "Informasi Serta Merta"));
        this.menuList.add(new Menu(iArr[0], "Informasi Berkala"));
        this.menuList.add(new Menu(iArr[0], "Informasi Setiap Saat"));
        this.menuList.add(new Menu(iArr[0], "Informasi Dikecualikan"));
    }
}