package com.nrp.android.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment{

    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        if (layoutId == 0) {
            throw new IllegalStateException("Please specify root layout resource id for " + getClass().getSimpleName());
        } else {
            View parentView = inflater.inflate(layoutId, null);
            ButterKnife.bind(this, parentView);
            return parentView;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        initContentView();
    }

    private void initContentView() {
        onBindListener();
        onApplyData();
    }
    protected abstract int getLayoutId();
    /**
     * 设置监听事件
     */
    protected void onBindListener() {

    }

    /**
     * 加载数据
     */
    protected void onApplyData() {


    }
}
