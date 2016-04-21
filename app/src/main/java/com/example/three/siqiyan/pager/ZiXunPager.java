package com.example.three.siqiyan.pager;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.three.siqiyan.base.BaseMenuDetailPager;
import com.example.three.siqiyan.base.BasePager;
import com.example.three.siqiyan.bean.NewsInfo;
import com.example.three.siqiyan.http.HService;
import com.example.three.siqiyan.http.ZixunAPI;
import com.example.three.siqiyan.menupager.CommentMenuPager;
import com.example.three.siqiyan.menupager.HomeMenuPager;
import com.example.three.siqiyan.menupager.PushMenuPager;
import com.example.three.siqiyan.menupager.SubscribeMenuPager;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 资讯首页
 * Created by Three on 2016/4/19.
 */
public class ZiXunPager extends BasePager {
    private ArrayList<BaseMenuDetailPager> pagerList;
    private List<NewsInfo> newsInfoList = new ArrayList<>();
    private HService service;
    private ZixunAPI zixunAPI;


    public ZiXunPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("华尔街见闻");// 修改标题
        btnMenu.setVisibility(View.VISIBLE);// 隐藏菜单按钮
        setSlidingMenuEnable(true);//关闭侧边栏
        flContent.removeAllViews();//清除先前的绘图
        addMenuPager();
        zixunAPI = ZixunAPI.getAPI();
        service = zixunAPI.getService();


        TextView text = new TextView(mActivity);
        text.setText("华尔街见闻");
        text.setTextColor(Color.BLUE);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);

        // 向FrameLayout中动态添加布局
        flContent.addView(text);
        getJsonResult();
    }

    /**
     * 为pagerlist添加MenuPager
     */
    public void addMenuPager() {
        pagerList = new ArrayList<>();
        pagerList.add(new HomeMenuPager(mActivity));
        pagerList.add(new SubscribeMenuPager(mActivity));
        pagerList.add(new CommentMenuPager(mActivity));
        pagerList.add(new PushMenuPager(mActivity));
    }

    /**
     * 在资讯页面设置侧边栏pager
     */
    public void setCurrentMenuPager(int position, String title) {
        BaseMenuDetailPager menuPsger = pagerList.get(position);
        flContent.removeAllViews();//清除baserpager里面framelayout的布局（中间显示的部分）
        flContent.addView(menuPsger.mRootView);
        tvTitle.setText(title);//设置当前menupager的标题
        menuPsger.initData();//初始化数据
    }

    /**
     * 请求网络获取数据
     */
    public void getJsonResult() {
        Observable<NewsInfo> observable = service.getResult();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsInfo>() {
                    @Override
                    public void onCompleted() {
                        Log.v("=======III===", "完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("=======III===", "出错");
                    }

                    @Override
                    public void onNext(NewsInfo newsInfo) {
                        Log.v("=======III===", "ZHIXING");
                    }
                });
    }


}
