package com.tonkia.rainbow.pojo;

public class FilterInfo {
    //0全部
    //1医生
    //2医院
    private Integer category;
    //0全部
    //1名医
    //2主任
    //3医师

    //1三甲
    //2二甲
    //3公立
    //4私立
    //5专科
    private Integer childCategory;

    //0全部
    //1性病
    //2器官

    private Integer disease;

    //附近
    //1 1km
    //2 3km
    //3 5km
    private Integer distance;

    //0 智能
    //1 好评
    //2 人气
    //3 距离
    private Integer sort;


    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(Integer childCategory) {
        this.childCategory = childCategory;
    }

    public Integer getDisease() {
        return disease;
    }

    public void setDisease(Integer disease) {
        this.disease = disease;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
