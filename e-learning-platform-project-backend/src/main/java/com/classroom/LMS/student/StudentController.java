package com.classroom.LMS.student;

import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.security.Repository.UserCustomDetailsRepository;
import com.classroom.LMS.security.UserCustomDetails;
import com.classroom.LMS.student.service.StudentService;
import com.classroom.LMS.student.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserCustomDetailsRepository userCustomDetailsRepository;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("create")
    public Student createStudent(@RequestBody Student student) throws Exception{

        return this.studentService.createStudent(student);
    };

    @PutMapping("update")
    public Student updateStudent(@RequestBody Student student) throws Exception{
        return this.studentService.updateStudent(student);
    }

    @GetMapping("students/{studentId}")
    public Optional<Student> getStudentById(@PathVariable Long studentId) throws Exception{
        return this.studentService.getStudentById(studentId);
    }

    @GetMapping("classrooms/{studentId}")
    public Set<Classroom> getClassroomsByStudentId(@PathVariable Long studentId) throws Exception{
        return this.studentService.getClassroomsByStudentId(studentId);
    }

    @GetMapping("studentsByUsername/{username}")
    public Optional<Student> getStudentByUsername(@PathVariable String username) throws Exception{
        return Optional.ofNullable(this.studentService.getStudentByUsername(username));
    }

    @DeleteMapping("delete/{studentId}")
    public String deleteStudentById(@PathVariable Long studentId) throws Exception{
        return this.studentService.deleteStudentById(studentId);
    }

}
