package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.DoctorInfo;
import com.tonkia.rainbow.pojo.ResponseMessage;
import com.tonkia.rainbow.pojo.UserInfo;
import com.tonkia.rainbow.service.DoctorService;
import com.tonkia.rainbow.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    FileService fileService;

    @RequestMapping("/delArticle/{id}")
    @ResponseBody
    public ResponseMessage delArticle(HttpSession session, @PathVariable("id") Integer id) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if (userInfo == null)
            return new ResponseMessage("用户未登录", null, ResponseMessage.FAILURE);

        if (doctorService.deleteArticle(userInfo.getUid(), id))
            return new ResponseMessage("添加新文章成功", null, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("添加新文章失败", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("/addArticle")
    @ResponseBody
    public ResponseMessage addArticle(HttpSession session, MultipartFile file, ArticleInfo articleInfo) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");

        if (userInfo == null)
            return new ResponseMessage("用户未登录", null, ResponseMessage.FAILURE);
        //添加文章
        String url = null;
        try {
            url = fileService.uploadImg(file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        articleInfo.setPicUrl(url);
        articleInfo.setUid(userInfo.getUid());

        if (doctorService.insertArticle(articleInfo))
            return new ResponseMessage("添加新文章成功", null, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("添加新文章失败", null, ResponseMessage.FAILURE);
    }


    @RequestMapping("/getAllArticle")
    @ResponseBody
    public ResponseMessage getAllArticle(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if (userInfo == null)
            return new ResponseMessage("用户未登录", null, ResponseMessage.FAILURE);
        List<ArticleInfo> articleInfos = doctorService.getAllArticleByUid(userInfo.getUid());
        if (articleInfos != null)
            return new ResponseMessage("用户文章获取成功", articleInfos, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("用户文章获取失败", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("/getDoctorInfo")
    @ResponseBody
    public ResponseMessage getDoctorInfo(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if (userInfo == null)
            return new ResponseMessage("用户未登录", null, ResponseMessage.FAILURE);
        DoctorInfo doctorInfo = doctorService.getDoctorInfoByUid(userInfo.getUid());
        if (doctorInfo != null)
            return new ResponseMessage("用户信息获取成功", doctorInfo, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("用户信息获取失败", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("/getDoctorInfoById/{uid}")
    @ResponseBody
    public ResponseMessage getDoctorInfoById(@PathVariable("uid") Integer uid) {
        DoctorInfo doctorInfo = doctorService.getDoctorInfoById(uid);
        if (doctorInfo != null)
            return new ResponseMessage("获取成功", doctorInfo, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("获取失败", null, ResponseMessage.FAILURE);
    }


    @RequestMapping("/registryDoctor")
    @ResponseBody
    public ResponseMessage registryDoctor(HttpSession session, String expert, String qualificate, String hospital) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if (userInfo == null)
            return new ResponseMessage("用户未登录", null, ResponseMessage.FAILURE);

        DoctorInfo doctorInfo = new DoctorInfo();
        doctorInfo.setUid(userInfo.getUid());
        doctorInfo.setExpert(expert);
        doctorInfo.setQualificate(qualificate);
        doctorInfo.setHospital(hospital);
        doctorInfo.setLabel("初来驾到");

        doctorInfo = doctorService.insertDoctorInfo(doctorInfo);
        if (doctorInfo != null)
            return new ResponseMessage("用户信息注册成功", doctorInfo, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("用户信息注册失败", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("/thumbup")
    @ResponseBody
    public ResponseMessage thumbup(String phoneNumber, String token, Integer doctorId) {
        if (doctorService.insertThumbup(phoneNumber, token, doctorId))
            return new ResponseMessage("点赞成功", null, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("点赞失败", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("/thumbdown")
    @ResponseBody
    public ResponseMessage thumbdown(String phoneNumber, String token, Integer doctorId) {
        if (doctorService.insertThumbdown(phoneNumber, token, doctorId))
            return new ResponseMessage("点赞成功", null, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("点赞失败", null, ResponseMessage.FAILURE);
    }

}
