package com.tonkia.rainbow.service.impl;

import com.tonkia.rainbow.mapper.ManagerMapper;
import com.tonkia.rainbow.pojo.BannerInfo;
import com.tonkia.rainbow.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    ManagerMapper managerMapper;

    @Override
    public boolean insertBanner(BannerInfo bannerInfo) {
        return managerMapper.insertBannerInfo(bannerInfo) > 0;
    }

    @Override
    public boolean deleteBanner(int id) {
        return managerMapper.delBannerById(id) > 0;
    }
}
