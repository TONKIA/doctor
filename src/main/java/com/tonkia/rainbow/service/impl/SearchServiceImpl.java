package com.tonkia.rainbow.service.impl;

import com.tonkia.rainbow.mapper.DoctorMapper;
import com.tonkia.rainbow.mapper.SearchMapper;
import com.tonkia.rainbow.pojo.DoctorInfo;
import com.tonkia.rainbow.pojo.FilterInfo;
import com.tonkia.rainbow.pojo.HospitalInfo;
import com.tonkia.rainbow.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    SearchMapper searchMapper;
    @Autowired
    DoctorMapper doctorMapper;

    @Override
    public List<HospitalInfo> filterHospital(FilterInfo filterInfo) {
        List<HospitalInfo> list = searchMapper.filterHospital(filterInfo);
        for (HospitalInfo hi : list) {
            hi.setLabelArray(hi.getLabel().split("&"));
        }
        return list;
    }

    @Override
    public List<DoctorInfo> filterDoctor(FilterInfo filterInfo) {
        List<DoctorInfo> list = searchMapper.filterDoctor(filterInfo);
        for (DoctorInfo di : list) {
            di.setLabelArray(di.getLabel().split("&"));
            Float favorRate = doctorMapper.getDoctorFavorRateByUid(di.getUid());
            int praiseCount = doctorMapper.getPraiseCount(di.getUid());
            if (favorRate != null)
                di.setFavorRate(favorRate);
            di.setPraiseCount(praiseCount);
            int cmtCount = doctorMapper.getCmtCountByUid(di.getUid());
            di.setCmtCount(cmtCount);
        }
        return list;

    }

    @Override
    public HospitalInfo getHospitalById(int i) {
        HospitalInfo hospitalInfo = searchMapper.getHospitalById(i);
        hospitalInfo.setLabelArray(hospitalInfo.getLabel().split("&"));
        return hospitalInfo;
    }
}
