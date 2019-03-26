package com.tonkia.rainbow.service;

import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.DoctorInfo;

import java.util.List;

public interface DoctorService {
    DoctorInfo getDoctorInfoByUid(Integer uid);

    DoctorInfo insertDoctorInfo(DoctorInfo doctorInfo);

    List<ArticleInfo> getAllArticleByUid(Integer uid);

    boolean insertArticle(ArticleInfo articleInfo);

    boolean deleteArticle(Integer uid, Integer id);

    DoctorInfo getDoctorInfoById(Integer uid);

    boolean insertThumbup(String phoneNumber, String token, Integer doctorId);

    boolean insertThumbdown(String phoneNumber, String token, Integer doctorId);
}
