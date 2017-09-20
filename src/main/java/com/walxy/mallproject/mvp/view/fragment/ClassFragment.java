package com.walxy.mallproject.mvp.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.walxy.mallproject.R;
import com.walxy.mallproject.base.BaseFragment;
import com.walxy.mallproject.mvp.model.ClassAdapter;
import com.walxy.mallproject.mvp.model.ClassBean;
import com.walxy.mallproject.mvp.view.activity.GrideBean;
import com.walxy.mallproject.mvp.view.activity.RecyclerViewAdapter2;
import com.walxy.mallproject.utils.OkHttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * 作者：王兵洋  2017/9/6 09:58
 * 类的用途：
 */
public class ClassFragment extends BaseFragment {
    @BindView(R.id.rcy)
    RecyclerView mRcy;
    Unbinder unbinder;
/*    @BindView(R.id.rcy2)
    RecyclerView mRcy2;*/
    private String path = "http://169.254.148.115/mobile/index.php?act=goods_class";
    private String path2 = "http://169.254.148.115/mobile/index.php?act=goods_class&gc_id=1";

    private ClassAdapter mClassAdapter;
    private List<ClassBean.DatasBean.ClassListBean> mClassListBean = new ArrayList<>();

    private List<GrideBean.DatasBean.ClassListBean> mClassListBeen = new ArrayList<>();

    private RecyclerViewAdapter2 mRecyclerViewAdapter2;

    @Override
    public int getLayOutId() {
        return R.layout.fragment_class;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRcy.setLayoutManager(manager);
        setOkHttp();

    }

    private void setOkHttp() {
        OkHttp.getAsync(path, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
            }

            @Override
            public void requestSuccess(String result) throws Exception {

                Gson gson = new Gson();
                mClassListBean.addAll(gson.fromJson(result, ClassBean.class).getDatas().getClass_list());
                mClassAdapter = new ClassAdapter(getActivity(), mClassListBean);
                mRcy.setAdapter(mClassAdapter);
                mClassAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    protected void initData() {
   /*     LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        mRcy2.setLayoutManager(manager2);


        OkHttp.getAsync(path2, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                mClassListBeen.addAll(gson.fromJson(result, GrideBean.class).getDatas().getClass_list());
                mRecyclerViewAdapter2 = new RecyclerViewAdapter2(getActivity(), mClassListBeen);
                mRcy2.setAdapter(mRecyclerViewAdapter2);
                mRecyclerViewAdapter2.notifyDataSetChanged();
            }
        });*/


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

