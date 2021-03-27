package com.websarva.wings.android.drozer_content_provider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri;
        // ContentResolver cr = getContentResolver();
        // ContentValues values;

        // select
        uri = UserColumns.CONTENT_URI;
        Cursor c = managedQuery(uri, null, null, null, null);
       /* ListAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c, new String[]{
                UserColumns.NAME, UserColumns.EMAIL}, new int[]{
                        android.R.id.text1, android.R.id.text2});*/

        // テーブルのデータを全件検索.表示
        startManagingCursor(c);
       /* ListView lv = new ListView(this);
        lv.setAdapter(adapter);
        setContentView(lv);*/

        getColumnData(c);
    }

    // getColumnData関数の定義
    // emailをLogに出力
    private void getColumnData(Cursor cur){
        if (cur.moveToFirst()){
            String email;
            int emailColumn = cur.getColumnIndex(UserColumns.EMAIL);

            do{
                email = cur.getString(emailColumn);
                Log.v("LogSample",email);
            }while (cur.moveToNext());
        }
    }
}