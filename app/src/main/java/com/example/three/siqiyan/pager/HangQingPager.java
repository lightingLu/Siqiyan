package com.example.three.siqiyan.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.three.siqiyan.base.BasePager;


/**
 * 行情
 * Created by Three on 2016/4/19.
 */
public class HangQingPager extends BasePager {

	public HangQingPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		btnMenu.setVisibility(View.GONE);// 隐藏菜单按钮
		tvTitle.setText("行情");
		setSlidingMenuEnable(false);// 打开侧边栏

		TextView text = new TextView(mActivity);
		text.setText("行情");
		text.setTextColor(Color.BLUE);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);

		// 向FrameLayout中动态添加布局
		flContent.addView(text);
	}

}
