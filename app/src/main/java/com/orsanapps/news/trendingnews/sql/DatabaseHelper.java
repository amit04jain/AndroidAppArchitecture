package com.orsanapps.news.trendingnews.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Home on 1/26/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    static final int DB_VERSION = 1;
    private static DatabaseHelper instance;
    SQLiteDatabase db;
    static final String DB_NAME = "APP.DB";

    public DatabaseHelper(Context context) {
        super(context, "app.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }

        return instance;
    }

    public void onCreate(SQLiteDatabase db) {
        this.db = db;

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public String getDatabaseName() {
        return super.getDatabaseName();
    }
}
