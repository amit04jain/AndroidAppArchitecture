package com.orsanapps.news.trendingnews.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.orsanapps.news.trendingnews.sql.AbstractDatabaseManager;
import com.orsanapps.news.trendingnews.sql.DatabaseHelper;
import com.orsanapps.news.trendingnews.vo.TestVO;

/**
 * Created by Home on 1/26/2017.
 */

public class TestDAO extends AbstractDatabaseManager<TestVO> {
    private static final String TAG = "TestDAO";
    protected SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;

    private static String TEST_ID = "TEST_ID";
    private static String TEST_NAME = "TEST_NAME";
    private static String TEST_REAL = "TEST_REAL";
    private static String TEST_BLOB = "TEST_BLOB";

    public TestDAO(Context context) {
        this.context = context;
        dbHelper = DatabaseHelper.getInstance(context);
    }

    @Override
    public String getTableName() {
        return "TEST";
    }

    @Override
    public String getCreateTableQuery() {
        return "CREATE TABLE " + getTableName() + " (" +
                "   " + TEST_ID + " INT PRIMARY KEY  NOT NULL," +
                "   " + TEST_NAME + "  TEXT    NOT NULL," +
                "   " + TEST_REAL + "  REAL     NOT NULL," +
                "   " + TEST_BLOB + "  BLOB     NOT NULL," +
                "); ";
    }

    @Override
    public ContentValues generateContentValuesFromObject(TestVO result) {
        if (result == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEST_ID, result.getTestID());
        contentValues.put(TEST_NAME, result.getTestName());
        contentValues.put(TEST_REAL, result.getTestReal());
        contentValues.put(TEST_BLOB, result.getTestBlob());
        return contentValues;
    }


    public TestVO generateObjectFromCursor(Cursor c) {
        if (c == null)
            return null;
        TestVO result = new TestVO();
        try {
            if (c != null && c.moveToFirst()) {
                do {
                    result.setTestID(c.getInt(c.getColumnIndex(TEST_ID)));
                    result.setTestName(c.getString(c.getColumnIndex(TEST_NAME)));
                    result.setTestReal(c.getDouble(c.getColumnIndex(TEST_REAL)));
                    result.setTestBlob(c.getString(c.getColumnIndex(TEST_BLOB)));

                }
                while (c.moveToNext());
            }
            return result;
        } finally {
            if (c != null)
                c.close();
        }


    }

}
