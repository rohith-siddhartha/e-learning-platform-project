package com.classroom.LMS.classroom;

import com.classroom.LMS.Category.Category;
import com.classroom.LMS.Instructor.Instructor;
import com.classroom.LMS.classroomActivity.entity.Enrollment;
import com.classroom.LMS.classroomActivity.entity.Notice;
import com.classroom.LMS.classroomActivity.entity.Quiz;
import com.classroom.LMS.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "classrooms")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private int capacity;
    private Date createdOn;

    @ManyToMany
    @JoinTable(name = "classroom_category",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
//    @JsonIgnore
    private Set<Category> categories = new HashSet<>();
    @ManyToMany(mappedBy = "classrooms", fetch = FetchType.EAGER)
    private Set<Instructor> instructors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Enrollment> enrollmentRequests = new HashSet<>();

    public Set<Enrollment> getEnrollmentRequests() {
        return enrollmentRequests;
    }

    public void setEnrollmentRequests(Set<Enrollment> enrollmentRequests) {
        this.enrollmentRequests = enrollmentRequests;
    }

    @OneToMany(mappedBy = "classroom")
    @JsonIgnore
    private Set<Notice> notices = new HashSet<>();

    @OneToMany(mappedBy = "classroom")
    @JsonIgnore
    private Set<Quiz> quizzes = new HashSet<>();

    public Classroom(Long id, String title, String description, Set<Instructor> instructors) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructors = instructors;
    }

    public Classroom() {
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Set<Notice> getNotices() {
        return notices;
    }

    public void setNotices(Set<Notice> notices) {
        this.notices = notices;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
