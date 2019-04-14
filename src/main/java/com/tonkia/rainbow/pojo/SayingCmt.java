package com.tonkia.rainbow.pojo;

import java.util.Date;

public class SayingCmt {
    private Integer sayingId;
    private Integer uid;
    private String avator;
    private String nikeName;
    private String content;
    private Date time;
    private Integer id;

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public Integer getSayingId() {
        return sayingId;
    }

    public void setSayingId(Integer sayingId) {
        this.sayingId = sayingId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
