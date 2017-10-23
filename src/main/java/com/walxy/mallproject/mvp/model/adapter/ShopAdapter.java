package com.walxy.mallproject.mvp.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.walxy.mallproject.R;
import com.walxy.mallproject.mvp.model.bean.Databean;

import java.util.List;



public class ShopAdapter extends RecyclerView.Adapter{
    private List<Databean.GoodsListBean> list;
    private Context context;
    public interface OnItemAdd{
        void setOnItemremove(int position);
    }
    private OnItemAdd onItemAdd;
    public void setOnItemAdd(OnItemAdd onItemAdd){
        this.onItemAdd = onItemAdd;
    }

    public ShopAdapter(List<Databean.GoodsListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.shop_itemlist,null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getHd_thumb_url()).into(viewHolder.img);
        viewHolder.title.setText(list.get(position).getGoods_name());
        viewHolder.price.setText(list.get(position).getMarket_price()+"");
        viewHolder.jiaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemAdd.setOnItemremove(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 :list.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView title;
        private TextView price;
        private ImageButton jiaru;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img);
            title = (TextView)itemView.findViewById(R.id.title);
            price = (TextView)itemView.findViewById(R.id.price);
            jiaru = (ImageButton)itemView.findViewById(R.id.jiaru);
        }
    }
}
