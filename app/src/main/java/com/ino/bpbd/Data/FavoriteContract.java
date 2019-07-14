package com.ino.bpbd.Data;

import android.provider.BaseColumns;

public class FavoriteContract {

    public static final class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_NEWSID = "newsid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_TAG = "tag";
        public static final String COLUMN_TANGGAL = "tanggal";
        public static final String COLUMN_GAMBAR = "gambar";

    }
}
