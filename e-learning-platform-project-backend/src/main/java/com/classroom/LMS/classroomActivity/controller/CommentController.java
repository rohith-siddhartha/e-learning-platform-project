package com.classroom.LMS.classroomActivity.controller;

import com.classroom.LMS.classroomActivity.entity.Comment;
import com.classroom.LMS.classroomActivity.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {


    @Autowired
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("create")
    public Comment create(@RequestBody Comment comment) throws Exception {
        return this.commentService.create(comment);
    }

}
