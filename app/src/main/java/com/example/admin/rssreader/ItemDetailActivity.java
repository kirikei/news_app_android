package com.example.admin.rssreader;

/**
 * Created by admin on 2014/11/05.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;


public class ItemDetailActivity extends Activity {
    private TextView mTitle;
    private TextView mDescr;
    private TextView mLink;
    WebView webview;//webviewを定義

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        Intent intent = getIntent();


        String link = intent.getStringExtra("LINK");//LINKがkeyであるもの、即ちリンク先のURLを取り出す。
        webview = (WebView)findViewById(R.id.WebView1);
        webview.setWebViewClient(new WebViewClient());//webviewClientでブラウザなしにページが表示できるように？
        webview.getSettings().setJavaScriptEnabled(true);//JavaScriptが読めるように
        webview.loadUrl(link);

        webview.getSettings().setBuiltInZoomControls(true);//webviewに拡大縮小機能を追加

        Button btnDisp = (Button)findViewById(R.id.backbutton);//戻るボタンの動作
        btnDisp.setOnClickListener(new View.OnClickListener() {//clickされたら
            public void onClick(View v) {
                finish();//元のアクティビティに戻る
            }
        });



//        String title = intent.getStringExtra("TITLE");
//        mTitle = (TextView) findViewById(R.id.item_detail_title);
//        mTitle.setText(title);
//        String descr = intent.getStringExtra("DESCRIPTION");
//        mDescr = (TextView) findViewById(R.id.item_detail_descr);
//        mDescr.setText(descr);
    }

    @Override
    public void onDestroy(){//activityの最後に動く
        super.onDestroy();//もともとあるDestroy()を継承
        setVisible(false);//Viewを閉じるとき全ての機能を閉じる？
    }



}



