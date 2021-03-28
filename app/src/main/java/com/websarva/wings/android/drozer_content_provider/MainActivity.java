package com.websarva.wings.android.drozer_content_provider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri;

        // select
        uri = UserColumns.CONTENT_URI;
        Cursor c = managedQuery(uri, null, null, null, null);

        // テーブルのデータを全件検索.表示
        startManagingCursor(c);

        getColumnData(c);
    }

    // getColumnData関数の定義
    // name,emailをアプリ画面内に出力
    // passwordはコメント化
    private void getColumnData(Cursor cur){
        if (cur.moveToFirst()){
            String email;
            String name;
        //    String password_encode;
            int emailColumn = cur.getColumnIndex(UserColumns.EMAIL);
            int nameColumn = cur.getColumnIndex(UserColumns.NAME);
        //    int passwordColumn = cur.getColumnIndex(UserColumns.PASSWORD);
            List<Map<String,String>> userList = new ArrayList<>();
            Map<String,String> user = new HashMap<>();
            do {
                email = cur.getString(emailColumn);
                name = cur.getString(nameColumn);
        //        password_encode = cur.getString(passwordColumn);
        //        String password = new String(Base64.decode(password_encode,Base64.DEFAULT));
                user.put(UserColumns.NAME,name);
                user.put(UserColumns.EMAIL,email);
        //        user.put("password",password);
        //        Log.v("LogSample", password);
                userList.add(user);
                user = new HashMap<>();
            }while (cur.moveToNext());
            SimpleAdapter adapter = new SimpleAdapter(this, userList, android.R.layout.simple_list_item_2, new String[]{
                    UserColumns.NAME, UserColumns.EMAIL}, new int[]{
                    android.R.id.text1, android.R.id.text2});
            ListView lv = new ListView(this);
            lv.setAdapter(adapter);
            setContentView(lv);
        }
    }
}