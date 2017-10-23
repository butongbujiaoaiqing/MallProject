package com.walxy.mallproject.mvp.view;

/**
 * 类用途 :常量池网络接口
 * 作者 : 于帅光
 * 时间 : 2017/9/7
 */
public final class AppConstans {
    /**
     * 分类的网络接口
     */
    public static final class CLASSFY_BASE {
        public static final String DEVELOP = "http://169.254.49.93";
        public static final String BASE_CLASSFY_URL = "http://169.254.49.93/mobile/index.php?act=goods_class";
        public static final String BASE_CLASSFY_EXPAND_URL = "http://169.254.49.93/mobile/index.php?act=goods_class&gc_id=";
        public static final String BASE_HOME_URL = "http://apiv3.yangkeduo.com/v4/goods?";
        public static final String BASE_HOME_URL_TWO = "&size=20&list_id=1573810071&platform=2";

    }

    public static boolean isOnline = false;
    public static final String PRODUCT = "http://www.baidu.com";
    public static final String DEVELOP = "http://169.254.49.93";
    public static final String HOST = isOnline ? PRODUCT : DEVELOP;
    public static final String PRODUCT_DETAIL = HOST + "/mobile/index.php?act=goods&op=goods_detail&goods_id=";//详情
    public static final String PRODUCT_LIST = HOST + "/mobile/index.php?act=goods&op=goods_list&page=100&gc_id=";//首页
    public static final String LINK_MOBILE_GOODS_SEARCH = HOST + "/mobile/index.php?act=goods&op=goods_list&page=100";
    public static final String LINK_MOBILE_GOODS_BODY = HOST + "/mobile/index.php?act=goods&op=goods_body&goods_id=";
    public static final String LINK_MOBILE_CART = HOST + "member_cart&op=cart_list";
}
