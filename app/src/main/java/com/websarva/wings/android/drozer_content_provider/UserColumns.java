package com.websarva.wings.android.drozer_content_provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class UserColumns implements BaseColumns {
    // URIパス
    public static final String PATH = "user";
    // コンテントURI
    public static final Uri CONTENT_URI = Uri.parse("content://" + UserContentProvider.AUTHORITY + "/" + PATH);
    // レコード個別指定コンテントタイプ
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + UserContentProvider.AUTHORITY + ".users";
    // テーブル指定コンテントタイプ
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + UserContentProvider.AUTHORITY + ".users";

    // テーブル名
    public static final String TABLE = "users";
    // カラム 名前
    public static final String NAME = "name";
    // カラム メールアドレス
    public static final String EMAIL = "email";
    // カラム パスワード
    public static final String PASSWORD = "password";

    // コンストラクタ
    private UserColumns(){}
}
