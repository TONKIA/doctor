package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.BannerInfo;
import com.tonkia.rainbow.pojo.ResponseMessage;
import com.tonkia.rainbow.service.FileService;
import com.tonkia.rainbow.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("mgr")
public class ManagerController {
    @Autowired
    FileService fileService;
    @Autowired
    ManagerService managerService;


    @RequestMapping("uploadBanner")
    @ResponseBody
    public ResponseMessage uploadBanner(MultipartFile file, String url) {
        boolean res = false;
        try {
            String img = fileService.uploadImg(file.getOriginalFilename(), file.getInputStream());
            res = managerService.insertBanner(new BannerInfo(img, url));
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
}
