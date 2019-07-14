package com.ino.bpbd.Fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ino.bpbd.Adapter.NewsAdapter;
import com.ino.bpbd.BookmarkList;
import com.ino.bpbd.Data.FavoriteDbHelper;
import com.ino.bpbd.Model.News;
import com.ino.bpbd.R;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookmarkFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView bookmarkRecyclerView;
    private List<News> lstNews;
    private View view;
    private NewsAdapter adapter;
    private FavoriteDbHelper favoriteDbHelper;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button moreButton;
    private MaterialSearchBar searchBar;
    private List<String> suggestList = new ArrayList<>();


    public BookmarkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        moreButton = view.findViewById(R.id.more_button);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        onLoadingSwipeRefresh();

        lstNews = new ArrayList<>();
        bookmarkRecyclerView = (RecyclerView) view.findViewById(R.id.bookmark_rv);
        bookmarkRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new NewsAdapter(getActivity(),lstNews);
        adapter.notifyDataSetChanged();
        bookmarkRecyclerView.setItemAnimator(new DefaultItemAnimator());
        bookmarkRecyclerView.setHasFixedSize(true);
        bookmarkRecyclerView.setAdapter(adapter);
        favoriteDbHelper = new FavoriteDbHelper(getActivity().getApplicationContext());

        searchBar = (MaterialSearchBar) view.findViewById(R.id.search_bar);

        //setup searchbar
        searchBar.setHint("Pencarian");
        searchBar.setCardViewElevation(10);
        loadSuggestList();
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for(String search:suggestList){
                    if(search.toLowerCase().contains(searchBar.getText().toLowerCase())){
                        suggest.add(search);
                    }
                }
                searchBar.setLastSuggestions(suggest);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){
                    adapter = new NewsAdapter(getActivity(),lstNews);
                    bookmarkRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });


        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),BookmarkList.class);
                getContext().startActivity(intent);
            }
        });

        return view;
    }

    private void startSearch(String text) {
        adapter = new NewsAdapter(getActivity(),favoriteDbHelper.getTitleByName(text));
        bookmarkRecyclerView.setAdapter(adapter);
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

    private void loadSuggestList(){
        suggestList = favoriteDbHelper.getTitle();
        searchBar.setLastSuggestions(suggestList);
    }


}
