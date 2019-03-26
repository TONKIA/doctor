package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.ResponseMessage;
import com.tonkia.rainbow.pojo.SayingInfo;
import com.tonkia.rainbow.pojo.UserInfo;
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
import java.util.List;

@RequestMapping("psychology")
@Controller
public class PsychologyController {

    @Autowired
    private PsychologyService psychologyService;
    @Autowired
    UserService userService;
    @Autowired
    FileService fileService;

    //0 我的恋爱
    //1 我的治疗
    //2 我的求助
    //3 我的安利

    @RequestMapping("getAllSaying/{category}")
    @ResponseBody
    public ResponseMessage getAllSaying(@PathVariable("category") Integer category) {
        List<SayingInfo> list = psychologyService.getAllSaying(category);
        return new ResponseMessage("返回成功", list, ResponseMessage.SUCCESS);
    }

    @RequestMapping("publish")
    @ResponseBody
    public ResponseMessage psychology(String phoneNumber, String token, SayingInfo sayingInfo, MultipartFile file) {
        UserInfo userInfo = userService.login(phoneNumber, token);
        if (userInfo != null) {
            String url = null;
            try {
                url = fileService.uploadImg(file.getOriginalFilename(), file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            sayingInfo.setUid(userInfo.getUid());
            sayingInfo.setImgUrl(url);
            if (psychologyService.insertSaying(sayingInfo))
                return new ResponseMessage("发布成功", null, ResponseMessage.SUCCESS);
            return new ResponseMessage("发布失败", null, ResponseMessage.FAILURE);
        }
        return new ResponseMessage("用户未登录", null, ResponseMessage.FAILURE);
    }
}
