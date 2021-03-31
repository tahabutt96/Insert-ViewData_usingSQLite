package com.arsalan.quiz3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "PetShopdb";
    public static final int DB_VERSION = 1;
    public static final String QUERY_CREATE_PETSHOP_TABLE ="create table Petshop(name text, age integer,color text, gender text)";
    public DataBaseHelper(Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY_CREATE_PETSHOP_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Petshop");
        onCreate(sqLiteDatabase);
    }
    long insertData(String name, int age, String color, String gender ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name", name);
        content.put("age", age);
        content.put("color", color);
        content.put("gender", gender);

        long id = db.insert("Petshop", null, content);
        db.close();
        return id;
    }

    Cursor getAllData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Petshop order by rowid desc", null);
        return cursor;
    }
}

