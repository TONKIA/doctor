package com.tonkia.rainbow.mapper;

import com.tonkia.rainbow.pojo.SayingInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PsychologyMapper {
    @Select("select * from saying where category = #{0}")
    List<SayingInfo> getAllSaying(Integer category);

    @Insert("insert into saying(content,imgUrl,category,uid) values(#{content},#{imgUrl},#{category},#{uid})")
    int insertSaying(SayingInfo sayingInfo);
}
