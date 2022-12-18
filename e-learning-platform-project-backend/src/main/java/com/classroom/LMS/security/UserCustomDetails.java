package com.classroom.LMS.security;

import com.classroom.LMS.Instructor.Instructor;
import com.classroom.LMS.student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_custom_details")
public class UserCustomDetails implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String role;
    private boolean status;
    private Long UserId;

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public UserCustomDetails(String username, String password, String role, boolean status, Student student, Instructor instructor) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.student = student;
        this.instructor = instructor;
    }

    public boolean isStatus() {
        return status;
    }

    public UserCustomDetails(String username, String password, String role, boolean status, Instructor instructor) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.instructor = instructor;
    }

    public UserCustomDetails(String username, String password, String role, boolean status, Student student) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.student = student;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @OneToOne(mappedBy = "userCustomDetails")
//    @JsonBackReference
    private Student student;

    @OneToOne(mappedBy = "userCustomDetails")
//    @JsonBackReference
    private Instructor instructor;

    public UserCustomDetails(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<Authority> authorities = new HashSet<>();

        authorities.add(new Authority(this.getRole()));

        return authorities;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserCustomDetails() {
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @JsonBackReference
    public Student getStudent() {
        return student;
    }

//    @JsonBackReference
    public Instructor getInstructor() {
        return instructor;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status;
    }
}
