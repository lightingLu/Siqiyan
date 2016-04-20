package com.example.three.siqiyan.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.three.siqiyan.R;
import com.example.three.siqiyan.base.BasePager;
import com.example.three.siqiyan.pager.MePager;
import com.example.three.siqiyan.pager.ZiXunPager;
import com.example.three.siqiyan.pager.ZhiboPager;
import com.example.three.siqiyan.pager.HangQingPager;

import java.util.ArrayList;

/**
 * 主页内容
 *
 * @author three
 */
public class ContentFragment extends BaseFragment {

    private RadioGroup rgGroup;
    private ViewPager mViewPager;
    private ArrayList<BasePager> mPagerList;

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_content);
        return view;
    }

    @Override
    public void initData() {
        rgGroup.check(R.id.rb_home);// 默认勾选首页
        mPagerList = new ArrayList<>();
		mPagerList.add(new ZiXunPager(mActivity));
		mPagerList.add(new ZhiboPager(mActivity));
		mPagerList.add(new HangQingPager(mActivity));
		mPagerList.add(new MePager(mActivity));

        mViewPager.setAdapter(new ContentAdapter());
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        // mViewPager.setCurrentItem(0);// 设置当前页面
                        mViewPager.setCurrentItem(0, false);// 去掉切换页面的动画
                        break;
                    case R.id.rb_news:
                        mViewPager.setCurrentItem(1, false);// 设置当前页面
                        break;
                    case R.id.rb_smart:
                        mViewPager.setCurrentItem(2, false);// 设置当前页面
                        break;
                    case R.id.rb_gov:
                        mViewPager.setCurrentItem(3, false);// 设置当前页面
                        break;

                    default:
                        break;
                }
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPagerList.get(position).initData();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mPagerList.get(0).initData();
    }

    /**
     * 获取咨讯这个pager
     * @return
     */
    public ZiXunPager getZiXunPager() {
        ZiXunPager pager = (ZiXunPager) mPagerList.get(0);
        return pager;
    }


    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mPagerList.get(position);
            container.addView(pager.mRootView);
            // pager.initData();// 初始化数据.... 不要放在此处初始化数据, 否则会预加载下一个页面
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }


    }

    /**
     * 设置当前菜单页面
     */
    public void setCurrentPage() {
        mViewPager.setCurrentItem(0, false);
    }
}
