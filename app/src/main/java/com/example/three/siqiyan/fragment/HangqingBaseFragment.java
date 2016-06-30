package com.example.three.siqiyan.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.three.siqiyan.MainActivity;
import com.example.three.siqiyan.bean.HangqingInfo;
import com.example.three.siqiyan.bean.NewsInfo;
import com.example.three.siqiyan.http.HanQingService;
import com.example.three.siqiyan.http.HangqingAPI;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Three on 2016/6/30.
 */
public abstract class HangqingBaseFragment extends Fragment {
    public MainActivity hactivity;
    public HanQingService hanQingService;
    public HangqingAPI hangqingAPI;
    public static  HangqingInfo info = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hactivity = (MainActivity) getActivity();
        hangqingAPI = HangqingAPI.getAPI();
        hanQingService = hangqingAPI.getservice();
        if (info==null) {
            getHangJsonResult();
        }
    }

    private void getHangJsonResult() {
        Observable<HangqingInfo> observable = hanQingService.gethqResult();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HangqingInfo>() {
                    @Override
                    public void onCompleted() {
                        Log.v("hangqing", "完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("hangqing", "网络错误");
                    }

                    @Override
                    public void onNext(HangqingInfo hangqingInfo) {
                        Log.v("hangqing", "下载成功");
                        info = hangqingInfo;
                    }
                });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initViews();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    // 子类必须实现初始化布局的方法
    public abstract View initViews();

    // 初始化数据, 可以不实现
    public void initData() {

    }
}
