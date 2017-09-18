package com.walxy.mallproject.mvp.view.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.walxy.mallproject.R;

import java.util.List;

/**
 * 作者：王兵洋  2017/9/15 15:09
 * 类的用途： 分类界面的二级列表 展示
 */
public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder2> {
    private Context mContext;
    private List<GrideBean.DatasBean.ClassListBean> mClassListBeen;

    public RecyclerViewAdapter2(Context context, List<GrideBean.DatasBean.ClassListBean> classListBeen) {
        mContext = context;
        mClassListBeen = classListBeen;
    }

    @Override
    public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder2 viewHolder = new ViewHolder2(LayoutInflater.from(mContext).inflate(R.layout.class_item, null));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder2 holder, int position) {
        holder.mTextView.setText(mClassListBeen.get(position).getGc_name());
    }

    @Override
    public int getItemCount() {
        return mClassListBeen != null ? mClassListBeen.size() : 0;
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView mTextView;

        public ViewHolder2(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_re);
        }
    }

}
