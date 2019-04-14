package com.tonkia.rainbow.pojo;

import java.util.Date;
import java.util.List;

public class SayingInfo {

    private Integer id;
    private String content;
    private Integer cmtCount;
    private Integer praise;
    private String imgUrl;
    private Integer category;
    private Integer uid;
    private Date time;
    private List<CmtInfo> cmtInfoList;

    public List<CmtInfo> getCmtInfoList() {
        return cmtInfoList;
    }

    public void setCmtInfoList(List<CmtInfo> cmtInfoList) {
        this.cmtInfoList = cmtInfoList;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCmtCount() {
        return cmtCount;
    }

    public void setCmtCount(Integer cmtCount) {
        this.cmtCount = cmtCount;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
