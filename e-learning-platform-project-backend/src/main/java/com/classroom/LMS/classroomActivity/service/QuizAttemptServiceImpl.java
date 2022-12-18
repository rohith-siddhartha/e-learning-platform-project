package com.classroom.LMS.classroomActivity.service;

import com.classroom.LMS.classroomActivity.entity.QuizAttempt;
import com.classroom.LMS.classroomActivity.repository.QuizAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizAttemptServiceImpl implements QuizAttemptService {

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    public QuizAttemptServiceImpl(QuizAttemptRepository quizAttemptRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
    }

    @Override
    public QuizAttempt create(QuizAttempt attempt) throws Exception {
        return quizAttemptRepository.save(attempt);
    }

    @Override
    public QuizAttempt edit(QuizAttempt attempt) throws Exception {
        return null;
    }

    @Override
    public QuizAttempt getById(Long attemptId) throws Exception {
        return quizAttemptRepository.findById(attemptId).get();
    }

    @Override
    public String deleteById(Long attemptId) throws Exception {
        return null;
    }
}
