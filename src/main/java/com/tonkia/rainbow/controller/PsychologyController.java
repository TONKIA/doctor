package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.*;
import com.tonkia.rainbow.service.FileService;
import com.tonkia.rainbow.service.PsychologyService;
import com.tonkia.rainbow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("psychology")
@Controller
public class PsychologyController {

    @Autowired
    PsychologyService psychologyService;
    @Autowired
    UserService userService;
    @Autowired
    FileService fileService;

    @RequestMapping("getBanner")
    @ResponseBody
    public ResponseMessage getBanner() {
        List<BannerInfo> list = psychologyService.getBanner();
        return new ResponseMessage("获取Banner成功", list, ResponseMessage.SUCCESS);
    }

    //0 我的恋爱
    //1 我的治疗
    //2 我的求助
    //3 我的安利

    @RequestMapping("publish")
    @ResponseBody
    public ResponseMessage psychology(String phoneNumber, String token, SayingInfo sayingInfo, MultipartFile file) {
        UserInfo userInfo = userService.login(phoneNumber, token);
        if (userInfo != null) {
            sayingInfo.setUid(userInfo.getUid());
            if (psychologyService.insertSaying(sayingInfo))
                return new ResponseMessage("发布成功", null, ResponseMessage.SUCCESS);
            return new ResponseMessage("发布失败", null, ResponseMessage.FAILURE);
        }
        return new ResponseMessage("用户未登录", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("getAllSaying/{category}")
    @ResponseBody
    public ResponseMessage getAllSaying(@PathVariable("category") Integer category) {
        List<SayingInfo> list = psychologyService.getAllSaying(category);
        return new ResponseMessage("返回成功", list, ResponseMessage.SUCCESS);
    }

    @RequestMapping("getSaying/{sayingId}")
    @ResponseBody
    public ResponseMessage getSaying(@PathVariable("sayingId") Integer sayingId) {
        SayingInfo sayingInfo = psychologyService.getSayingInfo(sayingId);
        return new ResponseMessage("返回成功", sayingInfo, ResponseMessage.SUCCESS);
    }

    @RequestMapping("comment")
    @ResponseBody
    public ResponseMessage comment(String phoneNumber, String token, SayingCmt sayingCmt) {
        SayingInfo sayingInfo = psychologyService.insertCmt(phoneNumber, token, sayingCmt);
        if (sayingInfo != null)
            return new ResponseMessage("发表评论成功", sayingInfo, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("发表评论失败", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("thumbup")
    @ResponseBody
    public ResponseMessage thumbup(String phoneNumber, String token, Integer sayingId) {
        Integer praise = psychologyService.insertThumbup(phoneNumber, token, sayingId);
        if (praise != null) {
            Map data = new HashMap();
            data.put("praiseCount",praise);
            return new ResponseMessage("点赞成功", data, ResponseMessage.SUCCESS);
        } else
            return new ResponseMessage("点赞失败", null, ResponseMessage.FAILURE);
    }
}