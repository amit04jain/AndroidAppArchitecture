package com.orsanapps.news.trendingnews.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 1/26/2017.
 */

public abstract class AbstractDatabaseManager<T> {
    private static String TAG = AbstractDatabaseManager.class.getSimpleName();
    Cursor cursor;
    protected SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context mContext;

    public AbstractDatabaseManager(SQLiteDatabase database) {
        this.database = database;
    }

    public AbstractDatabaseManager() {

    }

    public boolean save(ContentValues contentValues) {
        database = DatabaseManager.getInstance().openDatabase();

        if (contentValues == null)
            return false;
        if (!database.isOpen()) {
            database = DatabaseManager.getInstance().openDatabase();
        }

        long result = database.insertOrThrow(getTableName(), null, contentValues);
        DatabaseManager.getInstance().closeDatabase();

        return result > 0;
    }

    public boolean saveAll(AbstractDatabaseManager manager, String tableName, List<T> entity) {
        database = DatabaseManager.getInstance().openDatabase();
        if (entity == null) {
            return false;
        }

        database.beginTransaction();
        long result = 0;
        for (int i = 0; i < entity.size(); i++) {
            result = database.insert(tableName, null, manager.generateContentValuesFromObject(entity.get(i)));
            Log.e(TAG, "saveAll - RESULT " + result);
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        DatabaseManager.getInstance().closeDatabase();

        return result != -1;
    }

    public List<Object> loadAll(AbstractDatabaseManager manager, final String query) {

        database = DatabaseManager.getInstance().openDatabase();

        Cursor cursor = database.rawQuery(query, null);
        Log.e(TAG, "load: " + cursor.toString());
        List<Object> data = new ArrayList();

        if (cursor != null && cursor.moveToFirst()) {

            while (!cursor.isAfterLast()) {

                data.add(manager.generateObjectFromCursor(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }
        DatabaseManager.getInstance().closeDatabase();


        return data;
    }


    public abstract ContentValues generateContentValuesFromObject(T t);

    public abstract T generateObjectFromCursor(Cursor c);

    public abstract String getCreateTableQuery();

    public abstract String getTableName();


}
