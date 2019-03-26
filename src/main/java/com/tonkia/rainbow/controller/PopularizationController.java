package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.ResponseMessage;
import com.tonkia.rainbow.service.PopularizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("popularization")
public class PopularizationController {
    @Autowired
    PopularizationService popularizationService;

    @RequestMapping("getTop")
    @ResponseBody
    public ResponseMessage getTop() {
        List<ArticleInfo> data = popularizationService.getTop();
        if (data != null)
            return new ResponseMessage("返回置顶成功", data, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("返回置顶失败", data, ResponseMessage.FAILURE);
    }

    //0关注 1 推荐 2 情感 3疾病 4辟谣 5美容
    //0 置顶 1 推荐
    @RequestMapping("getArticle/{category}")
    @ResponseBody
    public ResponseMessage getScience(@PathVariable("category") int category, String phoneNumber, String token) {
        List<ArticleInfo> data = null;
        if (category == 0) {
            if (phoneNumber == null || token == null)
                return new ResponseMessage("返回文章失败,请先登录", null, ResponseMessage.FAILURE);
            //获取关注
            data = popularizationService.getFocus(phoneNumber, token);
        } else if (category == 1) {
            data = popularizationService.getRecommand();
        } else {
            data = popularizationService.getCategory(category);
        }

        if (data != null)
            return new ResponseMessage("返回文章成功", data, ResponseMessage.SUCCESS);
        else
            return new ResponseMessage("返回文章失败", data, ResponseMessage.FAILURE);
    }
}
