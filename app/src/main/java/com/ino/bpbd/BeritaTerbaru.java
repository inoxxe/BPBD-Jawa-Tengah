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
import com.ino.bpbd.Adapter.NewsAdapter;
import com.ino.bpbd.Model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BeritaTerbaru extends AppCompatActivity {

    private List<News> lstNews;
    private RecyclerView recyclerView;
    private final String JSON_URI = "http://chatbotku.dinus.ac.id/bpbd/berita.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.berita_terbaru);

        getSupportActionBar().setTitle("Berita Terbaru");

        lstNews = new ArrayList<>();
        recyclerView = findViewById(R.id.berita_terbaru_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadBeritaTerbaru();
    }

    public void loadBeritaTerbaru(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
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

                    NewsAdapter adapter = new NewsAdapter(BeritaTerbaru.this,lstNews);
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
        Volley.newRequestQueue(BeritaTerbaru.this).add(stringRequest);
    }
}
