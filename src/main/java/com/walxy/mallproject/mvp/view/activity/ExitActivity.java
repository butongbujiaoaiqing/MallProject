package com.walxy.mallproject.mvp.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;

import com.walxy.mallproject.R;
import com.walxy.mallproject.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ExitActivity extends BaseActivity {


    @BindView(R.id.bttttttttttttttt)
    Button mBttttttttttttttt;

    @Override
    public int getLayout() {
        return R.layout.activity_exit;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bttttttttttttttt)
    public void onViewClicked() {
        SharedPreferences zt = getSharedPreferences("ZT", MODE_PRIVATE);
        zt.edit().putBoolean("zt", false).putString("sj", null).commit();
        SharedPreferences qq = getSharedPreferences("bc", MODE_PRIVATE);
        qq.edit().putBoolean("zhuangtai", false).putString("touxiang", null).putString("yonghuming", null).commit();
        Intent intent = new Intent(ExitActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
