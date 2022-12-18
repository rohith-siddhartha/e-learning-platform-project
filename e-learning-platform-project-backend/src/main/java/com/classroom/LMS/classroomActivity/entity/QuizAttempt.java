package com.classroom.LMS.classroomActivity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class QuizAttempt {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Quiz quiz;

    private String studentName;

    private Long studentId;

    private Long quizIdentifier;

    private Integer marksScored;

    private Integer correctAnswers;

    private Integer attempted;

    @Transient
    private List< ArrayList<Object> > responseArray = new ArrayList<>();

    private HashMap<Long,String> answersMap = new HashMap<>();

    public QuizAttempt(Long id, Quiz quiz, Long studentId, HashMap<Long, String> answersMap) {
        this.id = id;
        this.quiz = quiz;
        this.studentId = studentId;
        this.answersMap = answersMap;
    }

    public QuizAttempt() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ArrayList<Object>> getResponseArray() {
        return responseArray;
    }

    public void setResponseArray(List<ArrayList<Object>> responseArray) {
        this.responseArray = responseArray;
    }

    public HashMap<Long, String> getAnswersMap() {
        return answersMap;
    }

    public void setAnswersMap(HashMap<Long, String> answersMap) {
        this.answersMap = answersMap;
    }

    public Integer getMarksScored() {
        return marksScored;
    }

    public void setMarksScored(Integer marksScored) {
        this.marksScored = marksScored;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Integer getAttempted() {
        return attempted;
    }

    public void setAttempted(Integer attempted) {
        this.attempted = attempted;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getQuizIdentifier() {
        return quizIdentifier;
    }

    public void setQuizIdentifier(Long quizIdentifier) {
        this.quizIdentifier = quizIdentifier;
    }
}
