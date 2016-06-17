package com.example.three.siqiyan;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.example.three.siqiyan.fragment.ContentFragment;
import com.example.three.siqiyan.fragment.LeftMenuFragment;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity {
    private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
    private static final String FRAGMENT_CONTENT = "fragment_content";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Bomb后端云服务

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.left_menu);// 设置侧边栏
        SlidingMenu slidingMenu = getSlidingMenu();// 获取侧边栏对象
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置全屏触摸
        slidingMenu.setBehindOffset(300);// 设置预留屏幕的宽度
        initFragment();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * 初始化fragment, 将fragment数据填充给布局文件
     */
    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();// 开启事务

        transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),
                FRAGMENT_LEFT_MENU);// 用fragment替换framelayout
        transaction.replace(R.id.ly_content, new ContentFragment(),
                FRAGMENT_CONTENT);
        transaction.commit();// 提交事务
    }

    /**
     * 获取contentfragment
     */
    public ContentFragment getContentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ContentFragment contentFragment = (ContentFragment) fm.findFragmentByTag(FRAGMENT_CONTENT);
        return contentFragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.three.siqiyan/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.three.siqiyan/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}


