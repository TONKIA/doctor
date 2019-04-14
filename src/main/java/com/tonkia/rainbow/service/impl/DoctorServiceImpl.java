package com.tonkia.rainbow.service.impl;

import com.tonkia.rainbow.mapper.DoctorMapper;
import com.tonkia.rainbow.mapper.UserMapper;
import com.tonkia.rainbow.pojo.*;
import com.tonkia.rainbow.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        int praiseCount = doctorMapper.getPraiseCount(uid);
        if (favorRate != null)
            doctorInfo.setFavorRate(favorRate);
        doctorInfo.setPraiseCount(praiseCount);
        return doctorInfo;
    }


    @Override
    public DoctorInfo getDoctorInfoById(Integer uid) {
        DoctorInfo di = doctorMapper.getDoctorInfoById(uid);
        di.setLabelArray(di.getLabel().split("&"));
        Float favorRate = doctorMapper.getDoctorFavorRateByUid(uid);
        int praiseCount = doctorMapper.getPraiseCount(uid);
        if (favorRate != null)
            di.setFavorRate(favorRate);
        di.setPraiseCount(praiseCount);
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

    @Override
    public boolean updateDoctorInfo(DoctorInfo doctorInfo) {
        return doctorMapper.updateDoctorInfo(doctorInfo) > 0 && doctorMapper.updateUserInfo(doctorInfo) > 0;
    }

    @Override
    public boolean insertFocus(String phoneNumber, String token, Integer doctorId) {
        UserInfo user = new UserInfo();
        user.setPhoneNumber(phoneNumber);
        user.setToken(token);
        UserInfo userInfo = userMapper.getUserInfoByLogin(user);
        if (userInfo != null) {
            Map map = new HashMap();
            map.put("uid", userInfo.getUid());
            map.put("doctorId", doctorId);
            if (!(doctorMapper.isFocus(map) > 0))
                return doctorMapper.insertFocus(map);
        }
        return false;
    }

    @Override
    public boolean isFocus(String phoneNumber, String token, Integer doctorId) {
        UserInfo user = new UserInfo();
        user.setPhoneNumber(phoneNumber);
        user.setToken(token);
        UserInfo userInfo = userMapper.getUserInfoByLogin(user);
        if (userInfo != null) {
            Map map = new HashMap();
            map.put("uid", userInfo.getUid());
            map.put("doctorId", doctorId);
            return (doctorMapper.isFocus(map) > 0);
        }
        return false;
    }

    @Override
    public List<CmtInfo> getComment(Integer doctorId) {
        return doctorMapper.getComment(doctorId);
    }

    @Override
    public boolean insertCmt(String phoneNumber, String token, DoctorCmtInfo cmtInfo) {
        UserInfo user = new UserInfo();
        user.setPhoneNumber(phoneNumber);
        user.setToken(token);
        UserInfo userInfo = userMapper.getUserInfoByLogin(user);
        if (userInfo != null) {
            cmtInfo.setUid(userInfo.getUid());
            cmtInfo.setTime(new Date());
            return doctorMapper.insertCmt(cmtInfo);
        }
        return false;
    }

    @Override
    public boolean insertFocusBySession(Integer uid, Integer doctorId) {
        Map map = new HashMap();
        map.put("uid", uid);
        map.put("doctorId", doctorId);
        if (!(doctorMapper.isFocus(map) > 0))
            return doctorMapper.insertFocus(map);
        else return false;
    }

    @Override
    public boolean isFocusBySession(Integer uid, Integer doctorId) {
        Map map = new HashMap();
        map.put("uid", uid);
        map.put("doctorId", doctorId);
        return (doctorMapper.isFocus(map) > 0);
    }

    @Override
    public int getFocusCount(Integer doctorId) {
        return doctorMapper.getFocusCount(doctorId);
    }

    @Override
    public boolean deleteFocusBySession(Integer uid, Integer doctorId) {
        Map date =new HashMap();
        date.put("uid",uid);
        date.put("doctorUid",doctorId);
        return doctorMapper.deleteFocus(date);
    }
}
