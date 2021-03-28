package com.websarva.wings.android.drozer_content_provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class UserColumns implements BaseColumns {
    // ユーザ URIパス
    public static final String PATH = "user";
    // ユーザ コンテントURI
    public static final Uri CONTENT_URI = Uri.parse("content://" + UserContentProvider.AUTHORITY + "/" + PATH);
    // ユーザ レコード個別指定コンテントタイプ
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + UserContentProvider.AUTHORITY + ".users";
    // ユーザ テーブル指定コンテントタイプ
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + UserContentProvider.AUTHORITY + ".users";

    // admin URIパス
    public static final String PATH_ADMIN = "admin";
    // admin コンテントURI
    public static final Uri CONTENT_URI_ADMIN = Uri.parse("content://" + UserContentProvider.AUTHORITY + "/" + PATH_ADMIN);
    // admin レコード個別指定コンテントタイプ
    public static final String CONTENT_ITEM_TYPE_ADMIN = "vnd.android.cursor.item/" + UserContentProvider.AUTHORITY + ".admins";
    // admin テーブル指定コンテントタイプ
    public static final String CONTENT_TYPE_ADMIN = "vnd.android.cursor.dir/" + UserContentProvider.AUTHORITY + ".admins";

    // ユーザ テーブル名
    public static final String TABLE = "users";
    // admin テーブル名
    public static final String TABLE_ADMIN = "admins";
    // カラム 名前
    public static final String NAME = "name";
    // カラム メールアドレス
    public static final String EMAIL = "email";
    // カラム パスワード
    public static final String PASSWORD = "password";

    // コンストラクタ
    private UserColumns(){}
}
