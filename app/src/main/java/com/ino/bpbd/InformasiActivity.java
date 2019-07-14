package com.ino.bpbd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ino.bpbd.Adapter.InformasiAdapter;
import com.ino.bpbd.Model.Informasi;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class InformasiActivity extends AppCompatActivity {
    private List<Informasi> infoList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RequestQueue requestQueue;
    String kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);
        Intent intent = getIntent();
        kategori = intent.getStringExtra("kategori");
        getSupportActionBar().setTitle("PPID - "+kategori);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerinfo);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        infoList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        getData();
        adapter = new InformasiAdapter(infoList,this);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final Informasi info = infoList.get(position);
                String judul = info.getJudul().toString();
                Intent informasi = new Intent(getApplicationContext(), DetailPPID.class);
                informasi.putExtra(Config.TAG_JUDUL,judul);
                startActivity(informasi);

            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));


    }

    private JsonArrayRequest getDataFromServer() {

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);


        progressBar.setVisibility(View.VISIBLE);
        setProgressBarIndeterminateVisibility(true);


        StringBuilder sb = new StringBuilder();
        sb.append("http://chatbotku.dinus.ac.id/bpbd/informasi.php?kategori="+kategori);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(0, sb.toString(), null, new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray jSONArray) {
                InformasiActivity.this.parseData(jSONArray);
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(InformasiActivity.this, "No More Items Available", Toast.LENGTH_SHORT).show();
            }
        });
        return jsonArrayRequest;
    }

    private void getData() {
        this.requestQueue.add(getDataFromServer());
    }

    /* access modifiers changed from: private */
    public void parseData(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            Informasi informasi = new Informasi();
            try {
                informasi.setJudul(jSONArray.getJSONObject(i).getString("judul"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.infoList.add(informasi);
        }
        this.adapter.notifyDataSetChanged();
    }
}
