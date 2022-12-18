package com.classroom.LMS.classroomActivity.entity;

import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.student.Student;

import javax.persistence.*;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String motivation;

    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    private Student student;

    @ManyToOne
    @JoinColumn(name="classroom_id", nullable=false)
    private Classroom classroom;
    private boolean status;
    private boolean active;

    public boolean isStatus() {
        return status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Enrollment(String name, String motivation, Student student, Classroom classroom, boolean status) {
        this.name = name;
        this.motivation = motivation;
        this.student = student;
        this.classroom = classroom;
        this.status = status;
    }

    public Enrollment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
