package com.ino.bpbd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ino.bpbd.Adapter.CategoryPostAdapter;
import com.ino.bpbd.Model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryPost extends AppCompatActivity {

    private List<News> lstNews;
    private RecyclerView recyclerView;
    private String title;
    private final String URL_JSON = "http://chatbotku.dinus.ac.id/bpbd/berita.php";
    private String KATEGORI_URL;
    private String kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_post);

        title = getIntent().getExtras().getString("title");
        getSupportActionBar().setTitle(title);

        lstNews = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.cp_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        kategori = getIntent().getExtras().getString("title");
        loadNewsKategori(kategori);
    }

    public void loadNewsKategori(String kategori){
        KATEGORI_URL = URL_JSON + "?kategori="+kategori;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, KATEGORI_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array = new JSONArray(response);
                    for(int i = 0; i < array.length(); i++){
                        JSONObject news = array.getJSONObject(i);
                        lstNews.add(new News(
                                news.getInt("id"),
                                news.getString("judul"),
                                news.getString("desc"),
                                news.getString("isi"),
                                news.getString("tag"),
                                news.getString("tanggal"),
                                news.getString("gambar"),
                                news.getString("default")
                        ));
                    }

                    CategoryPostAdapter adapter = new CategoryPostAdapter(CategoryPost.this,lstNews);
                    recyclerView.setAdapter(adapter);

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(CategoryPost.this).add(stringRequest);
    }
}
