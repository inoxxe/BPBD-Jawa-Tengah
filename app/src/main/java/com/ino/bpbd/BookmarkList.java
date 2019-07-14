package com.ino.bpbd;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ino.bpbd.Adapter.NewsAdapter;
import com.ino.bpbd.Data.FavoriteDbHelper;
import com.ino.bpbd.Model.News;

import java.util.ArrayList;
import java.util.List;

public class BookmarkList extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView bookmarkListRecyclerView;
    private List<News> lstNews;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsAdapter adapter;
    private FavoriteDbHelper favoriteDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_list);

        getSupportActionBar().setTitle("Daftar Bookmark");

        lstNews = new ArrayList<>();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        bookmarkListRecyclerView = (RecyclerView) findViewById(R.id.bookmark_list_rv);
        bookmarkListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(BookmarkList.this,lstNews);
        adapter.notifyDataSetChanged();
        bookmarkListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        bookmarkListRecyclerView.setHasFixedSize(true);
        bookmarkListRecyclerView.setAdapter(adapter);
        favoriteDbHelper = new FavoriteDbHelper(BookmarkList.this);

        onLoadingSwipeRefresh();
    }

    @Override
    public void onRefresh() {
        getAllFavorite();
    }

    private void onLoadingSwipeRefresh(){
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                getAllFavorite();
            }
        });
    }

    private void getAllFavorite(){
        swipeRefreshLayout.setRefreshing(true);
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params){
                lstNews.clear();
                lstNews.addAll(favoriteDbHelper.getAllFavorite());
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        }.execute();
        swipeRefreshLayout.setRefreshing(false);
    }
}
