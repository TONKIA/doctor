package com.tonkia.rainbow.mapper;

import com.tonkia.rainbow.pojo.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Set;

public interface UserMapper {
    @Select("select * from user where phoneNumber=#{0}")
    public UserInfo getUserInfoByPhoneNumber(String phoneNumber);

    @Insert("insert into user(phoneNumber,smsCode,expire,nikeName) values(#{phoneNumber},#{smsCode},#{expire},#{nikeName})")
    public void insertUserInfo(UserInfo userInfo);

    @Update("update user set smsCode=#{smsCode}, expire=#{expire} where uid=#{uid}")
    public int updateSmsCode(UserInfo userInfo);

    @Select("select * from user where phoneNumber=#{phoneNumber} and smsCode=#{smsCode}")
    public UserInfo selectUserInfoByPhoneNumberAndSmsCode(UserInfo userInfo);

    @Update("update user set token=#{token} where phoneNumber= #{phoneNumber}")
    public int updateTokenByPhoneNumber(UserInfo userInfo);

    @Select("select * from user where phoneNumber=#{phoneNumber} and token=#{token}")
    UserInfo getUserInfoByLogin(UserInfo userInfo);

    @Select("select nikeName, avator from user where uid=#{0}")
    UserInfo getUserInfo(Integer uid);

    @Select("select count(*) from focus where uid=#{0}")
    int getFocus(Integer uid);


    @Select("select count(*) from article_thumbup where uid=#{0}")
    Integer getThumbCount(Integer uid);

    @Select("select count(*) from article_comment where uid=#{0}")
    Integer getCmt(Integer uid);

    @Update("update user set avator=#{avator} where uid = #{uid}")
    boolean updateAvator(UserInfo userInfo);

    @Update("update user set nikeName=#{nikeName} where uid = #{uid}")
    boolean updateNikeName(UserInfo userInfo);
}
