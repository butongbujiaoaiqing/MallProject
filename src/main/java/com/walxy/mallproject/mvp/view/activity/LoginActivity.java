package com.walxy.mallproject.mvp.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.walxy.mallproject.R;
import com.walxy.mallproject.base.BaseActivity;
import com.walxy.mallproject.utils.Utils_Toast;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.x_login)
    ImageView mXLogin;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.sjhzc)
    TextView mSjhzc;
    @BindView(R.id.wjmm)
    TextView mWjmm;
    @BindView(R.id.wx)
    ImageView mWx;
    @BindView(R.id.qq)
    ImageView mQq;
    @BindView(R.id.jdcl)
    TextView mJdcl;
    @BindView(R.id.cb_login)
    CheckBox mCbLogin;
    private MySQLiteOpenHelper helper;
    private SQLiteDatabase mDb;
    private String mName;
    private String mPwd;
    private String img = "http://img2.imgtn.bdimg.com/it/u=663668703,1536465683&fm=27&gp=0.jpg";

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        helper = new MySQLiteOpenHelper(this);
    }

    @Override
    public void initData() {
        /**
         * 显示隐藏密码
         */
        mCbLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //如果选中，显示密码
                    mEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    mEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    //验证登录
    public boolean mlogin(String mEtphone, String mEtpassword) {
        mDb = helper.getReadableDatabase();
        String sql = "select * from logins where phone=? and pwd=?";
        Cursor cursor = mDb.rawQuery(sql, new String[]{mEtphone, mEtpassword});
        if (cursor.moveToFirst() == true) {
            cursor.close();
            return true;
        }
        return false;
    }

    @OnClick({R.id.x_login, R.id.login_btn, R.id.sjhzc, R.id.wjmm, R.id.wx, R.id.qq, R.id.jdcl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.x_login:
                finish();
                break;
            case R.id.login_btn:
                mName = mEtName.getText().toString();
                mPwd = mEtPwd.getText().toString();
                if (mlogin(mName, mPwd)) {
                    SharedPreferences bc = getSharedPreferences("bc", MODE_PRIVATE);
                    SharedPreferences.Editor edit = bc.edit();
                    edit.putString("yonghuming", mName);
                    edit.putString("touxiang", img);
                    edit.putBoolean("zhuangtai", true);
                    edit.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast toast = Toast.makeText(LoginActivity.this, "请输入正确的账号密码", Toast.LENGTH_LONG);
                    Utils_Toast.showMyToast(toast, 1000);
                }
                break;
            case R.id.sjhzc:
                startActivity(new Intent(LoginActivity.this, ZhuCeActivity.class));
                break;
            case R.id.wjmm:
                break;
            case R.id.wx:
                break;
            case R.id.qq:
                break;
            case R.id.jdcl:
                break;
        }
    }
}
