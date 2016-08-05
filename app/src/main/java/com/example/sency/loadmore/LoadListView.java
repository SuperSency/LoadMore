package com.example.sency.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by sency on 2016/8/5.
 */
public class LoadListView extends ListView implements AbsListView.OnScrollListener {

    //底部布局
    View footView;

    //Item总数量
    int totalItemCount;

    //最后一个可见item下标
    int lastVisibleItem;

    //正在加载
    boolean isLoading;

    onLoadListener loadListener;

    //重写构造方法
    public LoadListView(Context context) {
        super(context);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    //添加底部布局到ListView中
    public void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //实例化加载提示布局
        footView = inflater.inflate(R.layout.foot, null);
        footView.findViewById(R.id.load).setVisibility(View.GONE);
        this.addFooterView(footView);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        //当item总数等于最后一个item的值并且停止滚动
        if (totalItemCount == lastVisibleItem && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading = true;
                footView.findViewById(R.id.load).setVisibility(View.VISIBLE);
                //加载更多
                loadListener.onLoad();
            }
        }
    }

    /**
     *类似于数组下表从0开始
     * 第一个参数为当前第一个可见item的下标值
     * 第二个参数为当前可见item的数量
     * 第三个为总共有的item数量
     */
    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
        Log.i("tag", "firstVisibleItem:" + firstVisibleItem);//0,1
        Log.i("tag", "visibleItemCount:" + visibleItemCount);//6,7
        Log.i("tag", "totalItemCount:" + totalItemCount);//8,8
        Log.i("tag", "lastVisibleItem:" + this.lastVisibleItem);//6,8
        Log.i("tag", "this.TotalItem:" + this.totalItemCount);//8,8
    }

    //通过接口回调的方式
    /**
     * 定义一个接口
     * 家在更多数据的回调接口
     */
    public interface onLoadListener{
        public void onLoad();
    }

    //获取接口
    public void setOnLoadListener(onLoadListener listener){
        this.loadListener = listener;
    }

    //加载完毕
    public void loadComplete(){
        isLoading = false;
        footView.findViewById(R.id.load).setVisibility(View.GONE);
    }
}
