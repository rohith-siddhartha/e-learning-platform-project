package com.classroom.LMS.classroomActivity.service;

import com.classroom.LMS.classroomActivity.entity.Notice;
import com.classroom.LMS.classroomActivity.entity.Question;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface QuestionService {

    public Question createQuestion(Question question) throws Exception;
    public Question editQuestion(Question question) throws Exception;
    public Optional<Question> getQuestionById(Long questionId) throws Exception;
    public String deleteQuestionById(Long questionId) throws Exception;

}
