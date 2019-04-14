package com.tonkia.rainbow.mapper;

import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.UserInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PopularizationMapper {
    @Select("SELECT  articleId,article.uid,nikeName,avator,title,summary,cmtCount,praise,picUrl,articleUrl,content FROM article LEFT JOIN user ON article.uid = user.uid where article.state=0 order by articleId desc")
    List<ArticleInfo> getTop();

    @Select("SELECT  articleId,article.uid,nikeName,avator,title,summary,cmtCount,praise,picUrl,articleUrl,content,category FROM article LEFT JOIN user ON article.uid = user.uid where article.uid in (select doctorUid from focus where uid = #{uid}) order by articleId desc")
    List<ArticleInfo> getFocus(UserInfo userInfo);

    @Select("SELECT  articleId,article.uid,nikeName,avator,title,summary,cmtCount,praise,picUrl,articleUrl,content,category FROM article LEFT JOIN user ON article.uid = user.uid where article.state = 1 order by articleId desc")
    List<ArticleInfo> getRecommand();

    @Select("SELECT  articleId,article.uid,nikeName,avator,title,summary,cmtCount,praise,picUrl,articleUrl,content,category FROM article LEFT JOIN user ON article.uid = user.uid where category = #{0} order by articleId desc")
    List<ArticleInfo> getCategory(int category);

    @Select("SELECT  articleId,article.uid,nikeName,avator,title,summary,cmtCount,praise,picUrl,articleUrl,content,category FROM article LEFT JOIN user ON article.uid = user.uid where title like CONCAT('%',#{0},'%') order by articleId desc")
    List<ArticleInfo> search(String keyword);
}
