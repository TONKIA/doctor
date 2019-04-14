package com.tonkia.rainbow.service;

import com.tonkia.rainbow.pojo.BannerInfo;
import com.tonkia.rainbow.pojo.SayingCmt;
import com.tonkia.rainbow.pojo.SayingInfo;

import java.util.List;

public interface PsychologyService {
    List<SayingInfo> getAllSaying(Integer category);

    boolean insertSaying(SayingInfo sayingInfo);

    List<BannerInfo> getBanner();

    SayingInfo getSayingInfo(Integer sayingId);

    boolean isThumbup(String phoneNumber, String token, Integer articleId);

    Integer insertThumbup(String phoneNumber, String token, Integer sayingId);

    SayingInfo insertCmt(String phoneNumber, String token, SayingCmt sayingCmt);
}
