package com.example.demo.mapper;

import com.example.demo.enity.Answer;
import com.example.demo.enity.Owner;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnswerMapper {

    @Select("select * from \"answer\"")
    public List<Answer> findAllAnswer();

    @Select("select * from \"answer\" where \"answerId\" = #{iii}")
    public List<Answer> judge(String iii);

    @Select("select * from answer where \"questionId\" = #{question_id}")
    public List<Answer> findAnswerByQuestion_id(String question_id);

    @Select("select * from answer where \"isAccepted\" = 'true';")
    public List<Answer> findAnswerAccepted();

    @Insert("INSERT INTO \"answer\" (id, \"isAccepted\", score, \"lastActivityDate\", \"creationDate\", \"answerId\", " +
            "\"questionId\", \"contentLicense\", \"userId\") VALUES (DEFAULT, #{isAccepted}, #{score},#{lastActivityDate}," +
            "#{creationDate},#{answerId},#{questionId}, #{contentLicense}, #{userId})")
    public int insert(Answer answer);

}
