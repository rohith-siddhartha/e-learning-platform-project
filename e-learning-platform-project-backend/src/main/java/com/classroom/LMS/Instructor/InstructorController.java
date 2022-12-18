package com.classroom.LMS.Instructor;

import com.classroom.LMS.Instructor.service.InstructorService;
import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/instructor")
@CrossOrigin("*")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("create")
    public Instructor createInstructor(@RequestBody Instructor instructor) throws Exception{

        return this.instructorService.create(instructor);
    };

    @PutMapping("update")
    public Instructor updateInstructor(@RequestBody Instructor instructor) throws Exception{
        return this.instructorService.update(instructor);
    }

    @GetMapping("{instructorId}")
    public Optional<Instructor> getInstructorById(@PathVariable Long instructorId) throws Exception{
        return this.instructorService.findById(instructorId);
    }

    @GetMapping("instructorbyname/{username}")
    public Optional<Instructor> getInstructorByUsername(@PathVariable String username) throws Exception{
        return Optional.ofNullable(this.instructorService.findByUsername(username));
    }

    @GetMapping("classrooms/{id}")
    public Set<Classroom> getClassroomsByInstructorId(@PathVariable Long id) throws Exception{
        return this.instructorService.getClassroomsById(id);
    }

    @DeleteMapping("delete/{instructorId}")
    public String deleteInstructorById(@PathVariable Long instructorId) throws Exception{
        return this.instructorService.deleteById(instructorId);
    }

}
