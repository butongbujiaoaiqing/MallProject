package com.walxy.mallproject.mvp.presenter;

import android.content.Context;

import com.walxy.mallproject.mvp.view.IView;


public abstract class BasePresenter<T extends IView> {
    protected T iView;


    public BasePresenter(T iView) {
        this.iView = iView;
        initModel();
    }

    protected abstract void initModel();

    Context context() {
        if (iView != null && iView.context() != null){
            return iView.context();
        }
        return iView.context();
    }
}
