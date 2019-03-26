package com.tonkia.rainbow.service.impl;

import com.tonkia.rainbow.mapper.ManagerMapper;
import com.tonkia.rainbow.pojo.BannerInfo;
import com.tonkia.rainbow.pojo.TipInfo;
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

    @Override
    public boolean updateCancelState(int id) {
        return managerMapper.updateCancelState(id) > 0;
    }

    @Override
    public boolean updateTopState(int id) {
        return managerMapper.updateTopState(id) > 0;
    }

    @Override
    public boolean updateRecommandState(int id) {
        return managerMapper.updateRecommandState(id) > 0;
    }

    @Override
    public boolean insertTip(TipInfo tipInfo) {
        return managerMapper.insertTip(tipInfo) > 0;
    }

    @Override
    public boolean deleteTip(int id) {
        return managerMapper.delTip(id) > 0;
    }


}
