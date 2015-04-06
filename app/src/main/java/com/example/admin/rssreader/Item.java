package com.example.admin.rssreader;

/**
 * Created by admin on 2014/11/05.
 */
//記事データの構造を示している（タイトルと本文）
public class Item {
    // 記事のタイトル
    private CharSequence mTitle;//データ格納の為のフィールド
    // 記事の本文
    private CharSequence mDescription;
    // 記事のリンク
    private CharSequence mLink;

    public Item() {
        mTitle = "";
        mDescription = "";
    }
    public CharSequence getLink(){ return mLink;}

    public void setLink(CharSequence link){ mLink = link; }

    public CharSequence getDescription() {//データ格納の為のアクセサメソッド（クラス内の変数に外からアクセスできるように）
        return mDescription;
    }

    public void setDescription(CharSequence description) {
        mDescription = description;
    }

    public CharSequence getTitle() {
        return mTitle;
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
    }

}

