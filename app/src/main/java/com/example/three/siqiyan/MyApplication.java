package com.example.three.siqiyan;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import cn.bmob.v3.Bmob;

/**
 * Created by Three on 2016/6/17.
 */
public class MyApplication extends Application {
    /**
     * SDK初始化也可以放到Application中
     */
 public static String APPID = "b4828952703f0030462a720289165403";
    private static MyApplication application;
    private static int mainTid;//当前主线程的id
    private static Handler handler;//主线程的handler
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,APPID);
        mainTid = android.os.Process.myTid();
        handler = new Handler();
    }
    public static Context getApplication(){
        return application;
    }
    public static int getMainTid() {
        return mainTid;
    }
    public static Handler getHandler() {
        return handler;
    }
}
