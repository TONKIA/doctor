package com.tonkia.rainbow.service;

import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.BannerInfo;
import com.tonkia.rainbow.pojo.DoctorInfo;
import com.tonkia.rainbow.pojo.TipInfo;

import java.util.List;

public interface HomeService {

    public List<BannerInfo> getBanner();

    public List<DoctorInfo> getDoctor(int doctorNumber);

    public List<ArticleInfo> getArticle(int articleNumber);

    public List<TipInfo> getTip(Integer tipNumber);
}
