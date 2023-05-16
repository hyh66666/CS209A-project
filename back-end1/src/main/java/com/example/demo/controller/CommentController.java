package com.example.demo.controller;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
}
