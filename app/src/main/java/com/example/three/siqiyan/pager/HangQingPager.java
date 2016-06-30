package com.example.three.siqiyan.pager;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.three.siqiyan.R;
import com.example.three.siqiyan.base.BasePager;
import com.example.three.siqiyan.fragment.GuzhiFragment;
import com.example.three.siqiyan.fragment.GuzhiqihuoFragment;
import com.example.three.siqiyan.fragment.HushenFragment;
import com.example.three.siqiyan.fragment.ShangpinFragment;
import com.example.three.siqiyan.fragment.WaihuiFragment;
import com.example.three.siqiyan.fragment.ZhaiquanFragment;
import com.example.three.siqiyan.fragment.ZixuanFragment;

import butterknife.Bind;


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
        flContent.removeAllViews();
        View hangqing = View.inflate(mActivity, R.layout.pager_hangqing, null);
//        Toolbar toolBar= (Toolbar) hangqing.findViewById(R.id.toolBar);
        ViewPager viewPager  = (ViewPager) hangqing.findViewById(R.id.viewPager);
        TabLayout tabs = (TabLayout) hangqing.findViewById(android.R.id.tabs);
        // 向FrameLayout中动态添加布局

        flContent.addView(hangqing);
        viewPager.setAdapter(new FragmentPagerAdapter(mActivity.getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 7;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "自选";
                    case 1:
                        return "沪深";
                    case 2:
                        return "外汇";
                    case 3:
                        return "商品";
                    case 4:
                        return "股指";
                    case 5:
                        return "债券";
                    case 6:
                        return "股指期货";
                    default:
                        return "指数";
                }
            }



            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new ZixuanFragment();
                    case 1:
                        return new HushenFragment();
                    case 2:
                        return new WaihuiFragment();
                    case 3:
                        return new ShangpinFragment();
                    case 4:
                        return new GuzhiFragment();
                    case 5:
                        return new ZhaiquanFragment() ;
                    case 6:
                        return new GuzhiqihuoFragment() ;
                    default:
                        return new ShangpinFragment();
                }
            }
        });
        viewPager.setOffscreenPageLimit(0);
        viewPager.setCurrentItem(0);
        tabs.setupWithViewPager(viewPager);
    }

}
