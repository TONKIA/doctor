package com.tonkia.rainbow.mapper.dyna;

import com.tonkia.rainbow.pojo.FilterInfo;
import org.apache.ibatis.jdbc.SQL;

public class SearchDynaSqlProvicder {
    public String filterHospital(final FilterInfo filterInfo) {
        return new SQL() {
            {
                SELECT("search.hospitalId,hospitalUrl,name,label,distance");

                FROM("search");
                LEFT_OUTER_JOIN("hospital  on search.hospitalId = hospital.hospitalId");

                WHERE("category=2");
                switch (filterInfo.getCategory()) {
                    case 0:
                        break;
                    default:
                        if (filterInfo.getChildCategory() != 0)
                            WHERE("childCategory=#{childCategory}");
                        break;
                }

                switch (filterInfo.getDisease()) {
                    case 0:
                        break;
                    default:
                        WHERE("disease=#{disease}");
                        break;
                }

                switch (filterInfo.getDistance()) {
                    case 0:
                        break;
                    default:
                        WHERE("distance<=#{distance}*2-1");
                        break;
                }

                switch (filterInfo.getSort()) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        ORDER_BY("distance");
                        break;
                }

            }

        }.toString();
    }

    public String filterDoctor(final FilterInfo filterInfo) {
        return new SQL() {
            {
                SELECT("doctor.uid,nikeName,avator,star,qualificate,favorRate,cmtCount,expert,label,hospital,distance");
                FROM("search");
                LEFT_OUTER_JOIN("doctor on search.doctorId = doctor.uid", "user on search.doctorId=user.uid ");

                WHERE("category=1");
                switch (filterInfo.getCategory()) {
                    case 0:
                        break;
                    default:
                        if (filterInfo.getChildCategory() != 0)
                            WHERE("childCategory=#{childCategory}");
                        break;
                }
                switch (filterInfo.getDisease()) {
                    case 0:
                        break;
                    default:
                        WHERE("disease=#{disease}");
                        break;
                }

                switch (filterInfo.getDistance()) {
                    case 0:
                        break;
                    default:
                        WHERE("distance<=#{distance}*2-1");
                        break;
                }

                switch (filterInfo.getSort()) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        ORDER_BY("distance");
                        break;
                }
            }

        }.toString();
    }
}
