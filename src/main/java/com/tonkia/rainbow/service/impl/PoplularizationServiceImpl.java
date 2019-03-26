package com.tonkia.rainbow.service.impl;

import com.tonkia.rainbow.mapper.PopularizationMapper;
import com.tonkia.rainbow.mapper.UserMapper;
import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.UserInfo;
import com.tonkia.rainbow.service.PopularizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoplularizationServiceImpl implements PopularizationService {

    @Autowired
    PopularizationMapper popularizationMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<ArticleInfo> getTop() {
        return popularizationMapper.getTop();
    }

    @Override
    public List<ArticleInfo> getFocus(String phoneNumber, String token) {
        UserInfo user = new UserInfo();
        user.setPhoneNumber(phoneNumber);
        user.setToken(token);
        UserInfo userInfo = userMapper.getUserInfoByLogin(user);
        return popularizationMapper.getFocus(userInfo);
    }

    @Override
    public List<ArticleInfo> getRecommand() {
        return popularizationMapper.getRecommand();
    }

    @Override
    public List<ArticleInfo> getCategory(int category) {
        return popularizationMapper.getCategory(category);
    }
}
