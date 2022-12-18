package com.classroom.LMS.Instructor;

import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.security.UserCustomDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instructors")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public String username;
    public String email;
    public String password;
    public boolean status;
    public String profilePicture;
    private String firstName;
    private String lastName;
    private String role = "instructor";
    private String about;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

//    @JsonManagedReference
    public UserCustomDetails getUserCustomDetails() {
        return userCustomDetails;
    }

    public void setUserCustomDetails(UserCustomDetails userCustomDetails) {
        this.userCustomDetails = userCustomDetails;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_custom_details_id", referencedColumnName = "id")
//    @JsonManagedReference
    @JsonIgnore
    private UserCustomDetails userCustomDetails;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "instructor_course",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id"))
    @JsonIgnore
    private Set<Classroom> classrooms = new HashSet<>();

    public Instructor() {
    }

    public Instructor(Long id, String username, String email, String password, boolean status, String profilePicture,
                      String firstName, String lastName, Set<Classroom> classrooms) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.profilePicture = profilePicture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classrooms = classrooms;
    }

    public Instructor(Long id, String username, String email, String password, boolean status, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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

    public Set<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(Set<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public boolean getStatus() { return this.status; }
}
