package com.classroom.LMS.classroomActivity.service;

import com.classroom.LMS.classroomActivity.entity.Question;
import com.classroom.LMS.classroomActivity.entity.QuizAttempt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface QuizAttemptService {

    public QuizAttempt create(QuizAttempt attempt) throws Exception;
    public QuizAttempt edit(QuizAttempt attempt) throws Exception;
    public QuizAttempt getById(Long attemptId) throws Exception;
    public String deleteById(Long attemptId) throws Exception;

}
