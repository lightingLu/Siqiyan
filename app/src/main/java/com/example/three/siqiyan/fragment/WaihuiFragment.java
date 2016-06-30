package com.example.three.siqiyan.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Three on 2016/6/30.
 */
public class WaihuiFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView text = new TextView(getContext());
        text.setText("外汇");
        text.setTextColor(Color.BLUE);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);
        return text;
    }
}
