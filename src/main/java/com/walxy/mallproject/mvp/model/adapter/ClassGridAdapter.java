package com.walxy.mallproject.mvp.model.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.walxy.mallproject.R;
import com.walxy.mallproject.mvp.model.bean.Bean;

import java.util.List;


public class ClassGridAdapter extends BaseAdapter {
    private List<Bean> list;
    private Context context;

    public ClassGridAdapter(List<Bean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.class_grid_item, null);
            holder = new ViewHolder();
            holder.title=view.findViewById(R.id.classfy_type_grid_title);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.title.setText(list.get(i).name);
        return view;
    }

    static class ViewHolder {
        TextView title;
    }
}
