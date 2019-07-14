package com.ino.bpbd.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ino.bpbd.Adapter.NewsAdapter;
import com.ino.bpbd.Adapter.SlideAdapter;
import com.ino.bpbd.Adapter.SlideCategoryAdapter;
import com.ino.bpbd.BeritaTerbaru;
import com.ino.bpbd.Model.Category;
import com.ino.bpbd.Model.News;
import com.ino.bpbd.Model.Slide;
import com.ino.bpbd.R;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private final String JSON_URI = "http://chatbotku.dinus.ac.id/bpbd/berita.php";
    private List<News> lstNews;
    private RecyclerView recyclerView;
    private List<Slide> lstSlide;
    private MultiSnapRecyclerView multiSnapRecyclerView,slideCategory;
    private List<Category> lstCategory;
    private TextView berita_terbaru;

    View view;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news, container, false);

        lstNews = new ArrayList<>();
        lstSlide = new ArrayList<>();
        lstCategory = new ArrayList<>();

       recyclerView = (RecyclerView) view.findViewById(R.id.main_rv);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

       multiSnapRecyclerView = (MultiSnapRecyclerView) view.findViewById(R.id.slide_rv);
       multiSnapRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

       slideCategory = (MultiSnapRecyclerView) view.findViewById(R.id.slide_category);
       slideCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        int[] arr={R.drawable.tanah_longsor_icon,
                R.drawable.kekeringan_icon,
                R.drawable.gempa_bumi_icon,
                R.drawable.tsunami_icon,
                R.drawable.kebakaran_icon,
                R.drawable.puting_beliung_icon
        };

       //Slide Category
        lstCategory.add(new Category(
                "Longsor",arr[0]

        ));

        lstCategory.add(new Category(
                "Kekeringan",arr[1]

        ));
        lstCategory.add(new Category(
                "Gempa",arr[2]

        ));

        lstCategory.add(new Category(
                "Banjir",arr[3]

        ));

        lstCategory.add(new Category(
                "Kebakaran",arr[4]

        ));
        lstCategory.add(new Category(
                "Puting Beliung",arr[5]

        ));

        SlideCategoryAdapter slideCategoryAdapter = new SlideCategoryAdapter(getActivity(),lstCategory);
        slideCategory.setAdapter(slideCategoryAdapter);

       //Slide=========================================================================================
        loadSlideNews();

        //News Post================================================================================
        loadNews();

        //Klik Berita Terbaru
        berita_terbaru = view.findViewById(R.id.berita_terbaru);
        berita_terbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),BeritaTerbaru.class);
                getContext().startActivity(intent);
            }
        });

        return view;

    }

    public void loadSlideNews(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array = new JSONArray(response);
                    for(int i = 0; i < 4; i++){
                        JSONObject news = array.getJSONObject(i);
                        lstSlide.add(new Slide(
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

                    SlideAdapter adapter = new SlideAdapter(getActivity(),lstSlide);
                    multiSnapRecyclerView.setAdapter(adapter);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getActivity().getApplicationContext()).add(stringRequest);
    }

    public void loadNews(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
//                    JSONArray array = array.getJSONArray("articles");
                    for(int i = 0; i < 3; i++){
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

                    NewsAdapter adapter = new NewsAdapter(getActivity(),lstNews);
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
        Volley.newRequestQueue(getActivity().getApplicationContext()).add(stringRequest);
    }

}
