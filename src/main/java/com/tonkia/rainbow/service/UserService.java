package com.tonkia.rainbow.service;

import com.tonkia.rainbow.pojo.UserInfo;

import java.util.Set;

public interface UserService {
    public boolean insertSmsForLogin(String phoneNumber);

    public String insertToken(String phoneNumber, String smsCode);

    public UserInfo login(String phoneNumber, String token);
}
