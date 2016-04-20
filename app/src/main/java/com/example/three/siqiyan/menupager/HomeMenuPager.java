package com.example.three.siqiyan.menupager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.three.siqiyan.base.BaseMenuDetailPager;

/**
 * Created by Three on 2016/4/20.
 */
public class HomeMenuPager extends BaseMenuDetailPager {
    public HomeMenuPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initViews() {
        TextView text = new TextView(mactivity);
        text.setText("首页");
        text.setTextColor(Color.BLUE);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);
        return text;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
