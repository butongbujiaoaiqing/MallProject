package com.walxy.mallproject.mvp.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.walxy.mallproject.R;
import com.walxy.mallproject.base.BaseFragment;
import com.walxy.mallproject.mvp.view.activity.LoginActivity;
import com.walxy.mallproject.mvp.view.activity.SettingActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.content.Context.MODE_PRIVATE;

/**
 * 作者：王兵洋  2017/9/6 10:07
 * 类的用途：
 */
public class UserFragment extends BaseFragment {
    @BindView(R.id.login_001)
    ImageView mLogin001;
    @BindView(R.id.login_002)
    TextView mLogin002;
    @BindView(R.id.message_login)
    ImageView mMessageLogin;
    @BindView(R.id.setting_login)
    ImageView mSettingLogin;
    @BindView(R.id.linearLayout)
    RelativeLayout mLinearLayout;
    @BindView(R.id.dfk)
    TextView mDfk;
    @BindView(R.id.dsh)
    TextView mDsh;
    @BindView(R.id.dpj)
    TextView mDpj;
    @BindView(R.id.thsh)
    TextView mThsh;
    @BindView(R.id.wddd)
    TextView mWddd;
    @BindView(R.id.jd_s)
    TextView mJdS;
    @BindView(R.id.yhq_s)
    TextView mYhqS;
    @BindView(R.id.bt_s)
    TextView mBtS;
    @BindView(R.id.jdek_s)
    TextView mJdekS;
    @BindView(R.id.wdqb_s)
    ImageView mWdqbS;
    @BindView(R.id.js_x)
    TextView mJsX;
    @BindView(R.id.yhq_x)
    TextView mYhqX;
    @BindView(R.id.bt_x)
    TextView mBtX;
    @BindView(R.id.jdek_x)
    TextView mJdekX;
    @BindView(R.id.wdqb_x)
    TextView mWdqbX;
    @BindView(R.id.spgz)
    TextView mSpgz;
    @BindView(R.id.dpgz)
    TextView mDpgz;
    @BindView(R.id.nrgz)
    TextView mNrgz;
    @BindView(R.id.llls)
    TextView mLlls;
    @BindView(R.id.wdhd)
    TextView mWdhd;
    @BindView(R.id.sq)
    TextView mSq;
    @BindView(R.id.khfw)
    TextView mKhfw;
    Unbinder unbinder;
    @BindView(R.id.userImg)
    ImageView mUserImg;
    @BindView(R.id.login_007)
    TextView mLogin007;
    private boolean isBoolean;
    private SharedPreferences mSharedPreferences;
    private boolean mBoolean;

    @Override
    public int getLayOutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {


        /**
         * 记住账号信息 实现自动登录
         */
        SharedPreferences zt = getActivity().getSharedPreferences("ZT", MODE_PRIVATE);
        isBoolean = zt.getBoolean("zt", false);
        mSharedPreferences = getActivity().getSharedPreferences("bc", MODE_PRIVATE);
        mBoolean = mSharedPreferences.getBoolean("zhuangtai", false);
        if (mBoolean == true) {
            String yonghuming = mSharedPreferences.getString("yonghuming", null);
            String touxiang = mSharedPreferences.getString("touxiang", null);
            Glide.with(getActivity()).load(touxiang)
                    .bitmapTransform(new CropCircleTransformation(getActivity()))
                    .into(mUserImg);
            mUserImg.setVisibility(View.VISIBLE);
            if (mLogin007.length() < 10) {
            } else {
                mLogin001.setVisibility(View.GONE);
            }
            mLogin001.setVisibility(View.GONE);
            mLogin007.setText(yonghuming);
            if (mLogin007.length() > 10) {
                mLogin002.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void initData() {

    }

    protected void iii() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.login_001, R.id.login_002, R.id.message_login, R.id.setting_login, R.id.linearLayout, R.id.dfk, R.id.dsh, R.id.dpj, R.id.thsh, R.id.wddd, R.id.butong, R.id.jd_s, R.id.yhq_s, R.id.bt_s, R.id.jdek_s, R.id.wdqb_s, R.id.js_x, R.id.yhq_x, R.id.bt_x, R.id.jdek_x, R.id.wdqb_x, R.id.spgz, R.id.dpgz, R.id.nrgz, R.id.llls, R.id.wdhd, R.id.sq, R.id.khfw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_001:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.login_002:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.message_login:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.setting_login:
                break;
            case R.id.linearLayout:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.dfk:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.dsh:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.dpj:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.thsh:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.wddd:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.jd_s:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.yhq_s:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.bt_s:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.jdek_s:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.wdqb_s:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.js_x:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.yhq_x:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.bt_x:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.jdek_x:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.wdqb_x:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.spgz:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.dpgz:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.nrgz:
                if (mLogin007.length() < 10) {

                    iii();
                }
                break;
            case R.id.llls:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.wdhd:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.sq:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
            case R.id.khfw:
                if (mLogin007.length() < 10) {
                    iii();
                }
                break;
        }
    }
}