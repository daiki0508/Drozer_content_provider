package com.websarva.wings.android.drozer_content_provider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

public class UserContentProvider extends android.content.ContentProvider {
    // Authority
    public static final String AUTHORITY = "com.websarva.wings.android.drozer_content_provider.provider";

    // USERS テーブル URI ID
    private static final int USERS = 1;
    // USERS テーブル 個別 URI ID
    private static final int USERS_ID = 2;

    // 利用者がメソッドを呼び出したURIに対応する処理を判定処理に仕様します
    private static HashMap<String, String> personProjectionMap;
    private static final UriMatcher sUriMatcher;
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, UserColumns.PATH, USERS);
        sUriMatcher.addURI(AUTHORITY, UserColumns.PATH + "/#",USERS_ID);

        personProjectionMap = new HashMap<String, String>();
        personProjectionMap.put(UserColumns._ID, UserColumns._ID);
        personProjectionMap.put(UserColumns.NAME, UserColumns.NAME);
        personProjectionMap.put(UserColumns.EMAIL, UserColumns.EMAIL);
    }

    // DBHelperのインスタンス
    private UserDBHelper mDBHelper;

    // コンテントプロバイダの作成
    @Override
    public boolean onCreate(){
        mDBHelper = new UserDBHelper(getContext());
        return true;
    }

    // query実行
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(UserDBHelper.DB_NAME);
        switch (sUriMatcher.match(uri)){
            case USERS:
                queryBuilder.setProjectionMap(personProjectionMap);
                break;
            case USERS_ID:
                queryBuilder.setProjectionMap(personProjectionMap);
                queryBuilder.appendWhere(UserColumns._ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    // insert実行
    @Override
    public Uri insert(Uri uri, ContentValues values){
        String insertTable;
        Uri contentUri;
        switch (sUriMatcher.match(uri)){
            case USERS:
                insertTable = UserColumns.TABLE;
                contentUri = UserColumns.CONTENT_URI;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        long rowId = db.insert(insertTable, null, values);
        if (rowId > 0){
            Uri returnUri = ContentUris.withAppendedId(contentUri, rowId);
            getContext().getContentResolver().notifyChange(returnUri, null);
            return returnUri;
        }else {
            throw new IllegalArgumentException("Failed to insert row into " + uri);
        }
    }

    // update実行
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)){
            case USERS:
                count = db.update(UserDBHelper.DB_NAME, values, selection, selectionArgs);
                break;
            case USERS_ID:
                String id = uri.getPathSegments().get(1);
                count = db.update(UserDBHelper.DB_NAME, values, UserColumns._ID + "="
                        + id + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""),selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    // delete実行
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)){
            case USERS:
                count = db.delete(UserDBHelper.DB_NAME, selection, selectionArgs);
                break;
            case USERS_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(UserColumns.TABLE, UserColumns._ID + "=" + id
                        + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : ""),selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    // コンテントタイプ取得
    @Override
    public String getType(Uri uri){
        switch (sUriMatcher.match(uri)){
            case USERS:
                return UserColumns.CONTENT_TYPE;
            case USERS_ID:
                return UserColumns.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
}
