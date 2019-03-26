package com.tonkia.rainbow.service.impl;

import com.tonkia.rainbow.mapper.DoctorMapper;
import com.tonkia.rainbow.mapper.UserMapper;
import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.DoctorInfo;
import com.tonkia.rainbow.pojo.UserInfo;
import com.tonkia.rainbow.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    UserMapper userMapper;

    //
    @Override
    public DoctorInfo getDoctorInfoByUid(Integer uid) {
        DoctorInfo doctorInfo = doctorMapper.getDoctorInfoByUid(uid);
        Float favorRate = doctorMapper.getDoctorFavorRateByUid(uid);
        if (favorRate != null)
            doctorInfo.setFavorRate(favorRate);
        return doctorInfo;
    }


    @Override
    public DoctorInfo getDoctorInfoById(Integer uid) {
        DoctorInfo di = doctorMapper.getDoctorInfoById(uid);
        di.setLabelArray(di.getLabel().split("&"));
        Float favorRate = doctorMapper.getDoctorFavorRateByUid(uid);
        if (favorRate != null)
            di.setFavorRate(favorRate);
        return di;
    }

    @Override
    public DoctorInfo insertDoctorInfo(DoctorInfo doctorInfo) {
        if (doctorMapper.insertDoctorInfo(doctorInfo) > 0)
            return getDoctorInfoByUid(doctorInfo.getUid());
        else
            return null;
    }

    @Override
    public List<ArticleInfo> getAllArticleByUid(Integer uid) {
        return doctorMapper.getAllArticleByUid(uid);
    }

    @Override
    public boolean insertArticle(ArticleInfo articleInfo) {
        return doctorMapper.insertArticle(articleInfo);
    }

    @Override
    public boolean deleteArticle(Integer uid, Integer id) {
        Map map = new HashMap<>();
        map.put("uid", uid);
        map.put("articleId", id);
        return doctorMapper.deleteArticle(map);
    }


    @Override
    public boolean insertThumbup(String phoneNumber, String token, Integer doctorId) {
        UserInfo user = new UserInfo();
        user.setPhoneNumber(phoneNumber);
        user.setToken(token);
        UserInfo userInfo = userMapper.getUserInfoByLogin(user);
        if (userInfo != null) {
            Map map = new HashMap();
            map.put("uid", userInfo.getUid());
            map.put("doctorId", doctorId);
            return doctorMapper.insertThumbup(map);
        }
        return false;
    }

    @Override
    public boolean insertThumbdown(String phoneNumber, String token, Integer doctorId) {
        UserInfo user = new UserInfo();
        user.setPhoneNumber(phoneNumber);
        user.setToken(token);
        UserInfo userInfo = userMapper.getUserInfoByLogin(user);
        if (userInfo != null) {
            Map map = new HashMap();
            map.put("uid", userInfo.getUid());
            map.put("doctorId", doctorId);
            return doctorMapper.insertThumbdown(map);
        }
        return false;
    }
}
