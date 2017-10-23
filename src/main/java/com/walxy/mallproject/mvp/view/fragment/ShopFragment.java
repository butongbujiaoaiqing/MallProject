package com.walxy.mallproject.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.walxy.mallproject.R;
import com.walxy.mallproject.mvp.model.bean.Databean;
import com.walxy.mallproject.mvp.model.adapter.ShopAdapter;
import com.walxy.mallproject.mvp.model.adapter.ShopAdapters;
import com.walxy.mallproject.mvp.presenter.ShopPresenter;
import com.walxy.mallproject.mvp.view.IShopActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShopFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.rr)
    RecyclerView lv;
    Unbinder unbinder;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.selectAll)
    CheckBox selectAll;
    @BindView(R.id.pricehe)
    TextView pricehe;
    @BindView(R.id.numhe)
    Button numhe;
    @BindView(R.id.linear1)
    LinearLayout linear1;
    @BindView(R.id.deleteAll)
    Button deleteAll;

    private List<Databean.GoodsListBean> list = new ArrayList<>();
    private List<Databean.GoodsListBean> lists = new ArrayList<>();
    private ShopPresenter shopPresenter;
    private ShopAdapter adapter;
    private ShopAdapters adapters;
    private HashMap<Integer, Boolean> hashMap = new HashMap<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                list.addAll(new Gson().fromJson(msg.obj.toString(), Databean.class).getGoods_list());
                adapter.notifyDataSetChanged();
            } else if (msg.what == 2) {
                if (lists.size() == 0) {
                    layout.setVisibility(View.VISIBLE);
                    linear1.setVisibility(View.GONE);
                } else {
                    layout.setVisibility(View.GONE);
                    linear1.setVisibility(View.VISIBLE);
                }
                adapters.notifyDataSetChanged();
            } else {
                lists.remove(msg.arg1);
                adapters.notifyDataSetChanged();

            }


        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_shop, container, false);
        unbinder = ButterKnife.bind(this, v);

        initView();
        loadData();


        return v;
    }

    private void initView() {
        GridLayoutManager glide = new GridLayoutManager(getActivity(), 2);
        lv.setLayoutManager(glide);
        adapter = new ShopAdapter(list, getActivity());
        adapter.setOnItemAdd(new ShopAdapter.OnItemAdd() {
            @Override
            public void setOnItemremove(int position) {
                lists.add(list.get(position));
                Message msg = Message.obtain();
                msg.what = 2;
                mHandler.sendMessage(msg);
            }
        });
        lv.setAdapter(adapter);

        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(linear);


        adapters = new ShopAdapters(lists, getActivity());
        recycler.setAdapter(adapters);
        adapters.setOnItemClickck(new ShopAdapters.OnItemCheck() {
            @Override
            public void setOnItemCk(int position) {
                getcount(position);
            }
        });
    }

    private void loadData() {

        shopPresenter = new ShopPresenter(new IShopActivity() {
            @Override
            public void getDataBean(String json) {
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = json;
                mHandler.sendMessage(msg);
            }

            @Override
            public Context context() {
                return getActivity();
            }
        });
        shopPresenter.getData();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.selectAll, R.id.pricehe, R.id.numhe, R.id.deleteAll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.selectAll:
                adapters.selectAll();
                adapters.notifyDataSetChanged();
                break;
            case R.id.pricehe:
                break;
            case R.id.numhe:
                break;
            case R.id.deleteAll:

                HashMap<Integer, Boolean> map = adapters.getmap();
                Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
                for (Map.Entry<Integer, Boolean> en : entries) {
                    if (en.getValue()) {
                        Message msg = Message.obtain();
                        msg.arg1 = en.getKey();
                        msg.what = 3;
                        mHandler.sendMessage(msg);

                    }

                }

                break;
        }
    }

    public void getcount(int position) {
        HashMap<Integer, Boolean> map = adapters.getmap();
        int i = 0;
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> en : entries) {
            if (en.getValue()) {
                i++;
            }
        }
        numhe.setText("已选中" + i);
        i = 0;
        pricehe.setText("合计：" + lists.get(position).getMarket_price()+"");
    }


}
