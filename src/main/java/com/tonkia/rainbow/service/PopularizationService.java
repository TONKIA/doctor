package com.tonkia.rainbow.service;


import com.tonkia.rainbow.pojo.ArticleInfo;

import java.util.List;

public interface PopularizationService {
    List<ArticleInfo> getTop();

    List<ArticleInfo> getFocus(String phoneNumber, String token);

    List<ArticleInfo> getRecommand();

    List<ArticleInfo> getCategory(int category);

    List<ArticleInfo> search(String keyword);
}
