package com.walxy.mallproject.mvp.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.walxy.mallproject.R;
import com.walxy.mallproject.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 愿意留下来与你争吵的人才是真正爱你的人    --<-<-<@
 * <p>
 * 作者：王兵洋 on 2017/10/20 09:36
 * <p>
 * 类的作用：
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.z_setting)
    ImageView mZSetting;
    @BindView(R.id.dl_setting)
    ImageView mDlSetting;
    @BindView(R.id.dl2_setting)
    TextView mDl2Setting;
    @BindView(R.id.lsv)
    ListView mLsv;
    private boolean isBoolean;
    private SharedPreferences mSharedPreferences;
    private boolean mBoolean;
    private List<String> mStrings;

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        /**
         * 记住账号信息 实现自动登录
         */
        SharedPreferences zt = SettingActivity.this.getSharedPreferences("ZT", MODE_PRIVATE);
        isBoolean = zt.getBoolean("zt", false);
        mSharedPreferences = SettingActivity.this.getSharedPreferences("bc", MODE_PRIVATE);
        mBoolean = mSharedPreferences.getBoolean("zhuangtai", false);
        if (mBoolean == true) {
            String yonghuming = mSharedPreferences.getString("yonghuming", null);
            String touxiang = mSharedPreferences.getString("touxiang", null);
            Glide.with(this).load(touxiang)
                    .bitmapTransform(new CropCircleTransformation(this))
                    .into(mDlSetting);
            mDl2Setting.setText(yonghuming);
        }
    }

    @Override
    public void initData() {
        mStrings = new ArrayList<>();
        mStrings.add("会员俱乐部");
        mStrings.add("小白信用");
        mStrings.add("地址管理");
        mStrings.add("实名认证");
        mStrings.add("账户安全");
        mStrings.add("关联账号");
        mStrings.add("设置");
        mStrings.add("我的定制");
        mLsv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings));
        mLsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 6) {
                    startActivity(new Intent(SettingActivity.this, ExitActivity.class));
                    finish();
                }
            }
        });
    }

    private void inin() {
        startActivity(new Intent(SettingActivity.this, LoginActivity.class));
        finish();
    }

    @OnClick({R.id.z_setting, R.id.dl_setting, R.id.dl2_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.z_setting:
                finish();
                break;
            case R.id.dl_setting:
                if (mDl2Setting.length() < 10) {
                    inin();
                }
                break;
            case R.id.dl2_setting:
                if (mDl2Setting.length() < 10) {
                    inin();
                }
                break;
        }
    }
}