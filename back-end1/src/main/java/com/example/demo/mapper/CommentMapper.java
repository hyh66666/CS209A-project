package com.example.demo.mapper;

import com.example.demo.enity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO \"comment\" (id, edited, score, \"creationDate\", \"postId\", \"commentId\", \"contentLicense\", \"userId\")" +
            " VALUES (DEFAULT, #{edited}, #{score},#{creationDate},#{postId},#{commentId},#{contentLicense},#{userId})")
    public int insert(Comment comment);
}
