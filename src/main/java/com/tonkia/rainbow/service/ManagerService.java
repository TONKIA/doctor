package com.tonkia.rainbow.service;

import com.tonkia.rainbow.pojo.BannerInfo;
import com.tonkia.rainbow.pojo.TipInfo;

public interface ManagerService {
    public boolean insertBanner(BannerInfo bannerInfo);

    public boolean deleteBanner(int id);

    boolean updateCancelState(int id);

    boolean updateTopState(int id);

    boolean updateRecommandState(int id);

    boolean insertTip(TipInfo tipInfo);

    boolean deleteTip(int id);
}
