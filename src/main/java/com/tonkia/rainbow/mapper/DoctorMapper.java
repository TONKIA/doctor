package com.tonkia.rainbow.mapper;

import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.DoctorInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface DoctorMapper {
    @Select("SELECT doctor.uid, nikeName, user.avator, star, qualificate, favorRate, cmtCount, expert, label,hospital FROM doctor LEFT JOIN user ON doctor.uid = user.uid where doctor.uid=#{0}")
    DoctorInfo getDoctorInfoByUid(Integer uid);

    @Insert("insert into doctor(uid,qualificate,expert,label,hospital) values(#{uid},#{qualificate},#{expert},#{label},#{hospital})")
    int insertDoctorInfo(DoctorInfo doctorInfo);

    @Select("SELECT articleId,article.uid,nikeName,avator,title,summary,cmtCount,praise,picUrl,articleUrl,content FROM article LEFT JOIN user ON article.uid = user.uid where user.uid=#{0}")
    List<ArticleInfo> getAllArticleByUid(int uid);

    @Insert("insert into article(uid,title,summary,picUrl,content,category) values(#{uid},#{title},#{summary},#{picUrl},#{content},#{category})")
    boolean insertArticle(ArticleInfo articleInfo);

    @Delete("delete from article where uid =#{uid} and articleId = #{articleId}")
    boolean deleteArticle(Map map);

    @Select("SELECT doctor.uid, nikeName, user.avator, star, qualificate, favorRate, cmtCount, expert, label,hospital FROM doctor LEFT JOIN user ON doctor.uid = user.uid where doctor.uid=#{0}")
    DoctorInfo getDoctorInfoById(Integer uid);

    @Insert("insert into thumbup(uid,doctorId) values(#{uid},#{doctorId})")
    boolean insertThumbup(Map map);

    @Insert("insert into thumbup(uid,doctorId,type) values(#{uid},#{doctorId},0)")
    boolean insertThumbdown(Map map);

    @Select("SELECT (SELECT COUNT(*) FROM thumbup WHERE TYPE=1 AND doctorId = #{0})/(SELECT COUNT(*) FROM thumbup WHERE doctorId = #{0})FROM DUAL")
    Float getDoctorFavorRateByUid(Integer uid);
}
