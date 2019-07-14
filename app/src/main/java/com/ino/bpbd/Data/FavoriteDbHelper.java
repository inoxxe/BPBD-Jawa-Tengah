package com.ino.bpbd.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.ino.bpbd.Model.News;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDbHelper extends SQLiteOpenHelper {

    private SwipeRefreshLayout swipeRefreshLayout;

    private static final String DATABASE_NAME = "favorite.db";

    private static final int DATABASE_VERSION = 1;

    public static final String LOGTAG = "FAVORITE";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase db;

    public FavoriteDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        db = dbhandler.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG,"Database Closed");
        dbhandler.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + FavoriteContract.FavoriteEntry.TABLE_NAME + " (" +
                FavoriteContract.FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FavoriteContract.FavoriteEntry.COLUMN_NEWSID + " INTEGER, " +
                FavoriteContract.FavoriteEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                FavoriteContract.FavoriteEntry.COLUMN_CONTENT + " TEXT NOT NULL, " +
                FavoriteContract.FavoriteEntry.COLUMN_TANGGAL + " TEXT NOT NULL, " +
                FavoriteContract.FavoriteEntry.COLUMN_DESC + " TEXT NOT NULL, " +
                FavoriteContract.FavoriteEntry.COLUMN_TAG + " TEXT NOT NULL, " +
                FavoriteContract.FavoriteEntry.COLUMN_GAMBAR + " TEXT " +
                " ); ";
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteContract.FavoriteEntry.TABLE_NAME);
        onCreate(db);
    }

    public void addFavorite(News news){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FavoriteContract.FavoriteEntry.COLUMN_NEWSID, news.getId());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_TITLE, news.getJudul());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_CONTENT, news.getDescription());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_TANGGAL, news.getTanggal());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_DESC, news.getDescription());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_TAG, news.getTag());
        values.put(FavoriteContract.FavoriteEntry.COLUMN_GAMBAR, news.getGambar());

        db.insert(FavoriteContract.FavoriteEntry.TABLE_NAME,null,values);
        db.close();
    }

    public void deleteFavorite(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FavoriteContract.FavoriteEntry.TABLE_NAME,FavoriteContract.FavoriteEntry.COLUMN_NEWSID + "=" + id,null);
    }

    public List<String> getTitle(){
        String[] columns = {
                FavoriteContract.FavoriteEntry.COLUMN_TITLE
        };

        String sortOrder = FavoriteContract.FavoriteEntry._ID + " ASC";

        List<String> favoriteList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FavoriteContract.FavoriteEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if(cursor.moveToFirst()){
            do {
                favoriteList.add(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_TITLE)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favoriteList;
    }

    public List<News> getTitleByName(String title){
        String[] columns = {
                FavoriteContract.FavoriteEntry._ID,
                FavoriteContract.FavoriteEntry.COLUMN_NEWSID,
                FavoriteContract.FavoriteEntry.COLUMN_TITLE,
                FavoriteContract.FavoriteEntry.COLUMN_CONTENT,
                FavoriteContract.FavoriteEntry.COLUMN_TANGGAL,
                FavoriteContract.FavoriteEntry.COLUMN_DESC,
                FavoriteContract.FavoriteEntry.COLUMN_TAG,
                FavoriteContract.FavoriteEntry.COLUMN_GAMBAR
        };

        String sortOrder = FavoriteContract.FavoriteEntry._ID + " ASC";

        List<News> favoriteList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FavoriteContract.FavoriteEntry.TABLE_NAME,
                columns,
                FavoriteContract.FavoriteEntry.COLUMN_TITLE+" LIKE ?",
                new String[]{"%"+title+"%"},
                null,
                null,
                sortOrder);

        if(cursor.moveToFirst()){
            do {
                News news = new News();
                news.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_NEWSID))));
                news.setJudul(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_TITLE)));
                news.setDescription(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_DESC)));
                news.setIsi(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_CONTENT)));
                news.setTanggal(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_TANGGAL)));
                news.setTag(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_TAG)));
                news.setGambar(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_GAMBAR)));

                favoriteList.add(news);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favoriteList;
    }

    public List<News> getAllFavorite(){

        String[] columns = {
                FavoriteContract.FavoriteEntry._ID,
                FavoriteContract.FavoriteEntry.COLUMN_NEWSID,
                FavoriteContract.FavoriteEntry.COLUMN_TITLE,
                FavoriteContract.FavoriteEntry.COLUMN_CONTENT,
                FavoriteContract.FavoriteEntry.COLUMN_TANGGAL,
                FavoriteContract.FavoriteEntry.COLUMN_DESC,
                FavoriteContract.FavoriteEntry.COLUMN_TAG,
                FavoriteContract.FavoriteEntry.COLUMN_GAMBAR
        };

        String sortOrder = FavoriteContract.FavoriteEntry._ID + " ASC";

        List<News> favoriteList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FavoriteContract.FavoriteEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if(cursor.moveToFirst()){
            do {
                News news = new News();
                news.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_NEWSID))));
                news.setJudul(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_TITLE)));
                news.setDescription(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_DESC)));
                news.setIsi(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_CONTENT)));
                news.setTanggal(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_TANGGAL)));
                news.setTag(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_TAG)));
                news.setGambar(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMN_GAMBAR)));


                favoriteList.add(news);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favoriteList;
    }

}
