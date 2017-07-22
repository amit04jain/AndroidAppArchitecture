package com.orsanapps.news.trendingnews.sql;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Home on 1/26/2017.
 */

public class DatabaseManager {
    private static final String TAG = "DatabaseManager";
    private static DatabaseManager instance;
    private static DatabaseHelper mDatabaseHelper;
    private int mOpenCounter;
    private SQLiteDatabase mDatabase;

    public static synchronized void initializeInstance(DatabaseHelper helper) {
        if (instance == null) {
            instance = new DatabaseManager();
            mDatabaseHelper = helper;
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }

        return instance;
    }

    public synchronized SQLiteDatabase openDatabase() {
        mOpenCounter++;
        if (mOpenCounter == 1) {
            // Opening new database
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }
        Log.d(TAG, "openDatabase: " + mOpenCounter);
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        mOpenCounter--;
        if (mOpenCounter == 0) {
            // Closing database
            mDatabase.close();

        }
        Log.d(TAG, "closeDatabase: " + mOpenCounter);
    }

}
