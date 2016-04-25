package com.example.three.siqiyan.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.three.siqiyan.R;
import com.example.three.siqiyan.base.BasePager;

/**
 * 我
 * Created by Three on 2016/4/19.
 */
public class MePager extends BasePager{
	GridView grideView;

	public MePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {

		tvTitle.setText("我");
		btnMenu.setVisibility(View.GONE);// 隐藏菜单按钮
		setSlidingMenuEnable(false);// 打开侧边栏

		View view_me = View.inflate(mActivity, R.layout.me_pager, null);
		grideView = (GridView) view_me.findViewById(R.id.gride);



		// 向FrameLayout中动态添加布局
		flContent.addView(view_me);
	}
    class  GridViewAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public Object getItem(int i) {
			return null;
		}

		@Override
		public long getItemId(int i) {
			return 0;
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			return null;
		}
	}
}
