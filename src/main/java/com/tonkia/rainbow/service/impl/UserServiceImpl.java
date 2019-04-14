package com.tonkia.rainbow.service.impl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.tonkia.rainbow.mapper.UserMapper;
import com.tonkia.rainbow.pojo.UserInfo;
import com.tonkia.rainbow.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Value("${sms.codeLength}")
    private int smsCodeLength;
    @Value("${sms.smsExpireTime}")
    private long smsExpireTime;
    @Value("${sms.appid}")
    private int appid;
    @Value("${sms.appkey}")
    private String appkey;
    @Value("${sms.smsSign}")
    private String smsSign;
    @Value("${sms.templateId}")
    private int templateId;

    /**
     * 获取随机验证码
     *
     * @param length 验证码位数
     * @return
     */

    public String getRandomSmsCode(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append((int) (Math.floor(Math.random() * 10)));
        }
        return sb.toString();
    }

    /**
     * 发送验证码
     * 调用腾讯云接口
     *
     * @param phoneNumber
     * @param smsCode
     * @return
     */
    public boolean sendSmsCode(String phoneNumber, String smsCode) {
        try {
            //{1}为您的登录验证码，请于{2}分钟内填写。如非本人操作，请忽略本短信。
            String[] params = {smsCode, smsExpireTime / 60000 + ""};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    templateId, params, smsSign, "", "");
            if (result.result == 0)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 请求发送短信验证码
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public boolean insertSmsForLogin(String phoneNumber) {
        //获取用户信息
        UserInfo userInfo = userMapper.getUserInfoByPhoneNumber(phoneNumber);
        //如果用户不存在
        if (userInfo == null) {
            String smsCode = getRandomSmsCode(smsCodeLength);
            if (sendSmsCode(phoneNumber, smsCode)) {
                userInfo = new UserInfo();
                userInfo.setPhoneNumber(phoneNumber);
                userInfo.setNikeName("用户" + phoneNumber.substring(phoneNumber.length() - 4));
                userInfo.setSmsCode(smsCode);
                Date expire = new Date();
                expire.setTime(expire.getTime() + smsExpireTime);
                userInfo.setExpire(expire);
                userMapper.insertUserInfo(userInfo);
                return true;
            }
        } else {
            //如果用户存在 首先验证验证码是否过期
            if (userInfo.getExpire().before(new Date())) {
                String smsCode = getRandomSmsCode(smsCodeLength);
                if (sendSmsCode(phoneNumber, smsCode)) {
                    userInfo.setSmsCode(smsCode);
                    Date expire = new Date();
                    expire.setTime(expire.getTime() + smsExpireTime);
                    userInfo.setExpire(expire);
                    return userMapper.updateSmsCode(userInfo) > 0;
                }
            }
        }
        return false;
    }


    /**
     * 判断登录是否成功
     * 返回token
     *
     * @param phoneNumber
     * @param smsCode
     * @return 随机生成令牌返回
     */
    @Override
    public String insertToken(String phoneNumber, String smsCode) {
        String token = null;
        UserInfo userInfo = new UserInfo();
        userInfo.setPhoneNumber(phoneNumber);
        userInfo.setSmsCode(smsCode);
        userInfo = userMapper.selectUserInfoByPhoneNumberAndSmsCode(userInfo);
        if (userInfo != null && userInfo.getExpire().after(new Date())) {
            //生成token并且写到数据库中
            userInfo.setToken(UUID.randomUUID().toString().replace("-", ""));
            if (userMapper.updateTokenByPhoneNumber(userInfo) > 0)
                token = userInfo.getToken();
        }
        return token;
    }

    @Override
    public UserInfo login(String phoneNumber, String token) {
        UserInfo user = new UserInfo();
        user.setPhoneNumber(phoneNumber);
        user.setToken(token);
        return userMapper.getUserInfoByLogin(user);
    }

    @Override
    public UserInfo getInfo(Integer uid) {
        return userMapper.getUserInfo(uid);
    }


    @Override
    public Integer getFocus(Integer uid) {
        return userMapper.getFocus(uid);
    }

    @Override
    public Integer getCmtCount(Integer uid) {
        return userMapper.getCmt(uid);
    }

    @Override
    public Integer getThumbCount(Integer uid) {
        return userMapper.getThumbCount(uid);
    }

    @Override
    public boolean updateAvator(UserInfo userInfo) {
        return userMapper.updateAvator(userInfo);
    }

    @Override
    public boolean updateNikeName(UserInfo userInfo) {
        return userMapper.updateNikeName(userInfo);
    }

}
