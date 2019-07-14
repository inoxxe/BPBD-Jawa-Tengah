package com.ino.bpbd;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ino.bpbd.Fragments.BookmarkFragment;
import com.ino.bpbd.Fragments.CategoryFragment;
import com.ino.bpbd.Fragments.NewsFragment;

public class NewsActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private NewsFragment newsFragment;
    private CategoryFragment categoryFragment;
    private BookmarkFragment bookmarkFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        newsFragment = new NewsFragment();
        categoryFragment = new CategoryFragment();
        bookmarkFragment = new BookmarkFragment();

        setFragment(newsFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.nav_news:
                        getSupportActionBar().setTitle("News");
                        setFragment(newsFragment);
                        return true;
                    case R.id.nav_category:
                        getSupportActionBar().setTitle("Kategori");
                        setFragment(categoryFragment);
                        return true;
                    case R.id.nav_bookmark:
                        getSupportActionBar().setTitle("Bookmark");
                        setFragment(bookmarkFragment);
                        return true;
                    default:
                        return false;
                }
            }

        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchnews, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.search) {
            return super.onOptionsItemSelected(menuItem);
        }
        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
        return true;
    }

    private void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }
}
