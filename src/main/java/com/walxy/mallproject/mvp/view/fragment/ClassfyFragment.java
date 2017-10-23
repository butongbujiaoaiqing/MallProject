package com.walxy.mallproject.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.walxy.mallproject.R;
import com.walxy.mallproject.base.BaseFragment;
import com.walxy.mallproject.mvp.model.adapter.ClassFirstRecylerAdapter;
import com.walxy.mallproject.mvp.model.adapter.ClassGridAdapter;
import com.walxy.mallproject.mvp.model.bean.Bean;
import com.walxy.mallproject.mvp.model.bean.ClassBeanFirst;
import com.walxy.mallproject.mvp.model.bean.ClassBeanSecond;
import com.walxy.mallproject.mvp.view.AppConstans;
import com.walxy.mallproject.utils.CustomGridView;
import com.walxy.mallproject.utils.ItemDecration;
import com.walxy.mallproject.utils.OkHttpUtils;
import com.walxy.mallproject.utils.OnItemonclicklinearLeft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class ClassfyFragment extends BaseFragment {
    private List<ClassBeanFirst.DatasBean.ClassListBean> list_recyler = new ArrayList<>();
    List<Bean> class_Title = new ArrayList<>();
    private Bean bean;
    private Map<String, List<Bean>> map = new HashMap<>();
    private List<Bean> beanList;
    private ClassFirstRecylerAdapter firstRecyleradapter;
    private ExpandedAdapter expandedAdapter;
    private CustomGridView customGridView;
    private ClassGridAdapter gridAdapter;
    @BindView(R.id.firstrecyller)
    RecyclerView recyller_left;
    @BindView(R.id.expanded_menu)
    ExpandableListView expandableListView;
    Unbinder unbinder;

    @Override
    public int getLayOutId() {
        return R.layout.classify_fragment;
    }

    @Override
    protected void initView() {
        initAdapter();
        initData();
        LinearLayoutManager linear_left = new LinearLayoutManager(getActivity());
        linear_left.setOrientation(OrientationHelper.VERTICAL);
        recyller_left.setLayoutManager(linear_left);
        recyller_left.addItemDecoration(new ItemDecration(getActivity()));
        LayoutInflater.from(getActivity()).inflate(R.layout.title_bar, null);
    }
    //recyclerView的网络请求
    @Override
    protected void initData() {
        //通过类名直接调用静态方法获取单例对象再调用getBeanOfOK()方法传入参数通过接口回调获取数据
        OkHttpUtils.getInstance().getBeanOfOk(getActivity(), AppConstans.CLASSFY_BASE.BASE_CLASSFY_URL, ClassBeanFirst.class,
                new OkHttpUtils.CallBack<ClassBeanFirst>() {
                    @Override
                    public void getData(ClassBeanFirst testBean) {
                        if (testBean != null) {
                            //如果不为空用本地list接收
                            list_recyler.addAll(testBean.getDatas().getClass_list());
                            //数据一旦回调成功初始化数据源和适配器
                            initAdapter();
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //设置适配器
    public void initAdapter() {
        firstRecyleradapter = new ClassFirstRecylerAdapter(list_recyler, getActivity());
        recyller_left.setAdapter(firstRecyleradapter);
        expanadaData("1");//给expanadableListView默认id=1请求
        //点击
        firstRecyleradapter.setOnItemonclicklinear(new OnItemonclicklinearLeft() {
            @Override
            public void onClickListenerLeft(View v, int position) {
                ClassBeanFirst.DatasBean.ClassListBean recyler_bean = list_recyler.get(position);
                String id = recyler_bean.getGc_id();
                expanadaData(id);//传过去id
            }
        });
    }
    /*
    * 二级列表的适配器
    */
    public void expanadaData(String id) {
        class_Title.clear();
        OkHttpUtils.getInstance().getBeanOfOk(getActivity(), AppConstans.CLASSFY_BASE.BASE_CLASSFY_EXPAND_URL + id, ClassBeanSecond.class,
                new OkHttpUtils.CallBack<ClassBeanSecond>() {
                    @Override
                    public void getData(ClassBeanSecond testBean) {
                        if (testBean != null) {
                            //如果不为空用本地list接收
                            List<ClassBeanSecond.DatasBean.ClassListBean> list = new ArrayList<>();
                            list.addAll(testBean.getDatas().getClass_list());
                            for (ClassBeanSecond.DatasBean.ClassListBean classListBean : list) {
                                bean = new Bean();
                                bean.name = classListBean.getGc_name();
                                bean.id = classListBean.getGc_id();
                                class_Title.add(bean);
                            }
                            //数据一旦回调成功初始化数据源和适配器
                            expandedAdapter = new ExpandedAdapter();
                            gridData();
                            expandableListView.setAdapter(expandedAdapter);
                            for (int i = 0; i < expandedAdapter.getGroupCount(); i++) {
                                expandableListView.expandGroup(i);
                            }
                            expandedAdapter.notifyDataSetChanged();
                        }
                        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                            @Override
                            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                                return true;
                            }
                        });
                    }
                });
    }

    /*
    GridView的网络请求
    */
    private void gridData() {
        for (final Bean b : class_Title) {
            final List<Bean> class_ChildTitle = new ArrayList<>();
            OkHttpUtils.getInstance().getBeanOfOk(getActivity(), AppConstans.CLASSFY_BASE.BASE_CLASSFY_EXPAND_URL + b.id, ClassBeanSecond.class, new OkHttpUtils.CallBack<ClassBeanSecond>() {
                @Override
                public void getData(ClassBeanSecond testBean) {
                    if (testBean != null) {
                        //如果不为空用本地list接收
                        List<ClassBeanSecond.DatasBean.ClassListBean> list = new ArrayList<>();
                        list.addAll(testBean.getDatas().getClass_list());

                        for (ClassBeanSecond.DatasBean.ClassListBean classListBean : list) {
                            bean = new Bean();
                            bean.name = classListBean.getGc_name();
                            bean.id = classListBean.getGc_id();
                            class_ChildTitle.add(bean);
                        }
                        map.put(b.name, class_ChildTitle);
                    }
                }
            });

        }

    }
    /*
    * 二级列表的适配器
    */
    class ExpandedAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return class_Title.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return 1;
        }

        @Override
        public Object getGroup(int i) {
            return null;
        }

        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return 0;
        }

        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.expandable_item, null);
            TextView textView = view.findViewById(R.id.expan_name);
            textView.setText(class_Title.get(i).name);
            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.classfy_gridview, null);
            customGridView = view.findViewById(R.id.class_gridview);
            beanList = map.get(class_Title.get(i).name);
            gridAdapter = new ClassGridAdapter(beanList, getActivity());
            customGridView.setAdapter(gridAdapter);
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");

        }
    }
}
