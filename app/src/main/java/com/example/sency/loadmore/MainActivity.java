package com.example.sency.loadmore;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements LoadListView.onLoadListener {

    private LoadListView listView;
    private List<ItemBean> list;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initDatas();
        showList(list);
    }

    private void showList(List<ItemBean> list) {
        if (adapter == null) {
            listView = (LoadListView) findViewById(R.id.list);
            adapter = new ListAdapter(MainActivity.this, list);
            listView.setOnLoadListener(this);
            listView.setAdapter(adapter);
        } else {
            adapter.onDateChange(list);
        }
    }

    private void initDatas() {
        list = new ArrayList<>();
        ItemBean one = new ItemBean("Hello,蛋酱!!!", R.drawable.img);
        list.add(one);
        ItemBean two = new ItemBean("Hello,蛋酱!!!", R.drawable.img);
        list.add(two);
        ItemBean three = new ItemBean("Hello,蛋酱!!!", R.drawable.img);
        list.add(three);
        ItemBean four = new ItemBean("Hello,蛋酱!!!", R.drawable.img);
        list.add(four);
        ItemBean five = new ItemBean("Hello,蛋酱!!!", R.drawable.img);
        list.add(five);
        ItemBean six = new ItemBean("Hello,蛋酱!!!", R.drawable.img);
        list.add(six);
        ItemBean seven = new ItemBean("Hello,蛋酱!!!", R.drawable.img);
        list.add(seven);
    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
        //延迟两秒，正常情况下不需要，这里只是为了不要过快加载出来
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取数据
                getDate();
                //通知listview更新显示
                showList(list);
                //通知ListView加载完毕
                listView.loadComplete();
            }
        }, 2000);
    }

    public void getDate() {
        ItemBean one = new ItemBean("Hello,艺兴!!!", R.drawable.img);
        list.add(one);
        ItemBean two = new ItemBean("Hello,艺兴!!!", R.drawable.img);
        list.add(two);
        showList(list);
    }
}

class ItemBean {
    String content;
    int imgId;

    public ItemBean(String content, int imgId) {
        this.content = content;
        this.imgId = imgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
