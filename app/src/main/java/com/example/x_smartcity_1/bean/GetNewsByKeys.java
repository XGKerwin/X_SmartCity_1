package com.example.x_smartcity_1.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/24  8:53
 */
public class GetNewsByKeys {
    /**
     *             "newsid": "3",
     *             "newsType": "时政",
     *             "picture": "http://192.168.101.7:8080/mobileA/images/3.jpg",
     *             "abstract": "打开电视，收看新闻：\r\n\r\n今天国家又出台了新的政策，\r\n\r\n明天国家又开展了各种行动，\r\n\r\n不久我就享受到了各种优惠。\r\n\r\n就好像我看到家乡亮起了一盏盏路灯；\r\n\r\n看到家附近的黑恶团伙在扫黑除恶行动下一个个落网；\r\n\r\n看到新中国首部民法典颁布；\r\n\r\n看到疫情期间祖国强有力的指挥和调动十四亿人民，创造了一个又一个奇迹，成为全世界疫情防控得最好的国家和最先研制出有效疫苗的国家。",
     *             "title": "礼赞新中国成立七十一周年华诞！",
     *             "url": "https://new.qq.com/omn/20201001/20201001A02G2N00.html"
     */

    private String newsid,newsType,picture,title,url;
    @SerializedName("abstract")
    private String abstractX;

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public String getAbstractX() {
        return abstractX;
    }

    public void setAbstractX(String abstractX) {
        this.abstractX = abstractX;
    }





}
