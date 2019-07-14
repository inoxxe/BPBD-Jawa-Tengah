package com.ino.bpbd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ino.bpbd.Adapter.NewsAdapter;
import com.ino.bpbd.Data.FavoriteContract.FavoriteEntry;
import com.ino.bpbd.Model.News;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity implements OnQueryTextListener {
    private static final String TAG = "SearchActivity";
    /* access modifiers changed from: private */

    /* renamed from: a */
    public String a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public String b;
    Button btncari;
    Button btnrinci;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public String c;
    ExpandableRelativeLayout expandable1;
    /* access modifiers changed from: private */
    public List<News> lstNews;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    SearchView searchView;
    Spinner spinnerdaerah;
    Spinner spinnerkategori;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_searchresult);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.searchView = (SearchView) findViewById(R.id.search);
        this.lstNews = new ArrayList();
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclersearch);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.btncari = (Button) findViewById(R.id.btncari_rinci);
        this.btnrinci = (Button) findViewById(R.id.btnexpand);
        this.spinnerkategori = (Spinner) findViewById(R.id.spinner_kategori);
        this.spinnerdaerah = (Spinner) findViewById(R.id.spinner_daerah);
        ArrayAdapter createFromResource = ArrayAdapter.createFromResource(this, R.array.Kategori, android.R.layout.simple_spinner_item);
        createFromResource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter createFromResource2 = ArrayAdapter.createFromResource(this, R.array.Daerah, android.R.layout.simple_spinner_item);
        createFromResource2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerdaerah.setAdapter(createFromResource2);
        this.spinnerkategori.setAdapter(createFromResource);
        this.btnrinci.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SearchActivity searchActivity = SearchActivity.this;
                searchActivity.expandable1 = (ExpandableRelativeLayout) searchActivity.findViewById(R.id.expandableLayout);
                SearchActivity.this.expandable1.toggle();
            }
        });
        this.btncari.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SearchActivity searchActivity = SearchActivity.this;
                searchActivity.c = searchActivity.spinnerdaerah.getSelectedItem().toString();
                SearchActivity searchActivity2 = SearchActivity.this;
                searchActivity2.b = searchActivity2.spinnerkategori.getSelectedItem().toString();
                SearchActivity.this.lstNews.clear();
                SearchActivity searchActivity3 = SearchActivity.this;
                searchActivity3.searchRinci(searchActivity3.a, SearchActivity.this.b, SearchActivity.this.c);
                NewsAdapter newsAdapter = new NewsAdapter(SearchActivity.this.getApplicationContext(), SearchActivity.this.lstNews);
                SearchActivity.this.recyclerView.setAdapter(newsAdapter);
                newsAdapter.notifyDataSetChanged();
                SearchActivity.this.expandable1.toggle();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_main, menu);
        SearchView searchView2 = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView2.setQueryHint("Cari Berita...");
        searchView2.setIconified(false);
        searchView2.setOnQueryTextListener(this);
        return true;
    }

    public boolean onQueryTextSubmit(String str) {
        this.lstNews.clear();
        searchBerita(str);
        NewsAdapter newsAdapter = new NewsAdapter(getApplicationContext(), this.lstNews);
        this.recyclerView.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
        this.a = str;
        return true;
    }

    public boolean onQueryTextChange(String str) {
        this.a = str;
        return true;
    }

    public void searchBerita(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://chatbotku.dinus.ac.id/bpbd/search.php?na=");
        sb.append(str);
        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(0, sb.toString(), new Listener<String>() {
            public void onResponse(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        List access$200 = SearchActivity.this.lstNews;
                        News news = new News(jSONObject.getInt(Config.ID), jSONObject.getString("judul"), jSONObject.getString("desc"), jSONObject.getString(Config.TAG_ISI), jSONObject.getString(FavoriteEntry.COLUMN_TAG), jSONObject.getString(FavoriteEntry.COLUMN_TANGGAL), jSONObject.getString(FavoriteEntry.COLUMN_GAMBAR), jSONObject.getString("default"));
                        access$200.add(news);
                    }
                    NewsAdapter newsAdapter = new NewsAdapter(SearchActivity.this.getApplicationContext(), SearchActivity.this.lstNews);
                    SearchActivity.this.recyclerView.setAdapter(newsAdapter);
                    newsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
            }
        }));
    }

    public void searchRinci(String str, String str2, String str3) {
        String str4;
        String str5 = "All";
        String str6 = "http://chatbotku.dinus.ac.id/bpbd/search.php?na=";
        if (!str5.equals(str2) || !str5.equals(str3)) {
            String str7 = "&nb=";
            if (str5.equals(str2) || !str5.equals(str3)) {
                String str8 = "&nc=";
                if (str5.equals(str3) || !str5.equals(str2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str6);
                    sb.append(str);
                    sb.append(str7);
                    sb.append(str2);
                    sb.append(str8);
                    sb.append(str3);
                    str4 = sb.toString();
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str6);
                    sb2.append(str);
                    sb2.append(str8);
                    sb2.append(str3);
                    str4 = sb2.toString();
                }
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str6);
                sb3.append(str);
                sb3.append(str7);
                sb3.append(str2);
                str4 = sb3.toString();
            }
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str6);
            sb4.append(str);
            str4 = sb4.toString();
        }
        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(0, str4, new Listener<String>() {
            public void onResponse(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        List access$200 = SearchActivity.this.lstNews;
                        News news = new News(jSONObject.getInt(Config.ID), jSONObject.getString("judul"), jSONObject.getString("desc"), jSONObject.getString(Config.TAG_ISI), jSONObject.getString(FavoriteEntry.COLUMN_TAG), jSONObject.getString(FavoriteEntry.COLUMN_TANGGAL), jSONObject.getString(FavoriteEntry.COLUMN_GAMBAR), jSONObject.getString("default"));
                        access$200.add(news);
                    }
                    NewsAdapter newsAdapter = new NewsAdapter(SearchActivity.this.getApplicationContext(), SearchActivity.this.lstNews);
                    SearchActivity.this.recyclerView.setAdapter(newsAdapter);
                    newsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
            }
        }));
    }
}