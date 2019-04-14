package com.tonkia.rainbow.mapper;

import com.tonkia.rainbow.pojo.BannerInfo;
import com.tonkia.rainbow.pojo.CmtInfo;
import com.tonkia.rainbow.pojo.SayingCmt;
import com.tonkia.rainbow.pojo.SayingInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PsychologyMapper {
    @Select("select * from saying where category = #{0} order by id desc")
    List<SayingInfo> getAllSaying(Integer category);

    @Insert("insert into saying(content,imgUrl,category,uid,time) values(#{content},#{imgUrl},#{category},#{uid},#{time})")
    int insertSaying(SayingInfo sayingInfo);

    @Select("select * from banner where type = 1")
    List<BannerInfo> getBanner();

    @Select("select * from saying where id = #{0}")
    SayingInfo getSayingInfo(Integer sayingId);

    @Select("select count(*) from saying_comment where sayingId=#{0}")
    Integer getCmtCount(Integer sayingId);

    @Select("select count(*) from saying_thumbup where sayingId=#{0}")
    Integer getPraiseCount(Integer sayingId);

    @Select("select sayingId,saying_comment.uid,avator,nikeName,content,time,id from saying_comment left join user on user.uid= saying_comment.uid where sayingId=#{0} order by id desc")
    List<CmtInfo> getCmtInfo(Integer sayingId);

    @Select("select count(*) from saying_thumbup where sayingId=#{sayingId} and uid=#{uid} ")
    int isThumbup(Map map);

    @Insert("insert into saying_thumbup(sayingId,uid) values(#{sayingId},#{uid})")
    Integer insertThumbup(Map map);

    @Insert("insert into saying_comment(sayingId,uid,content,time) values(#{sayingId},#{uid},#{content},#{time})")
    boolean insertCmt(SayingCmt sayingCmt);
}
