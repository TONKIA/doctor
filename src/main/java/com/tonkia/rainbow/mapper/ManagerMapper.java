package com.tonkia.rainbow.mapper;

import com.tonkia.rainbow.pojo.BannerInfo;
import com.tonkia.rainbow.pojo.TipInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface ManagerMapper {

    @Insert("insert into banner(img,url) values(#{img},#{url}) ")
    public int insertBannerInfo(BannerInfo bannerInfo);

    @Delete("delete from banner where id = #{0}")
    public int delBannerById(int id);

    @Update("update article set state = 2 where articleId= #{0}")
    public int updateCancelState(int id);

    @Update("update article set state = 1 where articleId= #{0}")
    public int updateRecommandState(int id);

    @Update("update article set state = 0 where articleId= #{0}")
    public int updateTopState(int id);

    @Insert("insert into tip(title,picUrl,content,summary) values (#{title},#{picUrl},#{content},#{summary})")
    public int insertTip(TipInfo tipInfo);

    @Delete("delete from tip  where tipId = #{0}")
    int delTip(int id);
}
