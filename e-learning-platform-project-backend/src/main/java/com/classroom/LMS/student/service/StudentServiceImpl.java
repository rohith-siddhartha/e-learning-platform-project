package com.classroom.LMS.student.service;

import com.classroom.LMS.Instructor.Instructor;
import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.security.Repository.UserCustomDetailsRepository;
import com.classroom.LMS.security.UserCustomDetails;
import com.classroom.LMS.security.service.UserCustomDetailsServiceImpl;
import com.classroom.LMS.student.Student;
import com.classroom.LMS.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserCustomDetailsServiceImpl userCustomDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) throws Exception {

        // checking empty fields
        if(student.getUsername() == null || student.getUsername().trim() ==""){
            throw new Exception("Username is empty");
        }

        if(student.getPassword() == null || student.getPassword().trim() ==""){
            throw new Exception("password is empty");
        }

        // checking if user already exists
        if(this.studentRepository.findByUsername(student.getUsername()) != null){
            throw new Exception("student already exists");
        }

        // setting the default properties
        student.setRole("student");
        student.setStatus(true);

        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));

        // student login details
        UserCustomDetails userCustomDetails = new UserCustomDetails(student.getUsername(),student.getPassword(),student.getRole(),student.getStatus(),student);
        userCustomDetails.setUserId(student.getId());
        student.setUserCustomDetails(userCustomDetails);

        Student savedStudent = this.studentRepository.save(student);

        return savedStudent;
    };

    @Override
    public Student updateStudent(Student student) throws Exception {

        if(student.getId()==null){
            throw new Exception("Id is empty");
        }

        Student existingStudent = this.studentRepository.findById(student.getId()).get();

        if(existingStudent == null){
            throw new Exception("no existing student");
        }

        // updating fields
        existingStudent.setAbout(student.getAbout());
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setProfilePicture(student.getProfilePicture());
        existingStudent.setEmail(student.getEmail());

        // updating username
        existingStudent.setUsername(student.getUsername());
        existingStudent.getUserCustomDetails().setUsername(student.getUsername());

        return this.studentRepository.save(existingStudent);

    }

    @Override
    public Optional<Student> getStudentById(Long studentId) throws Exception {
        return this.studentRepository.findById(studentId);
    }

    @Override
    public Student getStudentByUsername(String studentUsername) throws Exception {
        return this.studentRepository.findByUsername(studentUsername);
    }

    @Override
    public Set<Classroom> getClassroomsByStudentId(Long studentId){
        return this.studentRepository.findById(studentId).get().getClassrooms();
    }

    @Override
    public String deleteStudentById(Long studentId) throws Exception {

        // checking if user doesn't exists
        if(this.studentRepository.findById(studentId) == null){
            throw new Exception("student not found");
        }

        this.studentRepository.deleteById(studentId);
        return "deleted";
    }
}
