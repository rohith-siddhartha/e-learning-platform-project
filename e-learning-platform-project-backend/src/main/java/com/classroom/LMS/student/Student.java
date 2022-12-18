package com.classroom.LMS.student;

import com.classroom.LMS.Category.Category;
import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.classroomActivity.entity.Enrollment;
import com.classroom.LMS.security.UserCustomDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String username;
    public String email;
    public String password;
    public boolean status;
    private String firstName;
    private String lastName;
    private String role = "student";
    private String about;
    private String profilePicture;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_custom_details_id", referencedColumnName = "id")
    @JsonIgnore
//    @JsonManagedReference
    private UserCustomDetails userCustomDetails;

    @ElementCollection
    private List<Category> interests = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private Set<Enrollment> enrollmentRequests = new HashSet<>();

    public Set<Enrollment> getEnrollmentRequests() {
        return enrollmentRequests;
    }

    public void setEnrollmentRequests(Set<Enrollment> enrollmentRequests) {
        this.enrollmentRequests = enrollmentRequests;
    }

    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    private Set<Classroom> classrooms = new HashSet<>();

    public Student() {
    }

    public Student(Long id, String username, String email, String password, boolean status, String firstName, String lastName, String about,
                   String profilePicture, List<Category> interests, Set<Classroom> classrooms) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.about = about;
        this.profilePicture = profilePicture;
        this.interests = interests;
        this.classrooms = classrooms;
    }

    public Student(Long id, String username, String email, String password, boolean status, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // getters ans setters

//    @JsonManagedReference
    public UserCustomDetails getUserCustomDetails() {
        return userCustomDetails;
    }

    public void setUserCustomDetails(UserCustomDetails userCustomDetails) {
        this.userCustomDetails = userCustomDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Category> getInterests() {
        return interests;
    }

    public void setInterests(List<Category> interests) {
        this.interests = interests;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(Set<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
}
