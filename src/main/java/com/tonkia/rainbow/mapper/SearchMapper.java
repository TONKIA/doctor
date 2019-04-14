package com.tonkia.rainbow.mapper;

import com.tonkia.rainbow.pojo.DoctorInfo;
import com.tonkia.rainbow.pojo.FilterInfo;
import com.tonkia.rainbow.pojo.HospitalInfo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface SearchMapper {

    @SelectProvider(type = com.tonkia.rainbow.mapper.dyna.SearchDynaSqlProvicder.class, method = "filterHospital")
    List<HospitalInfo> filterHospital(FilterInfo filterInfo);

    @SelectProvider(type = com.tonkia.rainbow.mapper.dyna.SearchDynaSqlProvicder.class, method = "filterDoctor")
    List<DoctorInfo> filterDoctor(FilterInfo filterInfo);

    @Select("select hospital.hospitalId,hospitalUrl,name,distance,label from hospital left join search on hospital.hospitalId = search.hospitalId where hospital.hospitalId=#{0}")
    HospitalInfo getHospitalById(int i);
}
