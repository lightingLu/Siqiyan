package com.example.three.siqiyan.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.three.siqiyan.base.BaseMenuDetailPager;
import com.example.three.siqiyan.base.BasePager;
import com.example.three.siqiyan.menupager.CommentMenuPager;
import com.example.three.siqiyan.menupager.HomeMenuPager;
import com.example.three.siqiyan.menupager.PushMenuPager;
import com.example.three.siqiyan.menupager.SubscribeMenuPager;

import java.util.ArrayList;


/**
 * 资讯首页
 *Created by Three on 2016/4/19.
 */
public class ZiXunPager extends BasePager {
    private ArrayList<BaseMenuDetailPager> pagerList;

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
        TextView text = new TextView(mActivity);
        text.setText("华尔街见闻");
        text.setTextColor(Color.BLUE);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);

        // 向FrameLayout中动态添加布局
        flContent.addView(text);
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
    public void setCurrentMenuPager(int position,String title) {
        BaseMenuDetailPager menuPsger = pagerList.get(position);
        flContent.removeAllViews();//清除baserpager里面framelayout的布局（中间显示的部分）
        flContent.addView(menuPsger.mRootView);
        tvTitle.setText(title);//设置当前menupager的标题
        menuPsger.initData();//初始化数据
    }
}
