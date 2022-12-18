package com.classroom.LMS.classroomActivity.entity;

import com.classroom.LMS.Instructor.Instructor;
import com.classroom.LMS.classroom.Classroom;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Date createdOn;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="classroom_id", nullable=false)
    private Classroom classroom;

    public Notice(String title, String description, Date createdOn, Classroom classroom) {
        this.title = title;
        this.description = description;
        this.createdOn = createdOn;
        this.classroom = classroom;
    }

    public Notice() {
    }

    @OneToMany(mappedBy = "notice")
    @JsonIgnore
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
