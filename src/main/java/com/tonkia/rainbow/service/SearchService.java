package com.tonkia.rainbow.service;

import com.tonkia.rainbow.pojo.DoctorInfo;
import com.tonkia.rainbow.pojo.FilterInfo;
import com.tonkia.rainbow.pojo.HospitalInfo;

import java.util.List;

public interface SearchService {
    List<HospitalInfo> filterHospital(FilterInfo filterInfo);

    List<DoctorInfo> filterDoctor(FilterInfo filterInfo);

    HospitalInfo getHospitalById(int i);
}
