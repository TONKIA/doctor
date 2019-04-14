package com.tonkia.rainbow.mapper;

import com.tonkia.rainbow.pojo.CalendarInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CalendarMapper {
    @Insert("insert into calendar(uid,time,content,mood) values(#{uid},#{time},#{content},#{mood})")
    boolean insertItem(CalendarInfo calendarInfo);

    @Select("select * from calendar where uid = #{0} order by id desc")
    List<CalendarInfo> getItem(Integer uid);
}
