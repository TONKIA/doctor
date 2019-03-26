package com.tonkia.rainbow.service;

import com.tonkia.rainbow.pojo.SayingInfo;

import java.util.List;

public interface PsychologyService {
    List<SayingInfo> getAllSaying(Integer category);

    boolean insertSaying(SayingInfo sayingInfo);
}
