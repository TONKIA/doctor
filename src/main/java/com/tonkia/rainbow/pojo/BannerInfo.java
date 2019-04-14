package com.tonkia.rainbow.pojo;

public class BannerInfo {

    private Integer id;
    private String img;
    private String url;
    //0 用于主页
    //1 用于疗伤
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BannerInfo() {

    }

    public BannerInfo(String img, String url, Integer type) {
        this.img = img;
        this.url = url;
        this.type = type;
    }
}
