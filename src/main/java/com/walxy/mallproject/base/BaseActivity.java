package com.walxy.mallproject.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：王兵洋  2017/9/6 10:19
 * 类的用途：
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        initView();//初始化数据
        initData();//网络请求
    }

    public abstract int getLayout();//加载视图

    public abstract void initView();//初始化数据

    public abstract void initData();//网络请求


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();//解绑ButterKnife
    }
}