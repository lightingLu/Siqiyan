package com.example.three.siqiyan;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Three on 2016/6/17.
 */
public class MyApplication extends Application {
    /**
     * SDK初始化也可以放到Application中
     */
 public static String APPID = "b4828952703f0030462a720289165403";

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,APPID);
    }
}
