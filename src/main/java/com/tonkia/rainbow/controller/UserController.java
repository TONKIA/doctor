package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.ResponseMessage;
import com.tonkia.rainbow.pojo.UserInfo;
import com.tonkia.rainbow.service.FileService;
import com.tonkia.rainbow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    FileService fileService;

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
            UserInfo userInfo = userService.login(phoneNumber, token);
            Map data = new HashMap();
            data.put("token", token);
            data.put("uid", userInfo.getUid());
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
            Map data = new HashMap();
            data.put("uid", userInfo.getUid());
            responseMessage = new ResponseMessage("登录成功！", data, ResponseMessage.SUCCESS);
        }
        return responseMessage;
    }

    @RequestMapping("/info/{uid}")
    @ResponseBody
    public ResponseMessage info(@PathVariable("uid") Integer uid) {
        UserInfo userInfo = userService.getInfo(uid);
        if (userInfo == null)
            return new ResponseMessage("获取失败！", null, ResponseMessage.FAILURE);
        else {
            return new ResponseMessage("获取成功！", userInfo, ResponseMessage.SUCCESS);
        }
    }

    @RequestMapping("/detailInfo")
    @ResponseBody
    public ResponseMessage detailInfo(String phoneNumber, String token) {
        UserInfo userInfo = userService.login(phoneNumber, token);
        if (userInfo == null)
            return new ResponseMessage("获取失败,请先登录！", null, ResponseMessage.FAILURE);
        else {
            Map data = new HashMap();
            data.put("userInfo", userService.getInfo(userInfo.getUid()));
            data.put("focus", userService.getFocus(userInfo.getUid()));
            data.put("cmtCount", userService.getCmtCount(userInfo.getUid()));
            data.put("thumbCount", userService.getThumbCount(userInfo.getUid()));
            return new ResponseMessage("获取成功！", data, ResponseMessage.SUCCESS);
        }
    }


    @RequestMapping("/modifyAvator")
    @ResponseBody
    public ResponseMessage modifyAvator(String phoneNumber, String token, MultipartFile file) {
        UserInfo userInfo = userService.login(phoneNumber, token);
        if (userInfo == null)
            return new ResponseMessage("获取失败,请先登录！", null, ResponseMessage.FAILURE);
        else {
            String url = null;
            try {
                url = fileService.uploadImg(file.getOriginalFilename(), file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            userInfo.setAvator(url);
            if (userService.updateAvator(userInfo))
                return new ResponseMessage("修改成功", null, ResponseMessage.SUCCESS);
            else
                return new ResponseMessage("修改失败", null, ResponseMessage.FAILURE);
        }
    }


    @RequestMapping("/modifyNikeName")
    @ResponseBody
    public ResponseMessage modifyNikeName(String phoneNumber, String token, String nikeName) {
        UserInfo userInfo = userService.login(phoneNumber, token);
        if (userInfo == null)
            return new ResponseMessage("获取失败,请先登录！", null, ResponseMessage.FAILURE);
        else {
            userInfo.setNikeName(nikeName);
            if (userService.updateNikeName(userInfo))
                return new ResponseMessage("修改成功", null, ResponseMessage.SUCCESS);
            else
                return new ResponseMessage("修改失败", null, ResponseMessage.FAILURE);
        }
    }

}
