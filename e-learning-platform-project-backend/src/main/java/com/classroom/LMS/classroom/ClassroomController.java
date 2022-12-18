package com.classroom.LMS.classroom;

import com.classroom.LMS.classroom.service.ClassroomService;
import com.classroom.LMS.classroomActivity.entity.Enrollment;
import com.classroom.LMS.classroomActivity.entity.Notice;
import com.classroom.LMS.classroomActivity.entity.Quiz;
import com.classroom.LMS.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/classroom")
@CrossOrigin("*")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping("create")
    public Classroom createClassroom(@RequestBody Classroom classroom) throws Exception{
      return this.classroomService.create(classroom);
    };

    @PostMapping("enrolstudent")
    public Enrollment enrolStudent(@RequestBody Enrollment enrollment) throws Exception{
        return this.classroomService.enrolStudent(enrollment);
    }

    @PutMapping("{classroomId}/removestudent/{studentId}")
    public Classroom removeStudent(@PathVariable Long classroomId, @PathVariable Long studentId) throws Exception{
        return this.classroomService.removeStudent(classroomId, studentId);
    }

    @GetMapping("{classroomId}")
    public Classroom getClassroomById(@PathVariable Long classroomId) throws Exception{
        return this.classroomService.findById(classroomId);
    }

    @GetMapping("{classroomId}/notices")
    public Set<Notice> getNoticesByClassroomId(@PathVariable Long classroomId) throws Exception{
        return this.classroomService.findNoticesByClassroomId(classroomId);
    }

    @GetMapping("{classroomId}/enrollmentrequests")
    public Set<Enrollment> getEnrollmentByClassroomId(@PathVariable Long classroomId) throws Exception{
        return this.classroomService.findEnrollmentRequestsByClassroomId(classroomId);
    }

    @GetMapping("{classroomId}/quizzes")
    public Set<Quiz> getQuizzesByClassroomId(@PathVariable Long classroomId) throws Exception{
        return this.classroomService.findQuizzesByClassroomId(classroomId);
    }

    @GetMapping("{classroomId}/students")
    public Set<Student> getStudentsByClassroomId(@PathVariable Long classroomId) throws Exception{
        return this.classroomService.findStudentsByClassroomId(classroomId);
    }

    @GetMapping("all")
    public List<Classroom> getAllClassrooms() throws Exception{
        return this.classroomService.findAll();
    }

    @DeleteMapping("delete/{classroomId}")
    public String deleteClassroomById(@PathVariable Long classroomId) throws Exception{
        return this.classroomService.deleteById(classroomId);
    }
}
