package com.walxy.mallproject.mvp.model.bean;

import java.util.List;

public class Databean {

    private int server_time;
    private int total_size;
    private List<GoodsListBean> goods_list;

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
        this.server_time = server_time;
    }

    public int getTotal_size() {
        return total_size;
    }

    public void setTotal_size(int total_size) {
        this.total_size = total_size;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class GoodsListBean {

        private int normal_price;
        private int cnt;
        private String thumb_url;
        private int event_type;
        private int market_price;
        private PRecBean p_rec;
        private int region_limit;
        private String goods_name;
        private String country;
        private int goods_id;
        private String short_name;
        private GroupBean group;
        private String allowed_region;
        private String image_url;
        private String hd_thumb_url;
        private int is_app;

        public int getNormal_price() {
            return normal_price;
        }

        public void setNormal_price(int normal_price) {
            this.normal_price = normal_price;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        public String getThumb_url() {
            return thumb_url;
        }

        public void setThumb_url(String thumb_url) {
            this.thumb_url = thumb_url;
        }

        public int getEvent_type() {
            return event_type;
        }

        public void setEvent_type(int event_type) {
            this.event_type = event_type;
        }

        public int getMarket_price() {
            return market_price;
        }

        public void setMarket_price(int market_price) {
            this.market_price = market_price;
        }

        public PRecBean getP_rec() {
            return p_rec;
        }

        public void setP_rec(PRecBean p_rec) {
            this.p_rec = p_rec;
        }

        public int getRegion_limit() {
            return region_limit;
        }

        public void setRegion_limit(int region_limit) {
            this.region_limit = region_limit;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public GroupBean getGroup() {
            return group;
        }

        public void setGroup(GroupBean group) {
            this.group = group;
        }

        public String getAllowed_region() {
            return allowed_region;
        }

        public void setAllowed_region(String allowed_region) {
            this.allowed_region = allowed_region;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getHd_thumb_url() {
            return hd_thumb_url;
        }

        public void setHd_thumb_url(String hd_thumb_url) {
            this.hd_thumb_url = hd_thumb_url;
        }

        public int getIs_app() {
            return is_app;
        }

        public void setIs_app(int is_app) {
            this.is_app = is_app;
        }

        public static class PRecBean {

            private String t;
            private String m;
            private String bk;
            private String g;

            public String getT() {
                return t;
            }

            public void setT(String t) {
                this.t = t;
            }

            public String getM() {
                return m;
            }

            public void setM(String m) {
                this.m = m;
            }

            public String getBk() {
                return bk;
            }

            public void setBk(String bk) {
                this.bk = bk;
            }

            public String getG() {
                return g;
            }

            public void setG(String g) {
                this.g = g;
            }
        }

        public static class GroupBean {

            private int customer_num;
            private int price;

            public int getCustomer_num() {
                return customer_num;
            }

            public void setCustomer_num(int customer_num) {
                this.customer_num = customer_num;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }
}
