package com.tonkia.rainbow.service.impl;

import com.tonkia.rainbow.mapper.PsychologyMapper;
import com.tonkia.rainbow.mapper.UserMapper;
import com.tonkia.rainbow.pojo.BannerInfo;
import com.tonkia.rainbow.pojo.SayingCmt;
import com.tonkia.rainbow.pojo.SayingInfo;
import com.tonkia.rainbow.pojo.UserInfo;
import com.tonkia.rainbow.service.PsychologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PsychologyServiceImpl implements PsychologyService {
    @Autowired
    private PsychologyMapper psychologyMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<SayingInfo> getAllSaying(Integer category) {
        List<SayingInfo> list = psychologyMapper.getAllSaying(category);
        for (SayingInfo sayingInfo : list) {
            sayingInfo.setCmtCount(psychologyMapper.getCmtCount(sayingInfo.getId()));
            sayingInfo.setPraise(psychologyMapper.getPraiseCount(sayingInfo.getId()));
        }
        return list;
    }

    @Override
    public boolean insertSaying(SayingInfo sayingInfo) {
        sayingInfo.setTime(new Date());
        return psychologyMapper.insertSaying(sayingInfo) > 0;
    }

    @Override
    public List<BannerInfo> getBanner() {
        return psychologyMapper.getBanner();
    }

    @Override
    public SayingInfo getSayingInfo(Integer sayingId) {
        SayingInfo sayingInfo = psychologyMapper.getSayingInfo(sayingId);
        sayingInfo.setCmtCount(psychologyMapper.getCmtCount(sayingId));
        sayingInfo.setPraise(psychologyMapper.getPraiseCount(sayingId));
        sayingInfo.setCmtInfoList(psychologyMapper.getCmtInfo(sayingId));

        return sayingInfo;
    }

    @Override
    public boolean isThumbup(String phoneNumber, String token, Integer sayingId) {
        UserInfo user = new UserInfo();
        user.setPhoneNumber(phoneNumber);
        user.setToken(token);
        UserInfo userInfo = userMapper.getUserInfoByLogin(user);
        if (userInfo != null) {
            Map map = new HashMap();
            map.put("uid", userInfo.getUid());
            map.put("sayingId", sayingId);
            return (psychologyMapper.isThumbup(map) > 0);
        }
        return false;
    }

    @Override
    public Integer insertThumbup(String phoneNumber, String token, Integer sayingId) {
        UserInfo user = new UserInfo();
        user.setPhoneNumber(phoneNumber);
        user.setToken(token);
        UserInfo userInfo = userMapper.getUserInfoByLogin(user);
        if (userInfo != null) {
            Map map = new HashMap();
            map.put("uid", userInfo.getUid());
            map.put("sayingId", sayingId);
            if (psychologyMapper.isThumbup(map) == 0)
                if (psychologyMapper.insertThumbup(map) > 0)
                    return psychologyMapper.getPraiseCount(sayingId);
        }
        return null;
    }

    @Override
    public SayingInfo insertCmt(String phoneNumber, String token, SayingCmt sayingCmt) {
        sayingCmt.setTime(new Date());
        UserInfo user = new UserInfo();
        user.setPhoneNumber(phoneNumber);
        user.setToken(token);
        UserInfo userInfo = userMapper.getUserInfoByLogin(user);
        if (userInfo != null) {
            sayingCmt.setUid(userInfo.getUid());
            if (psychologyMapper.insertCmt(sayingCmt)) {
                SayingInfo sayingInfo = new SayingInfo();
                sayingInfo.setCmtCount(psychologyMapper.getCmtCount(sayingCmt.getSayingId()));
                sayingInfo.setCmtInfoList(psychologyMapper.getCmtInfo(sayingCmt.getSayingId()));
                return sayingInfo;
            }
        }
        return null;
    }

}
