package com.walxy.mallproject;

import android.app.Application;

import com.mob.MobSDK;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * 作者：王兵洋  2017/9/8 14:54
 * 类的用途：
 */
public class APPlication extends Application {

    public static APPlication instance;

    public APPlication() {

    }

    protected String a() {
        return null;
    }

    protected String b() {
        return null;
    }


    public void onCreate() {
        super.onCreate();

        instance = this;

        MobSDK.init(this, this.a(), this.b());
        PlatformConfig.setQQZone("1106327383", "pwnA9UWvtdU9WfN5");
        //初始化SDK防止发生意外
        UMShareAPI.get(this);
    }
}
