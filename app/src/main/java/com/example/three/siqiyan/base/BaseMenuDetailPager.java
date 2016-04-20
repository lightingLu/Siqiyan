package com.example.three.siqiyan.base;

import android.app.Activity;
import android.view.View;


/**
 * Created by Three on 2016/4/20.
 */
public abstract class BaseMenuDetailPager {
    public Activity mactivity;
    public View mRootView;

    public BaseMenuDetailPager(Activity activity) {
        this.mactivity = activity;
        mRootView = initViews();//ba子类视图给mrootview
    }

    /**
     * 初始化界面
     * @return 子view
     */
    public abstract  View initViews();

    /**
     *  初始化数据
     */
    public void initData(){

    }
}
