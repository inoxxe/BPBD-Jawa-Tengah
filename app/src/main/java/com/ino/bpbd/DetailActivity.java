package com.ino.bpbd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ino.bpbd.Data.FavoriteContract;
import com.ino.bpbd.Data.FavoriteDbHelper;
import com.ino.bpbd.Detail.Model.Tag;
import com.ino.bpbd.Detail.RelatedNewsAdapter;
import com.ino.bpbd.Detail.SlideTagAdapter;
import com.ino.bpbd.Model.News;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    private final String URL_JSON = "http://chatbotku.dinus.ac.id/bpbd/berita.php";
    private int id;
    private String DETAIL_URL;
    private RequestOptions option;
    private ImageView img_thumbnail;
    private TextView detail_title,detail_date,detail_content,moreNews;
    private Typeface robotoMedium;
    private RecyclerView slideTagRecyclerView;
    private RecyclerView relatedRecyclerView;
    public List<Tag> lstTag;
    public List<News> lstNews;
    public MaterialFavoriteButton favoriteButton;
    private FavoriteDbHelper favoriteDbHelper;
    private News favorite;
    private SQLiteDatabase nDB;
    private Map<String,String> myMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        myMap = new HashMap<String, String>();
        myMap.put("longsor","http://chatbotku.dinus.ac.id/bpbd/longsor.jpg");
        myMap.put("kekeringan","http://chatbotku.dinus.ac.id/bpbd/kekeringan.jpg");
        myMap.put("gempa","http://chatbotku.dinus.ac.id/bpbd/gempa.jpg");
        myMap.put("banjir","http://chatbotku.dinus.ac.id/bpbd/banjir.jpg");
        myMap.put("kebakaran","http://chatbotku.dinus.ac.id/bpbd/kebakaran.jpg");
        myMap.put("angin","http://chatbotku.dinus.ac.id/bpbd/angin.jpg");
        myMap.put("","http://chatbotku.dinus.ac.id/bpbd/longsor.jpg");

        lstTag = new ArrayList<>();
        lstNews = new ArrayList<>();
        option = new RequestOptions().centerCrop().placeholder(R.drawable.detailnoimage).error(R.drawable.detailnoimage);

        favoriteButton = (MaterialFavoriteButton) findViewById(R.id.favorite_button);

        robotoMedium = Typeface.createFromAsset(getAssets(), "fonts/robotomedium.ttf");
        img_thumbnail = (ImageView) findViewById(R.id.detail_img_thumbnail);
        detail_title = (TextView) findViewById(R.id.detail_title);
        detail_title.setTypeface(robotoMedium);
        detail_date = (TextView) findViewById(R.id.detail_date);
        detail_content = (TextView) findViewById(R.id.detail_content);
        moreNews = (TextView) findViewById(R.id.lihat_semuanya);

        detail_content.setTypeface(robotoMedium);
        id = getIntent().getIntExtra("id",0);
        DETAIL_URL = URL_JSON + "?id=" + String.valueOf(id);

        slideTagRecyclerView = findViewById(R.id.detail_tag);
        slideTagRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        relatedRecyclerView = findViewById(R.id.detail_related_news);
        relatedRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        favoriteDbHelper = new FavoriteDbHelper(this);
        nDB = favoriteDbHelper.getWritableDatabase();

        moreNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,BeritaTerbaru.class);
                DetailActivity.this.startActivity(intent);
            }
        });

        if(ExistsId(String.valueOf(id))){
            favoriteButton.setFavorite(true);
            favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if(favorite == true){
                        saveFavorite();
                        Snackbar.make(buttonView, "Ditambahkan ke Bookmark", Snackbar.LENGTH_SHORT).show();
                    }else{
                        favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
                        favoriteDbHelper.deleteFavorite(id);
                        Snackbar.make(buttonView, "Dihapus Dalam Bookmark", Snackbar.LENGTH_SHORT).show();

                    }
                }
            });

        }else{
            favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if(favorite == true){
                        saveFavorite();
                        Snackbar.make(buttonView, "Ditambahkan ke Bookmark", Snackbar.LENGTH_SHORT).show();
                    }else{
                        int news_id = getIntent().getExtras().getInt("id");
                        favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
                        favoriteDbHelper.deleteFavorite(news_id);
                        Snackbar.make(buttonView, "Dihapus Dalam Bookmark", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });

        }

        loadDetail();
        loadKategori();
        loadRelatedNews();

    }

    public void loadDetail(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DETAIL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array = new JSONArray(response);
                    for(int i = 0; i < array.length(); i++){
                        JSONObject news = array.getJSONObject(i);
                        detail_title.setText(news.getString("judul"));
                        detail_content.setText(news.getString("isi"));
                        detail_date.setText(news.getString("tanggal"));
                        Glide.with(DetailActivity.this).load(myMap.get(news.getString("default"))).apply(option).into(img_thumbnail);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(DetailActivity.this).add(stringRequest);
//        Toast.makeText(this,String.valueOf(DETAIL_URL),Toast.LENGTH_LONG).show();
    }

    public void loadRelatedNews(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_JSON, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array = new JSONArray(response);
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
                    RelatedNewsAdapter adapter = new RelatedNewsAdapter(DetailActivity.this,lstNews);
                    relatedRecyclerView.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(DetailActivity.this).add(stringRequest);
    }

    public void loadKategori(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DETAIL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    JSONObject obj = array.getJSONObject(0);
                    JSONArray tags = obj.getJSONArray("kategori");
                    for(int i = 0; i < tags.length(); i++){
                        lstTag.add(new Tag(
                           tags.getString(i)
                        ));
                    }

                    SlideTagAdapter adapter = new SlideTagAdapter(DetailActivity.this,lstTag);
                    slideTagRecyclerView.setAdapter(adapter);
                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(DetailActivity.this).add(stringRequest);
    }

    public void saveFavorite(){
        favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
        favorite = new News();
        int news_id = getIntent().getExtras().getInt("id");
        String title = getIntent().getExtras().getString("title");
        String content = getIntent().getExtras().getString("content");
        String tag = getIntent().getExtras().getString("tag");
        String tanggal = getIntent().getExtras().getString("tanggal");
        String desc = getIntent().getExtras().getString("desc");
        String gambar = getIntent().getExtras().getString("gambar");

        favorite.setId(news_id);
        favorite.setJudul(title);
        favorite.setTag(tag);
        favorite.setGambar(gambar);
        favorite.setTanggal(tanggal);
        favorite.setIsi(content);
        favorite.setDescription(desc);

        favoriteDbHelper.addFavorite(favorite);
    }

    public boolean ExistsId(String searchItem){
        String[] projection = {
                FavoriteContract.FavoriteEntry._ID,
                FavoriteContract.FavoriteEntry.COLUMN_NEWSID,
                FavoriteContract.FavoriteEntry.COLUMN_TITLE,
                FavoriteContract.FavoriteEntry.COLUMN_CONTENT,
                FavoriteContract.FavoriteEntry.COLUMN_TANGGAL,
                FavoriteContract.FavoriteEntry.COLUMN_DESC,
                FavoriteContract.FavoriteEntry.COLUMN_TAG,
                FavoriteContract.FavoriteEntry.COLUMN_GAMBAR,
        };
        String selection = FavoriteContract.FavoriteEntry.COLUMN_NEWSID + " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";

        Cursor cursor = nDB.query(FavoriteContract.FavoriteEntry.TABLE_NAME,projection, selection, selectionArgs,null,null,null,limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}
