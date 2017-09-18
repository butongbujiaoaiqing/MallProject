package com.walxy.mallproject.mvp.presenter;


import com.walxy.mallproject.mvp.model.IShopModel;
import com.walxy.mallproject.mvp.model.ShopModel;
import com.walxy.mallproject.mvp.view.IShopActivity;

public class ShopPresenter extends BasePresenter<IShopActivity> {

    private ShopModel shopModel;

    public ShopPresenter(IShopActivity iView) {
        super(iView);
    }

    @Override
    protected void initModel() {
        shopModel = new ShopModel();
    }
    public void getData(){
        shopModel.setDataBean(new IShopModel() {
            @Override
            public void addDataBean(String ss) {
                iView.getDataBean(ss);
            }
        });
    }
}
