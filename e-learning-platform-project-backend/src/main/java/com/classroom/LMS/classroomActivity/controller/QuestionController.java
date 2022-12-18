package com.classroom.LMS.classroomActivity.controller;

import com.classroom.LMS.classroomActivity.entity.Question;
import com.classroom.LMS.classroomActivity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("create")
    public Question createQuestion(@RequestBody Question question) throws Exception{
        return this.questionService.createQuestion(question);
    };

    @PutMapping("edit")
    public Question updateQuestion(@RequestBody Question question) throws Exception{
        return this.questionService.editQuestion(question);
    }

    @GetMapping("{QuestionId}")
    public Optional<Question> getQuestionById(@PathVariable Long QuestionId) throws Exception{
        return this.questionService.getQuestionById(QuestionId);
    }

    @DeleteMapping("delete/{QuestionId}")
    public String deleteQuestionById(@PathVariable Long questionId) throws Exception{
        return this.questionService.deleteQuestionById(questionId);
    }
}
