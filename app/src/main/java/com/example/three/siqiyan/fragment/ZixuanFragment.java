package com.example.three.siqiyan.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.three.siqiyan.R;

/**
 * Created by Three on 2016/6/30.
 */
public class ZixuanFragment extends HangqingBaseFragment {

    @Override
    public View initViews() {
        View view  = View.inflate(getContext(), R.layout.h_zixuan,null);
        if (info!=null){
            Log.v("hangqing", "shangpinbukong");
        }
        return view;
    }
}
