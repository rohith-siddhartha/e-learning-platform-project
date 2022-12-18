package com.classroom.LMS.classroomActivity.service;

import com.classroom.LMS.classroomActivity.entity.Question;
import com.classroom.LMS.classroomActivity.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(Question question) throws Exception {
        return this.questionRepository.save(question);
    }

    @Override
    public Question editQuestion(Question question) throws Exception {
        return this.questionRepository.save(question);
    }

    @Override
    public Optional<Question> getQuestionById(Long questionId) throws Exception {
        return this.questionRepository.findById(questionId);
    }

    @Override
    public String deleteQuestionById(Long questionId) throws Exception {
        this.questionRepository.deleteById(questionId);
        return "deleted";
    }
}
