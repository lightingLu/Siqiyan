package com.example.three.siqiyan.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.three.siqiyan.base.BasePager;

/**
 * 我
 * Created by Three on 2016/4/19.
 */
public class MePager extends BasePager{

	public MePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {

		tvTitle.setText("我");
		btnMenu.setVisibility(View.GONE);// 隐藏菜单按钮
		setSlidingMenuEnable(false);// 打开侧边栏

		TextView text = new TextView(mActivity);
		text.setText("我");
		text.setTextColor(Color.BLUE);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);

		// 向FrameLayout中动态添加布局
		flContent.addView(text);
	}

}
