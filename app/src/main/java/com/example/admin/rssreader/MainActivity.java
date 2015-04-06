package com.example.admin.rssreader;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

//メインアクティビティ
public class MainActivity extends ListActivity {//クラスlistActivityを継承
    Intent intent = getIntent();
    String category = null;
    public static final int MENU_ITEM_RELOAD = Menu.FIRST;
    public static final String RSS_FEED_URL = "https://news.google.com/news/feeds?hl=en&ned=us&ie=UTF-8&oe=UTF-8&output=rss";
    public static final String LINK_URL = "http://localhost:3000/api/v1/tid/links";
    public static final String TOP_URL = "http://localhost:3000/api/v1/tid";
    private ArrayList<Item> mItems;
    private RssListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//アプリ実行時に最初に呼び出される
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //activity_rss_reader.xmlをsetContestViewで設定

        // Itemオブジェクトを保持するためのリストを生成し、アダプタに追加する
        mItems = new ArrayList<Item>();
        mAdapter = new RssListAdapter(this, android.R.layout.simple_expandable_list_item_1, mItems);//RsslistAdapter型
        Log.d("main", "intent=" + intent);
        ListView _list = (ListView) findViewById(android.R.id.list);
        // アダプタをリストビューにセットする
//     		setListAdapter(mAdapter);//setListAdapterは古いコマンド。
//
//     		// サンプル用に空のItemオブジェクトをセットする
//     		for (int i = 0; i < 10; i++) {
//     			mAdapter.add(new Item());
//     		}
        //※上の処理をRssParserTask内で処理する
        // タスクを起動する


        RssParserTask task = new RssParserTask(this, mAdapter, _list);
        //task.execute(RSS_FEED_URL);//タスクの実行は１回だけ。もう一度実行するならばインスタンスを新たに作成

        //タイトルとdescriptionを並べるトップ記事表示画面
        if(intent != null){
            category = intent.getStringExtra("push_category");
            task.execute(RSS_FEED_URL + "&topic=" + category);
        }else{
            task.execute(RSS_FEED_URL);
        }

        //記事詳細表示画面へ遷移
        _list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override // リストの項目を選択した時の処理
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {  //Itemをクリックした時のイベント
                // TODO Auto-generated method stub
                Item item = mItems.get(arg2);
//                CharSequence link = item.getLink();//parserで得られたlinkを取り出す
//                Uri uri = Uri.parse((String) link);
//                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);//行き先：ItemDetailActivityをセット
                intent.putExtra("LINK",item.getLink());//Parserで得られたLINKをItemDetailActivityへIntentを通じてput
                startActivity(intent);//ItemDetailActivityへ移行
            }
        });

        Button Topbtn = (Button)findViewById(R.id.buttonTop);//各カテゴリボタンの動作
        Topbtn.setOnClickListener(new View.OnClickListener() {//clickされたら
            public void onClick(View v) {//vにはTopbtnが渡される
                Intent Topintent = new Intent(getApplicationContext(), MainActivity.class);//ピックアップニュース
                Topintent.putExtra("push_category", "ir");
                startActivity(Topintent);
            }
        });

        Button Socbtn = (Button)findViewById(R.id.buttonSoc);//ボタンの動作
        Socbtn.setOnClickListener(new View.OnClickListener() {//clickされたら
            public void onClick(View v) {
                Intent Socintent = new Intent(getApplicationContext(), MainActivity.class);
                Socintent.putExtra("push_category", "y");
                //Log.d("soc", "intent=" + Socintent.getStringExtra("push_category"));
                startActivity(Socintent);
            }
        });

        Button Worbtn = (Button)findViewById(R.id.buttonWor);//ボタンの動作
        Worbtn.setOnClickListener(new View.OnClickListener() {//clickされたら
            public void onClick(View v) {
                Intent Worintent = new Intent(getApplicationContext(), MainActivity.class);
                Worintent.putExtra("push_category", "w");
                startActivity(Worintent);
            }
        });

        Button Busbtn = (Button)findViewById(R.id.buttonBus);//戻るボタンの動作
        Busbtn.setOnClickListener(new View.OnClickListener() {//clickされたら
            public void onClick(View v) {
                Intent Busintent = new Intent(getApplicationContext(), MainActivity.class);
                Busintent.putExtra("push_category", "b");
                startActivity(Busintent);
            }
        });

        Button Techbtn = (Button)findViewById(R.id.buttonTech);//戻るボタンの動作
        Techbtn.setOnClickListener(new View.OnClickListener() {//clickされたら
            public void onClick(View v) {
                Intent Techintent = new Intent(getApplicationContext(), MainActivity.class);
                Techintent.putExtra("push_category", "t");
                startActivity(Techintent);
            }
        });

        Button Entbtn = (Button)findViewById(R.id.buttonEnt);//戻るボタンの動作
        Entbtn.setOnClickListener(new View.OnClickListener() {//clickされたら
            public void onClick(View v) {
                Intent Entintent = new Intent(getApplicationContext(), MainActivity.class);
                Entintent.putExtra("push_category", "e");
                startActivity(Entintent);
            }
        });

        Button Spobtn = (Button)findViewById(R.id.buttonSpo);//戻るボタンの動作
        Spobtn.setOnClickListener(new View.OnClickListener() {//clickされたら
            public void onClick(View v) {
                Intent Spointent = new Intent(getApplicationContext(), MainActivity.class);
                Spointent.putExtra("push_category", "s");
                startActivity(Spointent);
            }
        });

        Button Sncbtn = (Button)findViewById(R.id.buttonSnc);//戻るボタンの動作
        Sncbtn.setOnClickListener(new View.OnClickListener() {//clickされたら
            public void onClick(View v) {
                Intent Sncintent = new Intent(getApplicationContext(), MainActivity.class);
                Sncintent.putExtra("push_category", "snc");
                startActivity(Sncintent);
            }
        });

        Button Polbtn = (Button)findViewById(R.id.buttonPol);//戻るボタンの動作
        Polbtn.setOnClickListener(new View.OnClickListener() {//clickされたら
            public void onClick(View v) {
                Intent Polintent = new Intent(getApplicationContext(), MainActivity.class);
                Polintent.putExtra("push_category", "p");
                startActivity(Polintent);
            }
        });
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        // デフォルトではアイテムを追加した順番通りに表示する
        menu.add(0, MENU_ITEM_RELOAD, 0, "更新");
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 更新した時の挙動
            case MENU_ITEM_RELOAD:
                // アダプタを初期化し、タスクを起動する
                mItems = new ArrayList();
                mAdapter = new RssListAdapter(this,0 ,mItems);
                // タスクはその都度生成する
                ListView _list = (ListView) findViewById(android.R.id.list);
                RssParserTask task = new RssParserTask(this, mAdapter, _list);
                //task.execute(RSS_FEED_URL);
                if(intent == null){

                }else {
                    category = intent.getStringExtra("push_category");
                }
                if(category.length()==0){
                    task.execute(RSS_FEED_URL);
                }else {
                    task.execute(RSS_FEED_URL + "&topic=" + category);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
