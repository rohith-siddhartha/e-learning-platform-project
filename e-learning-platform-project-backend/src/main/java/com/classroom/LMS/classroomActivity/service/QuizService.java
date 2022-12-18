package com.classroom.LMS.classroomActivity.service;

import com.classroom.LMS.classroomActivity.entity.Notice;
import com.classroom.LMS.classroomActivity.entity.Quiz;
import com.classroom.LMS.classroomActivity.entity.QuizAttempt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface QuizService {

    public Quiz createQuiz(Quiz quiz) throws Exception;
    public Quiz editQuiz(Quiz quiz) throws Exception;
    public Quiz getQuizById(Long quizId) throws Exception;
    public String deleteQuizById(Long quizId) throws Exception;

    public QuizAttempt evaluateQuiz(QuizAttempt attempt) throws Exception;
}
