package com.nrp.android.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        int layout = getLayoutId();
        if (layout == 0) {
            throw new IllegalStateException("Please specify root layout resource id for " + getClass().getSimpleName());
        } else {
            setContentView(layout);
            ButterKnife.bind(this);
            initContentView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void initContentView() {
        onQueryArguments(getIntent());
        onBindListener();
        onApplyData();
    }

    /***
     * 用于在初始化View之前做一些事
     */
    protected void init() {
        mContext = this;
    }

    protected abstract int getLayoutId();

    protected abstract int getContentId();

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

    /**
     * 取得传递的参数
     */
    protected void onQueryArguments(Intent intent) {

    }

    protected <T extends BaseFragment> T replaceFragment(Class<T> cls, String tag) {
        BaseFragment fragment = null;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BaseFragment page = (BaseFragment) fm.findFragmentByTag(tag);
        if (page != null) {
            fragment = page;
        } else {
            try {
                fragment = cls.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fragment == null) {
                return null;
            }
        }
        ft.replace(getContentId(), fragment, tag);
        // ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
        return (T) fragment;
    }

    protected void startActivityWithoutExtras(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void startActivityWithExtras(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);

    }
}
