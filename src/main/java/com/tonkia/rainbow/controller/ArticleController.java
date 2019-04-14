package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.CmtInfo;
import com.tonkia.rainbow.pojo.ResponseMessage;
import com.tonkia.rainbow.pojo.UserInfo;
import com.tonkia.rainbow.service.ArticleService;
import com.tonkia.rainbow.service.DoctorService;
import com.tonkia.rainbow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @Autowired
    DoctorService doctorService;

    //    获取文章页面
    @RequestMapping("/{articleId}")
    public String show(@PathVariable("articleId") Integer articleId, Model model, HttpServletRequest request, String phoneNumber, String token) {
        if (phoneNumber != null && token != null) {
            UserInfo userInfo = userService.login(phoneNumber, token);
            request.getSession().setAttribute("user", userInfo);
        }
        ArticleInfo articleInfo = articleService.getArticleById(articleId);
        model.addAttribute("articleInfo", articleInfo);
        return "/passage.jsp";

    }

    @RequestMapping("/isThumbup")
    @ResponseBody
    public ResponseMessage isThumbup(HttpSession session, String phoneNumber, String token, Integer articleId) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        if (userInfo != null) {
            boolean isThumbup = articleService.isThumbupBySession(userInfo.getUid(), articleId);
            Map res = new HashMap<>();
            res.put("isThumbup", isThumbup);
            return new ResponseMessage("成功返回", res, ResponseMessage.SUCCESS);
        } else {
            boolean isThumbup = articleService.isThumbup(phoneNumber, token, articleId);
            Map res = new HashMap<>();
            res.put("isThumbup", isThumbup);
            return new ResponseMessage("成功返回", res, ResponseMessage.SUCCESS);
        }
    }

    @RequestMapping("/thumbup")
    @ResponseBody
    public ResponseMessage thumbup(String phoneNumber, String token, Integer articleId, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        if (userInfo != null) {
            if (articleService.insertSessionThumbup(userInfo.getUid(), articleId))
                return new ResponseMessage("点赞成功", null, ResponseMessage.SUCCESS);
            else
                return new ResponseMessage("点赞失败", null, ResponseMessage.FAILURE);
        } else {
            if (articleService.insertThumbup(phoneNumber, token, articleId))
                return new ResponseMessage("点赞成功", null, ResponseMessage.SUCCESS);
            else
                return new ResponseMessage("点赞失败", null, ResponseMessage.FAILURE);
        }
    }

    @RequestMapping("/getThumbCount/{articleId}")
    @ResponseBody
    public ResponseMessage thumbup(@PathVariable("articleId") Integer articleId) {
        int count = articleService.getThumbupCount(articleId);
        Map data = new HashMap();
        data.put("thumbCount", count);
        return new ResponseMessage("获取文章点赞数", data, ResponseMessage.SUCCESS);
    }

    @RequestMapping("/comment")
    @ResponseBody
    public ResponseMessage comment(String phoneNumber, String token, CmtInfo cmtInfo, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        if (userInfo != null) {
            cmtInfo.setUid(userInfo.getUid());
            if (articleService.insertSessionCmt(cmtInfo))
                return new ResponseMessage("发表评论成功", null, ResponseMessage.SUCCESS);
            else
                return new ResponseMessage("发表评论失败", null, ResponseMessage.FAILURE);
        } else {
            if (articleService.insertCmt(phoneNumber, token, cmtInfo))
                return new ResponseMessage("发表评论成功", null, ResponseMessage.SUCCESS);
            else
                return new ResponseMessage("发表评论失败", null, ResponseMessage.FAILURE);
        }
    }

    @RequestMapping("/comment/{articleId}")
    @ResponseBody
    public ResponseMessage getComment(@PathVariable("articleId") Integer articleId) {
        List<CmtInfo> data = articleService.getComment(articleId);
        return new ResponseMessage("获取评论成功", data, ResponseMessage.SUCCESS);
    }

    @RequestMapping("/getFocusCount/{doctorId}")
    @ResponseBody
    public ResponseMessage getFocusCount(@PathVariable("doctorId") Integer doctorId) {

        int count = doctorService.getFocusCount(doctorId);
        Map data = new HashMap();
        data.put("focusCount", count);
        return new ResponseMessage("获取医生的关注数目成功！", data, ResponseMessage.SUCCESS);
    }

    @RequestMapping("/reward/{rank}")
    @ResponseBody
    public ResponseMessage reward(HttpServletRequest request, String phoneNumber, String token, @PathVariable("rank") Integer rank, Integer articleId) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        if (userInfo == null)
            userInfo = userService.login(phoneNumber, token);
        if (userInfo != null) {
            if (articleService.insertReward(userInfo.getUid(), articleId, rank)) {
                return new ResponseMessage("打赏成功！", null, ResponseMessage.SUCCESS);
            } else {
                return new ResponseMessage("打赏失败！", null, ResponseMessage.FAILURE);
            }
        } else {
            return new ResponseMessage("用户未登录", null, ResponseMessage.FAILURE);
        }

    }

    @RequestMapping("/getReward")
    @ResponseBody
    public ResponseMessage getReward(Integer articleId) {
        List<String> list = articleService.getReward(articleId);
        return new ResponseMessage("打赏头像返回成功！", list, ResponseMessage.SUCCESS);
    }
}
