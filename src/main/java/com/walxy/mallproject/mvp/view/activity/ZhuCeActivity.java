package com.walxy.mallproject.mvp.view.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static android.widget.Toast.makeText;

public class ZhuCeActivity extends BaseActivity {

    @BindView(R.id.z_zc)
    ImageView mZZc;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.zc_name)
    EditText mZcName;
    @BindView(R.id.hqyzm)
    TextView mHqyzm;
    @BindView(R.id.zc_yzm)
    EditText mZcYzm;
    @BindView(R.id.zc_pwd)
    EditText mZcPwd;
    @BindView(R.id.zc_btn)
    Button mZcBtn;
    public EventHandler eh; //事件接收器
    @BindView(R.id.cb_login)
    CheckBox mCbLogin;
    private TimeCount mTimeCount;//计时器
    private SQLiteDatabase mDatabase;
    private ContentValues mValues;
    private MySQLiteOpenHelper helper;

    @Override
    public int getLayout() {
        return R.layout.activity_zhu_ce;
    }

    @Override
    public void initView() {
        mTimeCount = new TimeCount(60000, 1000);
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
                    mZcPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    mZcPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick({R.id.z_zc, R.id.hqyzm, R.id.zc_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.z_zc:
                finish();
                break;
            case R.id.hqyzm:
                /**
                 * 获取短信验证码   = =！
                 */
                eh = new EventHandler() {
                    @Override
                    public void afterEvent(int event, int result, Object data) {
                        if (result == SMSSDK.RESULT_COMPLETE) { //回调完成
                            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码成功
                            } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //获取验证码成功
                            } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) { //返回支持发送验证码的国家列表
                            }
                        } else {
                            ((Throwable) data).printStackTrace();
                        }
                    }
                };
                SMSSDK.registerEventHandler(eh); //注册短信回调
                if (!mZcName.getText().toString().trim().equals("")) {
                    if (checkTel(mZcName.getText().toString().trim())) {
                        SMSSDK.getVerificationCode("+86", mZcName.getText().toString());//获取验证码
                        mTimeCount.start();
                    } else {
                        makeText(ZhuCeActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    makeText(ZhuCeActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.zc_btn:

                if (!mZcName.getText().toString().trim().equals("")) {
                    if (checkTel(mZcName.getText().toString().trim())) {
                        if (!mZcYzm.getText().toString().trim().equals("")) {
                            SMSSDK.submitVerificationCode("+86", mZcName.getText().toString().trim(), mZcYzm.getText().toString().trim());//提交验证
                            if (!mZcPwd.getText().toString().trim().equals("")) {
                                /**
                                 * 把注册信息存入到数据库里面
                                 */
                                mDatabase = helper.getReadableDatabase();
                                mValues = new ContentValues();
                                //存入手机号
                                mValues.put("phone", mZcName.getText().toString());
                                //存入密码
                                mValues.put("pwd", mZcPwd.getText().toString());
                                mDatabase.insert("logins", null, mValues);
                                //关闭数据库
                                mDatabase.close();
                                //注册完成 跳转
                                Intent intent = new Intent(ZhuCeActivity.this, LoginActivity.class);
                                startActivity(intent);
                                //结束获取验证码的倒计时。。。。

                                finish();
                            } else {
                                Toast toast = makeText(ZhuCeActivity.this, "密码格式有误", Toast.LENGTH_SHORT);
                                Utils_Toast.showMyToast(toast, 1000);
                            }
                        } else {
                            Toast toast = makeText(ZhuCeActivity.this, "请输入验证码", Toast.LENGTH_SHORT);
                            Utils_Toast.showMyToast(toast, 1000);
                        }
                    } else {
                        Toast toast = makeText(ZhuCeActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT);
                        Utils_Toast.showMyToast(toast, 1000);
                    }
                } else {
                    Toast toast = makeText(ZhuCeActivity.this, "请输入手机号码", Toast.LENGTH_SHORT);
                    Utils_Toast.showMyToast(toast, 1000);
                }
                break;
        }
    }

    /**
     * 8
     * 计时器
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            mHqyzm.setClickable(false);
            mHqyzm.setText(l / 1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            mHqyzm.setClickable(true);
            mHqyzm.setText("获取验证码");
        }
    }

    /**
     * 正则匹配手机号码
     *
     * @param tel
     * @return
     */
    public boolean checkTel(String tel) {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher matcher = p.matcher(tel);
        return matcher.matches();
    }

    //注销短息验证
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}