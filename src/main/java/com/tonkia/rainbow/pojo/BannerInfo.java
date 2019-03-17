package com.tonkia.rainbow.pojo;

public class BannerInfo {

    private Integer id;
    private String img;
    private String url;

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

    public BannerInfo(String img, String url) {
        this.img = img;
        this.url = url;
    }
}
