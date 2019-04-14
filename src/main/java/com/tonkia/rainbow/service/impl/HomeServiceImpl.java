package com.tonkia.rainbow.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.tonkia.rainbow.mapper.ArticleMapper;
import com.tonkia.rainbow.mapper.DoctorMapper;
import com.tonkia.rainbow.mapper.HomeMapper;
import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.BannerInfo;
import com.tonkia.rainbow.pojo.DoctorInfo;
import com.tonkia.rainbow.pojo.TipInfo;
import com.tonkia.rainbow.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    HomeMapper homeMapper;
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public List<BannerInfo> getBanner() {
        return homeMapper.getBanner();
    }

    @Override
    public List<DoctorInfo> getDoctor(int doctorNumber) {
        List<DoctorInfo> list = homeMapper.getDoctor(doctorNumber);
        for (DoctorInfo di : list) {
            di.setLabelArray(di.getLabel().split("&"));
            Float favorRate = doctorMapper.getDoctorFavorRateByUid(di.getUid());
            if (favorRate != null)
                di.setFavorRate(favorRate);
            int cmtCount = doctorMapper.getCmtCountByUid(di.getUid());
            di.setCmtCount(cmtCount);
        }
        return list;
    }

    @Override
    public List<ArticleInfo> getArticle(int articleNumber) {
        List<ArticleInfo> list = homeMapper.getArticle(articleNumber);
        for (ArticleInfo articleInfo : list) {
            articleInfo.setCmtCount(articleMapper.getCmtCount(articleInfo.getArticleId()));
            articleInfo.setPraise(articleMapper.getThumbupCount(articleInfo.getArticleId()));
        }
        return list;
    }

    @Override
    public List<TipInfo> getTip(Integer tipNumber) {
        return homeMapper.getTip(tipNumber);
    }

    @Override
    public List<TipInfo> getAllTip() {
        return homeMapper.getAllTip();
    }


}
