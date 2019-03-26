package com.tonkia.rainbow.controller;

import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/{articleId}")
    public String show(@PathVariable("articleId") Integer articleId, Model model, HttpServletRequest request) {
        ArticleInfo articleInfo = articleService.getArticleById(articleId);

        model.addAttribute("articleInfo", articleInfo);

        return "/passage.jsp";
    }
}
