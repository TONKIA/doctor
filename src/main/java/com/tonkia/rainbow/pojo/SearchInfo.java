package com.tonkia.rainbow.pojo;

import java.util.List;

public class SearchInfo {
    //百科
    Encyclopedias encyclopedias;
    String medicine;
    String recommend;
    String message;
    List<DoctorInfo> doctor;
    List<HospitalInfo> hospital;
    List<ArticleInfo> article;
    List<AdviceInfo> advice;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Encyclopedias getEncyclopedias() {
        return encyclopedias;
    }

    public void setEncyclopedias(Encyclopedias encyclopedias) {
        this.encyclopedias = encyclopedias;
    }

    public List<DoctorInfo> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<DoctorInfo> doctor) {
        this.doctor = doctor;
    }

    public List<HospitalInfo> getHospital() {
        return hospital;
    }

    public void setHospital(List<HospitalInfo> hospital) {
        this.hospital = hospital;
    }

    public List<ArticleInfo> getArticle() {
        return article;
    }

    public void setArticle(List<ArticleInfo> article) {
        this.article = article;
    }

    public List<AdviceInfo> getAdvice() {
        return advice;
    }

    public void setAdvice(List<AdviceInfo> advice) {
        this.advice = advice;
    }

    public class Encyclopedias {
        private String summary;
        private String[] labelArray;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String[] getLabelArray() {
            return labelArray;
        }

        public void setLabelArray(String[] labelArray) {
            this.labelArray = labelArray;
        }
    }


    public class AdviceInfo {
        private Integer adviceId;
        private String title;

        public Integer getAdviceId() {
            return adviceId;
        }

        public void setAdviceId(Integer adviceId) {
            this.adviceId = adviceId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


}
