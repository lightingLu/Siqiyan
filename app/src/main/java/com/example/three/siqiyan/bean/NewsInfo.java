package com.example.three.siqiyan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Three on 2016/4/21.
 * 资讯信息的Javabean
 *
 */
public class NewsInfo implements Parcelable {

    /**
     * code : 200
     * more : /zixun/list_2.json
     * newslist : [{"listimage":"http://posts.cdn.wallstcn.com/73/55/b5/20151118145843-2b447be3.jpg","pubdate":"6分钟前","title":"德国副总理：欧洲央行刺激已到达\u201c极限\u201d 呼吁政府加大开支","url":"http://wallstreetcn.com/node/235204"},{"listimage":"http://posts.cdn.wallstcn.com/40/95/74/2009101315572930e41.jpg","pubdate":"18分钟前","title":"产油国还在演\u201c宫心计\u201d 但是留给他们折腾的时间可能只有五年了","url":"http://wallstreetcn.com/node/235199"},{"listimage":"http://posts.cdn.wallstcn.com/14/4f/8e/20151124211632-85b4b39c.jpg","pubdate":"23分钟前","title":" 三菱汽车承认燃油经济性测试作弊 股价创近12年最大跌幅 ","url":"http://wallstreetcn.com/node/235198"},{"listimage":"http://posts.cdn.wallstcn.com/a3/07/f0/8WbFkf.jpg","pubdate":"46分钟前","title":"比肩摩根大通 这家小投行抢到了可能是史上最大规模的一笔IPO生意 ","url":"http://wallstreetcn.com/node/235197"},{"listimage":"http://posts.cdn.wallstcn.com/56/d7/60/20120216105938153.jpg","pubdate":"54分钟前","title":"美国十年期国债收益率会跌到1.25%？","url":"http://wallstreetcn.com/node/235192"},{"listimage":"http://posts.cdn.wallstcn.com/0d/00/70/keqiang-li.jpg","pubdate":"2小时前","title":"李克强：五大政策促进外贸回稳向好","url":"http://wallstreetcn.com/node/235194"},{"listimage":"http://posts.cdn.wallstcn.com/3e/d4/1d/wireap-efa5c5087e274adb941626507c70f7f8-16x9-992.jpg","pubdate":"3小时前","title":"\u201c上海协议\u201d的秘密\u2014\u2014结束货币战争 人民币\u201c策略性贬值\u201d ","url":"http://wallstreetcn.com/node/235195"},{"listimage":"http://posts.cdn.wallstcn.com/5f/a0/3f/farstad-shipping-reports-2q-and-1st-half-year-results.jpg","pubdate":"3小时前","title":"这个刚刚成立的新联盟 可能改变全球航运界整体格局","url":"http://wallstreetcn.com/node/235193"},{"listimage":"http://posts.cdn.wallstcn.com/93/1d/1f/20160115103214374.jpg","pubdate":"4小时前","title":"最大的P2P也难脱身？红岭回应25亿逾期借款回收问题","url":"http://wallstreetcn.com/node/235181"},{"listimage":"http://posts.cdn.wallstcn.com/14/7b/51/20151019102643-5f149251-me.jpg","pubdate":"5小时前","title":"中国科技公司受亲睐：BAT赴离岸联贷市场集资 华为计划发债20亿美元","url":"http://wallstreetcn.com/node/235191"}]
     * topic : [{"topimage":"http://posts.cdn.wallstcn.com/e4/a6/aa/137044556.jpg","toptitle":"黑色系夜盘领涨 螺纹钢、铁矿石涨超2%","topurl":"http://wallstreetcn.com/node/235183"},{"topimage":"http://posts.cdn.wallstcn.com/3e/1f/d5/2.jpg","toptitle":"股债双杀 中国宽松转折点来临？","topurl":"http://wallstreetcn.com/node/235200"},{"topimage":"http://posts.cdn.wallstcn.com/9b/1d/f1/551471-110627-china-property.jpg","toptitle":"1万亿元！中国一季度新增个人住房贷款创新高","topurl":"http://wallstreetcn.com/node/235189"},{"topimage":"http://posts.cdn.wallstcn.com/08/e8/69/-4.png","toptitle":"OPEC不给力EIA来\u201c救火\u201d 美油库存增幅不及预期","topurl":"http://wallstreetcn.com/node/235210"},{"topimage":"http://posts.cdn.wallstcn.com/33/35/fa/89.jpg","toptitle":"扎克伯格的野心：Facebook的VR十年","topurl":"http://wallstreetcn.com/node/235203"}]
     */

    private int code;
    private String more;
    /**
     * listimage : http://posts.cdn.wallstcn.com/73/55/b5/20151118145843-2b447be3.jpg
     * pubdate : 6分钟前
     * title : 德国副总理：欧洲央行刺激已到达“极限” 呼吁政府加大开支
     * url : http://wallstreetcn.com/node/235204
     */

    private List<NewslistBean> newslist;
    /**
     * topimage : http://posts.cdn.wallstcn.com/e4/a6/aa/137044556.jpg
     * toptitle : 黑色系夜盘领涨 螺纹钢、铁矿石涨超2%
     * topurl : http://wallstreetcn.com/node/235183
     */

    private List<TopicBean> topic;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public List<TopicBean> getTopic() {
        return topic;
    }

    public void setTopic(List<TopicBean> topic) {
        this.topic = topic;
    }

    public static class NewslistBean {
        private String listimage;
        private String pubdate;
        private String title;
        private String url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class TopicBean {
        private String topimage;
        private String toptitle;
        private String topurl;

        public String getTopimage() {
            return topimage;
        }

        public void setTopimage(String topimage) {
            this.topimage = topimage;
        }

        public String getToptitle() {
            return toptitle;
        }

        public void setToptitle(String toptitle) {
            this.toptitle = toptitle;
        }

        public String getTopurl() {
            return topurl;
        }

        public void setTopurl(String topurl) {
            this.topurl = topurl;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.more);
        dest.writeList(this.newslist);
        dest.writeList(this.topic);
    }

    public NewsInfo() {
    }

    protected NewsInfo(Parcel in) {
        this.code = in.readInt();
        this.more = in.readString();
        this.newslist = new ArrayList<NewslistBean>();
        in.readList(this.newslist, NewslistBean.class.getClassLoader());
        this.topic = new ArrayList<TopicBean>();
        in.readList(this.topic, TopicBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<NewsInfo> CREATOR = new Parcelable.Creator<NewsInfo>() {
        @Override
        public NewsInfo createFromParcel(Parcel source) {
            return new NewsInfo(source);
        }

        @Override
        public NewsInfo[] newArray(int size) {
            return new NewsInfo[size];
        }
    };
}
