package com.tonkia.rainbow.pojo;

public class HospitalInfo {
    private Integer hospitalId;
    private String hospitalUrl;
    private String name;
    private Float distance;
    private String label;
    private String[] labelArray;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalUrl() {
        return hospitalUrl;
    }

    public void setHospitalUrl(String hospitalUrl) {
        this.hospitalUrl = hospitalUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String[] getLabelArray() {
        return labelArray;
    }

    public void setLabelArray(String[] labelArray) {
        this.labelArray = labelArray;
    }
}