package com.walxy.mallproject.mvp.model;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class ResqustGet {
    private OkHttpClient client;
    private static volatile ResqustGet instance;
    private ResqustGet(){
        client = new OkHttpClient();
    }
    public static ResqustGet getInstance(){
        if(instance==null){
            synchronized (ResqustGet.class){
                if(instance==null){
                    instance = new ResqustGet();
                }
            }
        }
        return instance;
    }

    public Call getCall(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        return call;
    }

}
