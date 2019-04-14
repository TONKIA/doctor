package com.tonkia.rainbow.service.impl;

import com.tonkia.rainbow.mapper.ArticleMapper;
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
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public List<ArticleInfo> getTop() {
        List<ArticleInfo> list = popularizationMapper.getTop();
        for (ArticleInfo articleInfo : list) {
            articleInfo.setCmtCount(articleMapper.getCmtCount(articleInfo.getArticleId()));
            articleInfo.setPraise(articleMapper.getThumbupCount(articleInfo.getArticleId()));
            articleInfo.setNewestCmt(articleMapper.getNewestCmt(articleInfo.getArticleId()));
        }
        return list;
    }

    @Override
    public List<ArticleInfo> getFocus(String phoneNumber, String token) {
        UserInfo user = new UserInfo();
        user.setPhoneNumber(phoneNumber);
        user.setToken(token);
        UserInfo userInfo = userMapper.getUserInfoByLogin(user);
        List<ArticleInfo> list = popularizationMapper.getFocus(userInfo);
        for (ArticleInfo articleInfo : list) {
            articleInfo.setCmtCount(articleMapper.getCmtCount(articleInfo.getArticleId()));
            articleInfo.setPraise(articleMapper.getThumbupCount(articleInfo.getArticleId()));
            articleInfo.setNewestCmt(articleMapper.getNewestCmt(articleInfo.getArticleId()));
        }
        return list;
    }

    @Override
    public List<ArticleInfo> getRecommand() {
        List<ArticleInfo> list = popularizationMapper.getRecommand();
        for (ArticleInfo articleInfo : list) {
            articleInfo.setCmtCount(articleMapper.getCmtCount(articleInfo.getArticleId()));
            articleInfo.setPraise(articleMapper.getThumbupCount(articleInfo.getArticleId()));
            articleInfo.setNewestCmt(articleMapper.getNewestCmt(articleInfo.getArticleId()));
        }
        return list;

    }

    @Override
    public List<ArticleInfo> getCategory(int category) {

        List<ArticleInfo> list = popularizationMapper.getCategory(category);
        for (ArticleInfo articleInfo : list) {
            articleInfo.setCmtCount(articleMapper.getCmtCount(articleInfo.getArticleId()));
            articleInfo.setPraise(articleMapper.getThumbupCount(articleInfo.getArticleId()));
            articleInfo.setNewestCmt(articleMapper.getNewestCmt(articleInfo.getArticleId()));
        }
        return list;
    }

    @Override
    public List<ArticleInfo> search(String keyword) {
        List<ArticleInfo> list = popularizationMapper.search(keyword);
        for (ArticleInfo ai : list) {
            String title = ai.getTitle();
            title = title.replace(keyword, "<font color='#2db4ff'>" + keyword + "</font>");
            ai.setTitle(title);
            String summary = ai.getSummary();
            summary = summary.replace(keyword, "<font color='#2db4ff'>" + keyword + "</font>");
            ai.setSummary(summary);
            ai.setCmtCount(articleMapper.getCmtCount(ai.getArticleId()));
            ai.setPraise(articleMapper.getThumbupCount(ai.getArticleId()));
            ai.setNewestCmt(articleMapper.getNewestCmt(ai.getArticleId()));
        }
        return list;
    }
}
