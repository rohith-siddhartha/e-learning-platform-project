package com.classroom.LMS.classroomActivity.service;

import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.classroom.ClassroomRepository;
import com.classroom.LMS.classroomActivity.entity.Question;
import com.classroom.LMS.classroomActivity.entity.Quiz;
import com.classroom.LMS.classroomActivity.entity.QuizAttempt;
import com.classroom.LMS.classroomActivity.repository.QuestionRepository;
import com.classroom.LMS.classroomActivity.repository.QuizAttemptRepository;
import com.classroom.LMS.classroomActivity.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository, ClassroomRepository classroomRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.classroomRepository = classroomRepository;
    }

    public QuizServiceImpl() {
    }

    @Override
    public Quiz createQuiz(Quiz quiz) throws Exception {

        this.quizRepository.save(quiz);

        for(Question question:quiz.getQuestions()){
            question.setQuiz(quiz);
            this.questionRepository.save(question);
        }

        Long classroomId = quiz.getClassroom().getId();

        Classroom classroom = this.classroomRepository.findById(classroomId).get();

        quiz.setClassroom(classroom);

        classroom.getQuizzes().add(quiz);

        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz editQuiz(Quiz quiz) throws Exception {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuizById(Long quizId) throws Exception {
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public String deleteQuizById(Long quizId) throws Exception {
        this.quizRepository.deleteById(quizId);
        return "deleted";
    }

    @Override
    public QuizAttempt evaluateQuiz(QuizAttempt attempt) throws Exception {

        Integer marksScored=0, correctAnswers=0, attempted=0;

        for(ArrayList<Object> response : attempt.getResponseArray()) {

            Question question = this.questionRepository.findById(new Long((Integer) response.get(0))).get();

            String givenAnswer = (String) response.get(1);

            if (!givenAnswer.equals("")){
                attempted += 1;
            }

            if (question.getAnswer().equals(givenAnswer)){
                marksScored += question.getMarks();
                correctAnswers += 1;
            }
        }

        attempt.setAttempted(attempted);
        attempt.setMarksScored(marksScored);
        attempt.setCorrectAnswers(correctAnswers);

        Long quizId = attempt.getQuizIdentifier();

        Optional<Quiz> q = this.quizRepository.findById(quizId);

        attempt.setQuiz(q.get());

        QuizAttempt savedAttempt = this.quizAttemptRepository.save(attempt);

        this.quizRepository.findById(savedAttempt.getQuizIdentifier()).get().getAttempts().add(savedAttempt);

        return savedAttempt;
    }
}
