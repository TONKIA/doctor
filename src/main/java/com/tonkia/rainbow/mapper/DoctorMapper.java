package com.tonkia.rainbow.mapper;

import com.tonkia.rainbow.pojo.ArticleInfo;
import com.tonkia.rainbow.pojo.CmtInfo;
import com.tonkia.rainbow.pojo.DoctorCmtInfo;
import com.tonkia.rainbow.pojo.DoctorInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select("SELECT (SELECT COUNT(*) FROM thumbup WHERE TYPE=1 AND doctorId = #{0})/(SELECT COUNT(*) FROM thumbup WHERE doctorId = #{0})*100 FROM DUAL")
    Float getDoctorFavorRateByUid(Integer uid);

    @Update("update doctor set expert = #{expert} ,hospital = #{hospital}, qualificate= #{qualificate} where uid = #{uid}")
    int updateDoctorInfo(DoctorInfo doctorInfo);

    @Update("update user set nikeName = #{nikeName}, avator = #{avator} where uid = #{uid}")
    int updateUserInfo(DoctorInfo doctorInfo);

    @Select("select count(*) from focus where uid=#{uid} and doctorUid=#{doctorId}")
    Integer isFocus(Map map);

    @Insert("insert into focus(uid,doctorUid) values(#{uid},#{doctorId})")
    boolean insertFocus(Map map);

    @Insert("insert into doctor_comment(doctorId,uid,content,time) values(#{doctorId},#{uid},#{content},#{time})")
    boolean insertCmt(DoctorCmtInfo cmtInfo);

    @Select("select id,user.uid uid,doctorId,content,time,nikeName,avator from doctor_comment LEFT JOIN user ON doctor_comment.uid = user.uid where doctorId = #{0} order by time desc")
    List<CmtInfo> getComment(Integer doctorId);

    @Select("select count(*) from thumbup where doctorId = #{0}")
    int getPraiseCount(Integer uid);

    @Select("select count(*) from doctor_comment where doctorId = #{0}")
    int getCmtCountByUid(Integer uid);

    @Select("select count(*) from focus where doctorUid = #{0}")
    int getFocusCount(Integer doctorId);

    @Delete("delete from focus where uid = #{uid} and doctorUid=#{doctorUid}")
    boolean deleteFocus(Map date);
}
