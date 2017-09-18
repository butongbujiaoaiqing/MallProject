package com.walxy.mallproject.mvp.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.library.zxing.activity.QRCodeScanActivity;
import com.walxy.mallproject.R;
import com.walxy.mallproject.mvp.model.Databean;
import com.walxy.mallproject.mvp.model.HomeAdapter;
import com.walxy.mallproject.utils.GlideImageLoader;
import com.walxy.mallproject.utils.OkHttp;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * 作者：王兵洋  2017/9/6 09:58
 * 类的用途：
 */
public class HomeFragment extends QRCodeScanActivity {

    @BindView(R.id.sys_home)
    TextView mSysHome;
    @BindView(R.id.et_ssk)
    EditText mEtSsk;
    @BindView(R.id.message_home)
    TextView mMessageHome;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.rcy)
    RecyclerView mRcy;

    private List<String> list = new ArrayList<>();
    private String[] arr = new String[]{"http://img5.imgtn.bdimg.com/it/u=4267222417,1017407570&fm=200&gp=0.jpg ",
            "http://img4.imgtn.bdimg.com/it/u=1590736614,1368131788&fm=27&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=228206913,3268878643&fm=27&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=1766390445,1488273756&fm=27&gp=0.jpgd"};
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            for (int i = 0; i < arr.length; i++) {
                list.add(arr[i]);
            }
            mBanner.setImages(list)
                    .start();
        }
    };
    private View view;
    private Unbinder unbinder;
    private String path = "http://apiv3.yangkeduo.com/v5/newlist?page";
    int page =1;
    private List<Databean.GoodsListBean> mGoodsListBeen = new ArrayList<>();
    private HomeAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBanner.setImageLoader(new GlideImageLoader(getActivity()))
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setIndicatorGravity(BannerConfig.CENTER);
        Message msg = Message.obtain();
        msg.what = 1;
        handler.sendMessage(msg);
        /**
         * RecyclerView获取网络数据
         */

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRcy.setLayoutManager(gridLayoutManager);

        OkHttp.getAsync(path + page, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), "数据请求失败了2333333", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                mGoodsListBeen.addAll(gson.fromJson(result, Databean.class).getGoods_list());
                mAdapter = new HomeAdapter(getActivity(), mGoodsListBeen);
                mRcy.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });


    }


    @OnClick({R.id.sys_home, R.id.et_ssk, R.id.message_home, R.id.banner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sys_home:
                startScanQRCode();
                break;
            case R.id.et_ssk:
                break;
            case R.id.message_home:
                break;
            case R.id.banner:
                break;
        }
    }
}