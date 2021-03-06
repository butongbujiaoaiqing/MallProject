package com.walxy.mallproject.mvp.model.bean;

import java.util.List;

public class ClassBeanSecond {

    /**
     * code : 200
     * datas : {"class_list":[{"gc_id":"151","gc_name":"潮流女包"},{"gc_id":"152","gc_name":"时尚男包"},{"gc_id":"153","gc_name":"功能箱包"},{"gc_id":"154","gc_name":"礼品"},{"gc_id":"155","gc_name":"奢侈品"}]}
     */

    private int code;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private List<ClassListBean> class_list;

        public List<ClassListBean> getClass_list() {
            return class_list;
        }

        public void setClass_list(List<ClassListBean> class_list) {
            this.class_list = class_list;
        }

        public static class ClassListBean {
            /**
             * gc_id : 151
             * gc_name : 潮流女包
             */

            private String gc_id;
            private String gc_name;

            public String getGc_id() {
                return gc_id;
            }

            public void setGc_id(String gc_id) {
                this.gc_id = gc_id;
            }

            public String getGc_name() {
                return gc_name;
            }

            public void setGc_name(String gc_name) {
                this.gc_name = gc_name;
            }
        }
    }
}
