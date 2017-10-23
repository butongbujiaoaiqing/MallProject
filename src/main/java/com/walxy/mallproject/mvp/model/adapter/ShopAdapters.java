package com.walxy.mallproject.mvp.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.walxy.mallproject.R;
import com.walxy.mallproject.mvp.model.bean.Databean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ShopAdapters extends RecyclerView.Adapter {
    private List<Databean.GoodsListBean> list;
    private Context context;
    private HashMap<Integer, Boolean> map = new HashMap<>();
    private List<Integer> intlist = new ArrayList<>();

    public ShopAdapters(List<Databean.GoodsListBean> list, Context context) {
        this.list = list;
        this.context = context;
        for (int i = 0; i < 8; i++) {
            map.put(i, false);
        }
    }

    public interface OnItemCheck {
        void setOnItemCk(int position);
    }

    private OnItemCheck onItemCheck;

    public void setOnItemClickck(OnItemCheck onItemCheck) {
        this.onItemCheck = onItemCheck;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.shop_addlist, null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getHd_thumb_url()).into(viewHolder.img);
        viewHolder.title.setText(list.get(position).getGoods_name());
        viewHolder.price.setText(list.get(position).getMarket_price()+"");
        viewHolder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put(position, !map.get(position));
                if (map.get(position)) {
                    onItemCheck.setOnItemCk(position);
                }
            }
        });
        viewHolder.check.setChecked(map.get(position));
    }

    public void selectAll() {
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        boolean flag = false;
        for (Map.Entry<Integer, Boolean> en : entries) {
            if (!en.getValue()) {
                flag = true;
            }
        }
        for (Map.Entry<Integer, Boolean> en : entries) {
            en.setValue(flag);
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private CheckBox check;
        private ImageView img;
        private TextView title;
        private TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);
            check = (CheckBox) itemView.findViewById(R.id.checkbox);
            img = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }

    public HashMap<Integer, Boolean> getmap() {
        return map;
    }
}
