package com.example.three.siqiyan.utils;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.example.three.siqiyan.MyApplication;

/**
 * Created by Three on 2016/6/28.
 */
public class Uiutils {
    /**
     * 获取上下文
     *
     * @return
     */
    public static Context getContext() {
        return MyApplication.getApplication();
    }
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }
    /**
     * 把runnable方法提价到主线程运行
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        // 判断当前线程是否在主线程运行
        if (android.os.Process.myTid() == MyApplication.getMainTid()) {
            runnable.run();
        } else {
            // 获取handler
            MyApplication.getHandler().post(runnable);
        }
    }

}
