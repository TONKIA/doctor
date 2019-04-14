package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.BannerInfo;
import com.tonkia.rainbow.pojo.ResponseMessage;
import com.tonkia.rainbow.pojo.TipInfo;
import com.tonkia.rainbow.service.FileService;
import com.tonkia.rainbow.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("mgr")
public class ManagerController {
    @Autowired
    FileService fileService;
    @Autowired
    ManagerService managerService;

    @RequestMapping("getBanner")
    @ResponseBody
    public ResponseMessage getBanner() {
        List<BannerInfo> list = managerService.getBanner();
        return new ResponseMessage("获取Banner成功", list, ResponseMessage.SUCCESS);
    }

    @RequestMapping("uploadBanner")
    @ResponseBody
    public ResponseMessage uploadBanner(MultipartFile file, String url, Integer type) {
        boolean res = false;
        try {
            String img = fileService.uploadImg(file.getOriginalFilename(), file.getInputStream());
            res = managerService.insertBanner(new BannerInfo(img, url, type));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (res)
            return new ResponseMessage("添加Banner生效！", null, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("添加Banner失败！", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("delBanner/{id}")
    @ResponseBody
    public ResponseMessage delBanner(@PathVariable("id") int id) {
        boolean res = managerService.deleteBanner(id);
        if (res)
            return new ResponseMessage("删除Banner生效！", null, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("删除Banner失败！", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("cancelTop/{id}")
    @ResponseBody
    public ResponseMessage cancelTop(@PathVariable("id") int id) {
        boolean res = managerService.updateCancelState(id);
        if (res)
            return new ResponseMessage("取消置顶生效！", null, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("取消置顶失败！", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("cancelRecommand/{id}")
    @ResponseBody
    public ResponseMessage cancelRecommand(@PathVariable("id") int id) {
        boolean res = managerService.updateCancelState(id);
        if (res)
            return new ResponseMessage("取消推荐生效！", null, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("取消推荐失败！", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("setTop/{id}")
    @ResponseBody
    public ResponseMessage setTop(@PathVariable("id") int id) {
        boolean res = managerService.updateTopState(id);
        if (res)
            return new ResponseMessage("置顶生效！", null, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("置顶失败！", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("setRecommand/{id}")
    @ResponseBody
    public ResponseMessage getBanner(@PathVariable("id") int id) {
        boolean res = managerService.updateRecommandState(id);
        if (res)
            return new ResponseMessage("推荐生效！", null, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("推荐失败！", null, ResponseMessage.FAILURE);
    }

    @RequestMapping("addTip")
    @ResponseBody
    public ResponseMessage addTip(TipInfo tipInfo, MultipartFile file) {
        String url = null;
        try {
            url = fileService.uploadImg(file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        tipInfo.setPicUrl(url);
        boolean res = managerService.insertTip(tipInfo);
        if (res)
            return new ResponseMessage("添加小知识成功！", null, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("添加小知识失败！", null, ResponseMessage.FAILURE);
    }


    @RequestMapping("delTip/{id}")
    @ResponseBody
    public ResponseMessage delTip(@PathVariable("id") int id) {
        boolean res = managerService.deleteTip(id);
        if (res)
            return new ResponseMessage("删除小知识生效！", null, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("删除小知识失败！", null, ResponseMessage.FAILURE);

    }
}
