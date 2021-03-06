package com.walxy.mallproject.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.walxy.mallproject.R;
import com.walxy.mallproject.mvp.model.adapter.DetailAdapter;
import com.walxy.mallproject.mvp.model.bean.BaseBean;
import com.walxy.mallproject.mvp.model.bean.Detailbean;
import com.walxy.mallproject.mvp.view.AppConstans;
import com.walxy.mallproject.utils.HttpUtil;
import com.walxy.mallproject.utils.OnNetListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageback;
    private ListView lv;
    private List<Detailbean.DatasBean.GoodsCommendListBean> list = new ArrayList<>();
    private List<Detailbean.DatasBean.GoodsCommendListBean> list1 = new ArrayList<>();
    private DetailAdapter adapter;
    private Banner banner;
    private List<String> images;
    private ImageView image;
    private TextView title;
    private TextView price;
    private TextView sale;
    private TextView jieshao;
    private LinearLayout by;
    private PopupWindow mpop;
    private ImageView imagepop;
    private TextView titlepop;
    private TextView pricepop;
    private TextView kucunpop;
    private String goods_image;
    private WindowManager.LayoutParams params;
    private View rootview;
    private Button addshopcart;
    private Detailbean.DatasBean.GoodsInfoBean goods_info;
    private Button add;
    private Button jian;
    private Button num;
    private int count = 1;
    private String path = "http://169.254.50.98/data";
    private Detailbean bean;
    private ImageView pictruexq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initdata();
        initView();

    }

    private void initView() {
        imageback = (ImageView) findViewById(R.id.back2);
        lv = (ListView) findViewById(R.id.detaillist);
        by = (LinearLayout) findViewById(R.id.lijiby);
        pictruexq= (ImageView) findViewById(R.id.pictruexq);
        by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showbypop();
            }
        });
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //添加头布局
        View view = getLayoutInflater().inflate(R.layout.detail_head_item, lv, false);
//        banner = view.findViewById(R.id.banner);
        image = view.findViewById(R.id.headimage);
        title = view.findViewById(R.id.head_title);
        jieshao = view.findViewById(R.id.head_jianjie);
        price = view.findViewById(R.id.head_price);
        sale = view.findViewById(R.id.head_sale);
        lv.addHeaderView(view);
    }


    private void showbypop() {
        View contentView = LayoutInflater.from(DetailActivity.this).inflate(R.layout.detail_pop, null);
        mpop = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mpop.setContentView(contentView);
        rootview = LayoutInflater.from(DetailActivity.this).inflate(R.layout.activity_detail, null);
        mpop.setFocusable(true);
        mpop.setOutsideTouchable(true);
        mpop.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        imagepop = contentView.findViewById(R.id.imagepop);
        goods_image = bean.getDatas().getGoods_image();
        String[] datas = goods_image.split("data");
        StringBuffer str = new StringBuffer();
        String s = str + path + datas[1];
        Glide.with(DetailActivity.this).load(s).into(imagepop);
        titlepop = contentView.findViewById(R.id.titlepop);
        titlepop.setText(title.getText());
        pricepop = contentView.findViewById(R.id.pricepop);
        pricepop.setText(price.getText());
        kucunpop = contentView.findViewById(R.id.kucun);
        params = this.getWindow().getAttributes();
        params.alpha = 0.6f;
        this.getWindow().setAttributes(params);
        mpop.setOnDismissListener(new poponDismissListener());
        imagepop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpop.dismiss();

            }
        });
        addshopcart = contentView.findViewById(R.id.addshoppingcart);
        add = contentView.findViewById(R.id.add);
        jian = contentView.findViewById(R.id.jian);
        num = contentView.findViewById(R.id.num);
        num.setText(count + "");
        addgreendao();

    }

    private void addgreendao() {
        addshopcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
              /*  DBManager db = new DBManager(DetailActivity.this);
                shopcartdaobean bean = new shopcartdaobean();
                bean.setId(null);
                bean.setImage(goods_image);
                bean.setPrice(goods_info.getGoods_promotion_price());
                bean.setTitle(title.getText().toString());
                bean.setCount(count);
                db.insertUser(bean);*/
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                num.setText(count + "");

            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                if (count <= 1) {
                    count = 1;
                }

                num.setText(count + "");
            }
        });
    }

    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            params.alpha = 1f;
        }

    }

    private void initdata() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        HttpUtil.getHttpUtil().get(DetailActivity.this, AppConstans.PRODUCT_DETAIL + id, Detailbean.class, new OnNetListener() {
                    @Override
                    public void onSuccess(BaseBean basebean) {
                        bean = (Detailbean) basebean;
                        list.addAll(bean.getDatas().getGoods_commend_list());
                        adapter = new DetailAdapter(list, DetailActivity.this);
                        lv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        goods_info = bean.getDatas().getGoods_info();
                        title.setText(goods_info.getGoods_name());
                        jieshao.setText(goods_info.getGoods_jingle());
                        price.setText(goods_info.getGoods_promotion_price());
                        sale.setText("销售" + goods_info.getGoods_salenum() + "件");
                        goods_image = bean.getDatas().getGoods_image();
                        String replace = goods_image.replace("127.0.0.1", "169.254.50.98");
                        Glide.with(DetailActivity.this).load(replace).into(image);
                        image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Detailbean.DatasBean.GoodsCommendListBean bean=new Detailbean.DatasBean.GoodsCommendListBean();
                                Intent intent=new Intent(DetailActivity.this,Pictrue.class);
                                intent.putExtra("goods_id",bean.getGoods_id());
                                startActivity(intent);
                            }
                        });

                    }


                }
        );
    }
}