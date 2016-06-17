package com.myzhsh.bean;

import java.util.List;

/**
 * Created by yh on 2016/6/17.
 * 新闻列表实体类
 */
public class NewsListData  {

    private NewsTab data;

    private int retcode;

    public NewsTab getData() {
        return data;
    }

    public void setData(NewsTab data) {
        this.data = data;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public static class NewsTab {
        @Override
        public String toString() {
            return "NewsTab{" +
                    "countcommenturl='" + countcommenturl + '\'' +
                    ", more='" + more + '\'' +
                    ", title='" + title + '\'' +
                    ", news=" + news +
                    ", topnews=" + topnews +
                    '}';
        }

        private String countcommenturl;
        private String more;
        private String title;

        private List<NewsBean> news;


        private List<TopnewsBean> topnews;

        public String getCountcommenturl() {
            return countcommenturl;
        }

        public void setCountcommenturl(String countcommenturl) {
            this.countcommenturl = countcommenturl;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public List<TopnewsBean> getTopnews() {
            return topnews;
        }

        public void setTopnews(List<TopnewsBean> topnews) {
            this.topnews = topnews;
        }

        public static class NewsBean {
            @Override
            public String toString() {
                return "NewsBean{" +
                        "comment=" + comment +
                        ", commentlist='" + commentlist + '\'' +
                        ", commenturl='" + commenturl + '\'' +
                        ", id=" + id +
                        ", listimage='" + listimage + '\'' +
                        ", pubdate='" + pubdate + '\'' +
                        ", title='" + title + '\'' +
                        ", type='" + type + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }

            private boolean comment;
            private String commentlist;
            private String commenturl;
            private int id;
            private String listimage;
            private String pubdate;
            private String title;
            private String type;
            private String url;

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getListimage() {
                return listimage;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class TopnewsBean {
            @Override
            public String toString() {
                return "TopnewsBean{" +
                        "comment=" + comment +
                        ", commentlist='" + commentlist + '\'' +
                        ", commenturl='" + commenturl + '\'' +
                        ", id=" + id +
                        ", pubdate='" + pubdate + '\'' +
                        ", title='" + title + '\'' +
                        ", topimage='" + topimage + '\'' +
                        ", type='" + type + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }

            private boolean comment;
            private String commentlist;
            private String commenturl;
            private int id;
            private String pubdate;
            private String title;
            private String topimage;
            private String type;
            private String url;

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTopimage() {
                return topimage;
            }

            public void setTopimage(String topimage) {
                this.topimage = topimage;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
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
        return "NewsListData{" +
                "data=" + data +
                ", retcode=" + retcode +
                '}';
    }
}
