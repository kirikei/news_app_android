package com.example.admin.rssreader;

/**
 * Created by admin on 2014/11/05.
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class RssParserTask extends AsyncTask<String, Integer, RssListAdapter>{//AsyncTaskの使用はクラスの継承で
    private MainActivity mActivity;
    private RssListAdapter mAdapter;
    private ProgressDialog mProgressDialog;//処理待ちのダイアログ
    private ListView mListview; //Main.xmlに表示するlistview

    // コンストラクタ
    public RssParserTask(MainActivity activity, RssListAdapter adapter,ListView _list) {
        mActivity = activity;
        mAdapter = adapter;
        mListview = _list;
    }

    // タスク（UIの処理）を実行した直後にコールされる
    @Override
    protected void onPreExecute() {
        // プログレスバー（読み込み状況）を表示する
        mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setMessage("Now Loading...");
        mProgressDialog.show();
    }

    // バックグラウンドにおける処理を担う。タスク実行時に渡された値を引数とする
    @Override
    protected RssListAdapter doInBackground(String... params) {
        RssListAdapter result = null;//parseした結果を返す
        try {
            // HTTP経由でアクセスし、InputStreamを取得する
            //Log.d("app6", "test");
            URL url = new URL(params[0]);
            Log.d("URL", "URL=" + url);
            InputStream is = url.openConnection().getInputStream();
            Log.d("appAd", "is=" + is);
            result = parseXml(is);//RSSをパースする関数
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("appAd2", "error=" + e);
        }
        // ここで返した値は、onPostExecuteメソッドの引数として渡される
        return result;
    }

    // メインスレッド上で実行される
    @Override
    protected void onPostExecute(RssListAdapter result) {
        mProgressDialog.dismiss();//dismissによってprogress(処理待ち)を破棄
        mListview.setAdapter(result);//Listviewにresultをセットする。
        Log.d("App5", "result=" + result);
    }

    // XMLをパースする
    //XML文書を頭から読み、各イベントに対して個別に処理
    public RssListAdapter parseXml(InputStream is) throws IOException, XmlPullParserException {
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(is, null);
            int eventType = parser.getEventType();
            //Log.d("App4", "event=" + eventType);
            Item currentItem = null;//該当するタグの文章が格納される
            while (eventType != XmlPullParser.END_DOCUMENT) {//全て読み終わるまで
                String tag = null;
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.equals("item")) {
                            currentItem = new Item();
                        } else if (currentItem != null) {
                            if (tag.equals("title")) {//titleと
                                currentItem.setTitle(parser.nextText());
                            }else if (tag.equals("link")){
                                   currentItem.setLink(parser.nextText());
                                 } else if (tag.equals("description")) {//descriptionのみを用いる
                                        CharSequence str = parser.nextText();
                                        str = DetectDescription(str);
                                        //Log.d("Apppp", "str=" + str);
                                        currentItem.setDescription(str);
                               }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        if (tag.equals("item")) {
                            mAdapter.add(currentItem);
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mAdapter;
    }

    private CharSequence DetectDescription(CharSequence str){//Descriptionからタグを消去
        String change = str.toString();//String型でないとreplaceAllが使えない
        str = change.replaceAll("<.+?>", "");
    return str;
    }

}
