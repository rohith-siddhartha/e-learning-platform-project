package com.classroom.LMS.classroomActivity.entity;

import com.classroom.LMS.Instructor.Instructor;
import com.classroom.LMS.classroom.Classroom;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Date createdOn;
    private Integer maxMarks;
    private Integer duration;
    private Date deadline;

    @ManyToOne
    @JoinColumn(name="classroom_id", nullable=false)
    private Classroom classroom;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "quiz")
    private Set<QuizAttempt> attempts = new HashSet<>();

    public Quiz(String title, String description, Date createdOn, Integer maxMarks, Date deadline, Set<Question> questions) {
        this.title = title;
        this.description = description;
        this.createdOn = createdOn;
        this.maxMarks = maxMarks;
        this.questions = questions;
    }

    public Integer getDuration() {
        return duration;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<QuizAttempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(Set<QuizAttempt> attempts) {
        this.attempts = attempts;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Quiz() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(Integer maxMarks) {
        this.maxMarks = maxMarks;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
