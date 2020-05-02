package com.example.testvolley;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "user";
    public static final String TABLE_USER = "tbl_user";

    public static final String KEY_ID = "_id";
    public static final String KEY_NICKNAME = "nickname";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_PASS = "pass";







    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_USER + "(" + KEY_ID + " integer primary key," + KEY_NICKNAME + " text," + KEY_TOKEN + " text," + KEY_PASS + " text" + ");" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_USER );
        onCreate(db);

    }
}
