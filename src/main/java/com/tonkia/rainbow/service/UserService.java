package com.tonkia.rainbow.service;

import com.tonkia.rainbow.pojo.UserInfo;

import java.util.List;
import java.util.Set;

public interface UserService {
    public boolean insertSmsForLogin(String phoneNumber);

    public String insertToken(String phoneNumber, String smsCode);

    public UserInfo login(String phoneNumber, String token);

    UserInfo getInfo(Integer uid);

    Object getFocus(Integer uid);

    Object getCmtCount(Integer uid);

    Object getThumbCount(Integer uid);

    boolean updateAvator(UserInfo userInfo);

    boolean updateNikeName(UserInfo userInfo);

}
