package com.tonkia.rainbow.service.impl;

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

    @Override
    public List<BannerInfo> getBanner() {
        return homeMapper.getBanner();
    }

    @Override
    public List<DoctorInfo> getDoctor(int doctorNumber) {
        List<DoctorInfo> list = homeMapper.getDoctor(doctorNumber);
        for (DoctorInfo di : list) {
            di.setLabelArray(di.getLabel().split("&"));
        }
        return list;
    }

    @Override
    public List<ArticleInfo> getArticle(int articleNumber) {
        return homeMapper.getArticle(articleNumber);
    }

    @Override
    public List<TipInfo> getTip(Integer tipNumber) {
        return homeMapper.getTip(tipNumber);
    }


}
