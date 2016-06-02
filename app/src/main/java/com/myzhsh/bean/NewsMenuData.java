package com.myzhsh.bean;

import java.util.List;

/**
 * Created by yh on 2016/6/2.
 */
public class NewsMenuData {

    private int retcode;


    private List<NewsData> data;
    private List<Integer> extend;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public List<NewsData> getData() {
        return data;
    }

    public void setData(List<NewsData> data) {
        this.data = data;
    }

    public List<Integer> getExtend() {
        return extend;
    }

    public void setExtend(List<Integer> extend) {
        this.extend = extend;
    }

    public static class NewsData {
        private int id;
        private String title;
        private int type;

        private List<NewsTabData> children;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<NewsTabData> getChildren() {
            return children;
        }

        public void setChildren(List<NewsTabData> children) {
            this.children = children;
        }

        public static class NewsTabData {
            private int id;
            private String title;
            private int type;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    @Override
    public String toString() {
        return "NewsMenuData{" +
                "retcode=" + retcode +
                ", data=" + data +
                ", extend=" + extend +
                '}';
    }
}
