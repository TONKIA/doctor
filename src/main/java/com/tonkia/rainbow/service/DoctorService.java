package com.tonkia.rainbow.service;

import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.CmtInfo;
import com.tonkia.rainbow.pojo.DoctorCmtInfo;
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

    boolean updateDoctorInfo(DoctorInfo doctorInfo);

    boolean insertFocus(String phoneNumber, String token, Integer doctorId);

    boolean isFocus(String phoneNumber, String token, Integer doctorId);

    List<CmtInfo> getComment(Integer doctorId);

    boolean insertCmt(String phoneNumber, String token, DoctorCmtInfo cmtInfo);

    boolean insertFocusBySession(Integer uid, Integer doctorId);

    boolean isFocusBySession(Integer uid, Integer doctorId);

    int getFocusCount(Integer doctorId);

    boolean deleteFocusBySession(Integer uid, Integer doctorId);
}
