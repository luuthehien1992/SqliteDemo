package com.ersolution.ernavigator.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Jeremy on 8/6/2014.
 */
public class SQLiteAdapter extends DBAdapter {
    public static final String TAG = "DBAdapter";

    private static SQLiteHelper sqLiteHelper = null;
    private static SQLiteAdapter sqliteAdapter = null;

    public static SQLiteAdapter createInstance(Context context) {
        if (sqliteAdapter == null)
            sqliteAdapter = new SQLiteAdapter(context);

        return sqliteAdapter;
    }

    private SQLiteAdapter(Context context) {
        sqLiteHelper = new SQLiteHelper(context);
    }

    private static synchronized SQLiteDatabase open() throws SQLException {
        return sqLiteHelper.getWritableDatabase();
    }

    public void test() {
        SQLiteDatabase db = open();
        ContentValues cVal = new ContentValues();
        cVal.put("ID", "2");

        db.insert("My", null, cVal);
        db.close();
    }

    public synchronized void add(String table, ContentValues contentValues) {
        SQLiteDatabase db = open();
        db.insert(table, null, contentValues);
        db.close();
    }

    public synchronized Bitmap getBitmap(String url) {
        SQLiteDatabase db = open();

        Cursor cursor = db.query(IMAGE_TABLE,
                new String[]
                        {
                                IMAGE_TABLE_IMAGE
                        },
                "WHERE " + IMAGE_TABLE_URL + " = ?",
                new String[]
                        {
                                url
                        },
                null,
                null,
                null
        );

        if (cursor.getCount() <= 0) {
            return null;
        }

        Bitmap bitmap = decodeBitmap(cursor.getBlob(0));
        db.close();

        return bitmap;
    }

    private static Bitmap decodeBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


    public class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(TAG, "onCreate");

            for (String sql : ALL_TABLES) {
                sqLiteDatabase.execSQL(sql);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            Log.d(TAG, "onUpgrade");
        }
    }
}
