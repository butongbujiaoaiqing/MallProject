package com.walxy.mallproject.mvp.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.walxy.mallproject.R;
import com.walxy.mallproject.mvp.model.bean.Databean;

import java.util.List;

/**
 * 作者：王兵洋  2017/9/17 19:15
 * 类的用途：
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context mContext;
    private List<Databean.GoodsListBean> mGoodsListBeen;

    public HomeAdapter(Context context, List<Databean.GoodsListBean> goodsListBeen) {
        mContext = context;
        mGoodsListBeen = goodsListBeen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_data, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_data.setText(mGoodsListBeen.get(position).getGoods_name());
        Glide.with(mContext).load(mGoodsListBeen.get(position).getThumb_url()).into(holder.img_data);
    }

    @Override
    public int getItemCount() {
        return mGoodsListBeen != null ? mGoodsListBeen.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_data;
        ImageView img_data;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_data = itemView.findViewById(R.id.tv_data);
            img_data = itemView.findViewById(R.id.img_data);
        }
    }

}
