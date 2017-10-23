package com.walxy.mallproject.mvp.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.walxy.mallproject.R;
import com.walxy.mallproject.mvp.model.bean.ClassBeanFirst;
import com.walxy.mallproject.utils.OnItemonclicklinearLeft;

import java.util.ArrayList;
import java.util.List;
public class ClassFirstRecylerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<ClassBeanFirst.DatasBean.ClassListBean>list=new ArrayList<>();
    private Context context;
    private OnItemonclicklinearLeft onItemonclicklinear;

    public void setOnItemonclicklinear(OnItemonclicklinearLeft onItemonclicklinear) {
        this.onItemonclicklinear = onItemonclicklinear;

    }
    public ClassFirstRecylerAdapter(List<ClassBeanFirst.DatasBean.ClassListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    //加载布局
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        RecyclerView.ViewHolder viewHolder=null;
        view= LayoutInflater.from(context).inflate(R.layout.first_recylerview,parent,false);
        viewHolder=new MyViewHolder(view);

        return viewHolder;
    }
    //赋值
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ClassBeanFirst.DatasBean.ClassListBean bean=list.get(position);
        final MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.gc_name.setText( bean.getGc_name());
        Log.i("===", "77777ata: " + bean.getGc_name());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemonclicklinear.onClickListenerLeft(myViewHolder.itemView,position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView gc_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            gc_name=(TextView) itemView.findViewById(R.id.gc_name);
        }
    }
}
