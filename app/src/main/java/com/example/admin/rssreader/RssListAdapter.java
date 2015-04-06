package com.example.admin.rssreader;

/**
 * Created by admin on 2014/11/05.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
//文章の表示
public class RssListAdapter extends ArrayAdapter<Item> {
    private LayoutInflater mInflater;
    private TextView mTitle;
    private TextView mDescr;

    public RssListAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {//クラスの初期化＝コンストラクタ
        super(context, textViewResourceId, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d("App3", "Call Adapter");
    }

    // 1行ごとのビューを生成する
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
//            String title_test = "test";
//            mTitle = (TextView) view.findViewById(R.id.test_items);
//            mTitle.setText(title_test);
//            mTitle.setTextColor(Color.RED);

        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_row, null);
        }

        // 現在参照しているリストの位置からItemを取得する
        Item item = this.getItem(position);
        //Log.d("App2", "Item=" + item);
        if (item != null) {
            // Itemから必要なデータを取り出し、それぞれidのTextViewにセットする
            String title = item.getTitle().toString();
            mTitle = (TextView) view.findViewById(R.id.item_title);
            mTitle.setText(title);
            mTitle.setTextColor(Color.BLUE);

            String descr = item.getDescription().toString();
            mDescr = (TextView) view.findViewById(R.id.item_descr);
            mDescr.setText(descr);
        }

        return view;
    }
}
