package com.classroom.LMS.security.controller;

import com.classroom.LMS.Instructor.Instructor;
import com.classroom.LMS.security.JWTRequest;
import com.classroom.LMS.security.JWTResponse;
import com.classroom.LMS.security.JWTUtils;
import com.classroom.LMS.security.UserCustomDetails;
import com.classroom.LMS.security.service.UserCustomDetailsServiceImpl;
import com.classroom.LMS.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserCustomDetailsServiceImpl userCustomDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("/generate")
    public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {

        try {

            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());


        } catch ( UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found ");
        }

        /////////////authenticate

        UserDetails userDetails = this.userCustomDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));


    }

    @GetMapping("/test")
    public String test() {
        return "hi";
    }

    private void authenticate(String username, String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER DISABLED " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials " + e.getMessage());
        }

    }

    @GetMapping("/current-user")
    public Object getCurrentUser(Principal principal) {
        UserCustomDetails userCustomDetails = (UserCustomDetails)this.userCustomDetailsService.loadUserByUsername(principal.getName());

        if(userCustomDetails.getRole().equals("instructor")){
            return (Instructor)userCustomDetails.getInstructor();
        }

        return (Student)userCustomDetails.getStudent();

    }

//    @GetMapping("/current-student")
//    public UserCustomDetails getCurrentUser(Principal principal) {
//        return (UserCustomDetails)this.userCustomDetailsService.loadUserByUsername(principal.getName());
//
//    }
//
//    @GetMapping("/current-instructor")
//    public UserCustomDetails getCurrentUser(Principal principal) {
//        return (UserCustomDetails)this.userCustomDetailsService.loadUserByUsername(principal.getName());
//
//    }

}
