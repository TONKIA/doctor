package com.tonkia.rainbow.service;

import com.tonkia.rainbow.pojo.BannerInfo;

public interface ManagerService {
    public boolean insertBanner(BannerInfo bannerInfo);

    public boolean deleteBanner(int id);
}
