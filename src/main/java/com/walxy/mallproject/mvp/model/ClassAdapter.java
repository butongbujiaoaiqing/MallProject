package com.walxy.mallproject.mvp.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.walxy.mallproject.R;

import java.util.List;

/**
 * 作者：王兵洋  2017/9/14 14:05
 * 类的用途：    分类界面的以及列表展示。。。。。。
 */
public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {
    private Context mContext;
    private List<ClassBean.DatasBean.ClassListBean> mClassListBean;

    public ClassAdapter(Context context, List<ClassBean.DatasBean.ClassListBean> classListBean) {
        mContext = context;
        mClassListBean = classListBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_class, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.class_tv.setText(mClassListBean.get(position).getGc_name());
    }

    @Override
    public int getItemCount() {
        return mClassListBean != null ? mClassListBean.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView class_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            class_tv = itemView.findViewById(R.id.class_tv);
        }
    }

}
