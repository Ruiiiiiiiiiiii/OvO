package com.rarcher.ovo.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.rarcher.ovo.model.History_Been;
import java.io.ByteArrayOutputStream;

/**
 * Created by 25532 on 2019/4/1.
 */

public class LocalDB extends SQLiteOpenHelper {

    public static final String Creat_Book = "create table History ("

            + "id integer primary key autoincrement," +
            "context text," +
            "year text," +
            "mouth text,"+
            "day text," +
            "name text) ";

    public LocalDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Creat_Book);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists History");
        onCreate(db);
    }

    public static void insert_info(String context, String year, String mouth, String day, String name, LocalDB dbhelper) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        values.put("context", context);
        values.put("year", year);
        values.put("mouth",mouth);
        values.put("name", name);
        values.put("day", day);
        db.insert("History", null, values);

    }

    //查找本用户名,返回一个Uer类型
    public static History_Been query_user(LocalDB dbhelper) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("History", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String context = cursor.getString(cursor.getColumnIndex("context"));
                String year = cursor.getString(cursor.getColumnIndex("year"));
                String mouth = cursor.getString(cursor.getColumnIndex("mouth"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String day = cursor.getString(cursor.getColumnIndex("day"));
                History_Been history_been = new History_Been(context, year, mouth, day,name);
                return history_been;
            }
            while (cursor.moveToNext());
        }

        cursor.close();

        return null;


    }


}
