package com.walxy.mallproject.mvp.model;

import android.support.annotation.NonNull;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ShopModel {
    private String shopurl = "http://apiv3.yangkeduo.com/v5/newlist?page=3";

    public ShopModel() {
    }

    public void setDataBean(@NonNull final IShopModel shopModel) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Call call = ResqustGet.getInstance().getCall(shopurl);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println("A" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        shopModel.addDataBean(response.body().string());
                    }
                });
            }
        }.start();

    }
}