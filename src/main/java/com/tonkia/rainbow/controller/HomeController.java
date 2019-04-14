package com.tonkia.rainbow.controller;


import com.tonkia.rainbow.pojo.*;
import com.tonkia.rainbow.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("home")
public class HomeController {
    @Autowired
    HomeService homeService;

    /**
     * 轮播图数据获取
     *
     * @return
     */
    @RequestMapping("getBanner")
    @ResponseBody
    public ResponseMessage getBanner() {
        List<BannerInfo> list = homeService.getBanner();
        return new ResponseMessage("获取Banner成功", list, ResponseMessage.SUCCESS);
    }

    /**
     * @param doctorNumber  获取几个医生
     * @param articleNumber 获取几篇文章
     * @return
     */
    @RequestMapping("getHome")
    @ResponseBody
    public ResponseMessage getHome(Integer doctorNumber, Integer articleNumber, Integer tipNumber) {
        if (doctorNumber == null)
            doctorNumber = 3;
        if (articleNumber == null)
            articleNumber = 3;
        if (tipNumber == null)
            tipNumber = 1;

        List<DoctorInfo> doctorInfos = homeService.getDoctor(doctorNumber);
        List<ArticleInfo> articleInfos = homeService.getArticle(articleNumber);
        List<TipInfo> tipInfos = homeService.getTip(tipNumber);

        Map data = new HashMap();
        //星级高的在前面
        data.put("doctor", doctorInfos);
        //最新的文章在前面
        data.put("article", articleInfos);
        //随机
        data.put("tip", tipInfos);
        return new ResponseMessage("首页加载数据成功", data, ResponseMessage.SUCCESS);
    }


    @RequestMapping("getAllTip")
    @ResponseBody
    public ResponseMessage getTip() {
        List<TipInfo> tipInfos = homeService.getAllTip();
        return new ResponseMessage("获取小知识成功", tipInfos, ResponseMessage.SUCCESS);
    }

}
