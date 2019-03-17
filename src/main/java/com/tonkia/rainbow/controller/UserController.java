package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.ResponseMessage;
import com.tonkia.rainbow.pojo.UserInfo;
import com.tonkia.rainbow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * get请求
     * 格式 /user/getSmsForLogin/{phoneNumber}
     * 在数据库中注册用户（如果不存在该手机号）并且发出短信验证码
     * 如果存在就直接发出短信验证码
     * 更新数据库中短信验证码 并设置过期时间
     */
    @RequestMapping("/getSmsForLogin/{phoneNumber}")
    @ResponseBody
    public ResponseMessage getSmsForLogin(@PathVariable("phoneNumber") String phoneNumber) {
        ResponseMessage responseMessage;
        if (userService.insertSmsForLogin(phoneNumber))
            //获取到code
            responseMessage = new ResponseMessage("验证短信获取成功！", null, ResponseMessage.SUCCESS);
        else
            //获取失败
            responseMessage = new ResponseMessage("验证短信获取失败！", null, ResponseMessage.FAILURE);
        return responseMessage;
    }

    /**
     * post请求
     * 格式 /user/loginWithSms
     * {
     * phoneNumber：xxx
     * smsCode：xxx
     * }
     */
    @RequestMapping("/loginWithSms")
    @ResponseBody
    public ResponseMessage loginWithSms(String phoneNumber, String smsCode) {
        ResponseMessage responseMessage;
        String token = userService.insertToken(phoneNumber, smsCode);
        if (token == null)
            responseMessage = new ResponseMessage("登录失败！", null, ResponseMessage.FAILURE);
        else {
            Map data = new HashMap();
            data.put("token", token);
            responseMessage = new ResponseMessage("登录成功！", data, ResponseMessage.SUCCESS);
        }
        return responseMessage;
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseMessage login(String phoneNumber, String token, HttpSession session) {
        ResponseMessage responseMessage;
        UserInfo userInfo = userService.login(phoneNumber, token);
        session.setAttribute("user", userInfo);
        if (userInfo == null)
            responseMessage = new ResponseMessage("登录失败！", null, ResponseMessage.FAILURE);
        else {
            responseMessage = new ResponseMessage("登录成功！", null, ResponseMessage.SUCCESS);
        }
        return responseMessage;
    }

}
