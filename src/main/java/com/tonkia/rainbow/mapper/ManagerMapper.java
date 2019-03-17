package com.tonkia.rainbow.mapper;

import com.tonkia.rainbow.pojo.BannerInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface ManagerMapper {

    @Insert("insert into banner(img,url) values(#{img},#{url}) ")
    public int insertBannerInfo(BannerInfo bannerInfo);

    @Delete("delete from banner where id = #{0}")
    public int delBannerById(int id);
}
