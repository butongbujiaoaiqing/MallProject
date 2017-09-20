package com.walxy.mallproject.mvp.view.activity;

import android.graphics.Color;

import com.gyf.barlibrary.ImmersionBar;
import com.hjm.bottomtabbar.BottomTabBar;
import com.walxy.mallproject.R;
import com.walxy.mallproject.base.BaseActivity;
import com.walxy.mallproject.mvp.view.fragment.ClassFragment;
import com.walxy.mallproject.mvp.view.fragment.FindFragment;
import com.walxy.mallproject.mvp.view.fragment.HomeFragment;
import com.walxy.mallproject.mvp.view.fragment.ShopFragment;
import com.walxy.mallproject.mvp.view.fragment.UserFragment;

public class MainActivity extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        //设置沉浸式
        ImmersionBar.with(this).init();
        ImmersionBar.with(this).transparentStatusBar().fullScreen(true).destroy();
        /*初始化控件*/
        BottomTabBar mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);
        /*使用BottomTabBar设置底部导航栏*/
        mBottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50, 50)
                .setFontSize(12)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("首页", R.drawable.aby, R.drawable.abx, HomeFragment.class)
                .addTabItem("分类", R.drawable.abu, R.drawable.abt, ClassFragment.class)
                .addTabItem("发现", R.drawable.abw, R.drawable.abv, FindFragment.class)
                .addTabItem("购物车", R.drawable.abs, R.drawable.abr, ShopFragment.class)
                .addTabItem("我的", R.drawable.ac0, R.drawable.abz, UserFragment.class)
                .isShowDivider(true);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}

