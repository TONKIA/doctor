package com.tonkia.rainbow.mapper;

import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.BannerInfo;
import com.tonkia.rainbow.pojo.DoctorInfo;
import com.tonkia.rainbow.pojo.TipInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HomeMapper {

    @Select("select * from banner")
    public List<BannerInfo> getBanner();

    @Select("SELECT doctor.uid, nikeName, user.avator, star, qualificate, favorRate, cmtCount, expert, label FROM doctor LEFT JOIN user ON doctor.uid = user.uid ORDER BY favorRate LIMIT #{0}")
    List<DoctorInfo> getDoctor(int doctorNumber);

    @Select("SELECT  articleId,article.uid,nikeName,avator,title,summary,cmtCount,praise,picUrl,articleUrl,content FROM article LEFT JOIN user ON article.uid = user.uid ORDER BY articleId DESC LIMIT #{0}")
    List<ArticleInfo> getArticle(int articleNumber);

    @Select("SELECT * FROM tip ORDER BY RAND() LIMIT #{0}")
    List<TipInfo> getTip(Integer tipNumber);
}
