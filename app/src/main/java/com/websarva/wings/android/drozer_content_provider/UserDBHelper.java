package com.websarva.wings.android.drozer_content_provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper {
    // DB名とバージョン
    final static String DB_NAME = "database";
    private static final int DB_VERSION = 1;

    // コンストラクタ
    public UserDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        // データベースのテーブルを作成する
        sqLiteDatabase.execSQL("CREATE TABLE " + DB_NAME + " (" + UserColumns._ID + " INTEGER PRIMARY KEY,"
                + UserColumns.NAME + " TEXT," + UserColumns.EMAIL + " TEXT" + ");");

        // テーブルにデータを挿入する
        sqLiteDatabase.execSQL("insert into " + DB_NAME + "(" + UserColumns.NAME + ","
                + UserColumns.EMAIL + ") values ('山田 太郎', 'hogehoge@gmail.com');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){

    }
}