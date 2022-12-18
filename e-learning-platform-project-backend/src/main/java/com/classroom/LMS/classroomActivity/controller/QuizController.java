package com.classroom.LMS.classroomActivity.controller;

import com.classroom.LMS.classroomActivity.entity.Quiz;
import com.classroom.LMS.classroomActivity.entity.QuizAttempt;
import com.classroom.LMS.classroomActivity.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public Quiz createQuiz(@RequestBody Quiz quiz) throws Exception{
        return this.quizService.createQuiz(quiz);
    };

    @PutMapping("edit")
    public Quiz updateQuiz(@RequestBody Quiz quiz) throws Exception{
        return this.quizService.editQuiz(quiz);
    }

    @GetMapping("{quizId}")
    public Quiz getQuizById(@PathVariable Long quizId) throws Exception{
        return this.quizService.getQuizById(quizId);
    }

    @PostMapping("evaluatequiz")
    public QuizAttempt evaluateQuiz(@RequestBody QuizAttempt attempt) throws Exception{
        return this.quizService.evaluateQuiz(attempt);
    };

    @DeleteMapping("delete/{quizId}")
    public String deleteNoticeById(@PathVariable Long quizId) throws Exception{
        return this.quizService.deleteQuizById(quizId);
    }
}
