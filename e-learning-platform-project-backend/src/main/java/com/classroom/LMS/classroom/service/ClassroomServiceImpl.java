package com.classroom.LMS.classroom.service;

import com.classroom.LMS.Category.Category;
import com.classroom.LMS.Category.CategoryRepository;
import com.classroom.LMS.Instructor.Instructor;
import com.classroom.LMS.Instructor.InstructorRepository;
import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.classroom.ClassroomRepository;
import com.classroom.LMS.classroomActivity.entity.Enrollment;
import com.classroom.LMS.classroomActivity.entity.Notice;
import com.classroom.LMS.classroomActivity.entity.Quiz;
import com.classroom.LMS.student.Student;
import com.classroom.LMS.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public Classroom create(Classroom classroom) throws Exception {

        if(classroom.getTitle()== null || classroom.getTitle().trim() == ""){
            throw new Exception("title should not be empty");
        }

        if(this.classroomRepository.findByTitle(classroom.getTitle()) != null){
            throw new Exception("a classroom with the same title exists");
        }

        Set<Instructor> instructors = new HashSet<>();

        for(Instructor instructor: classroom.getInstructors()){
            instructors.add(this.instructorRepository.findByUsername(instructor.getUsername()));
        }

        Set<Category> categories = new HashSet<>();

        for(Category category: classroom.getCategories()){
            categories.add(this.categoryRepository.findByName(category.getName()));
        }

        classroom.setInstructors(instructors);
        classroom.setCategories(categories);

        for(Category category: classroom.getCategories()){
            category.getClassrooms().add(classroom);
        }

        for(Instructor instructor: classroom.getInstructors()){
            instructor.getClassrooms().add(classroom);
        }

        return this.classroomRepository.save(classroom);
    }

    @Override
    public Classroom findById(Long classroomId) throws Exception {
        return this.classroomRepository.findById(classroomId).get();
    }

    @Override
    public Enrollment enrolStudent(Enrollment enrollment) throws Exception {

        if(enrollment.getClassroom() == null){
            throw new Exception(" classroom is null");
        }

        if(enrollment.getStudent() == null){
            throw new Exception(" student is null");
        }

        if(this.classroomRepository.findById(enrollment.getClassroom().getId()).get() == null){
            throw new Exception("no class exists with the requested class id");
        }

        if(this.studentRepository.findById(enrollment.getStudent().getId()).get() == null){
            throw new Exception("no student exists with the requested student id");
        }

        Student student = this.studentRepository.findById(enrollment.getStudent().getId()).get();

        Classroom classroom = this.classroomRepository.findById(enrollment.getClassroom().getId()).get();

        classroom.getStudents().add(student);

        student.getClassrooms().add(classroom);

        enrollment.setStatus(true);
        enrollment.setActive(false);

        this.studentRepository.save(student);

        this.classroomRepository.save(classroom);

//        this.classroomRepository.save(classroom);

        return enrollment;

    };

    @Override
    public Classroom removeStudent(Long classroomId, Long studentId) throws Exception{

        Classroom classroom = this.classroomRepository.findById(classroomId).get();

        Student student = this.studentRepository.findById(studentId).get();

        student.getClassrooms().remove(classroom);

        classroom.getStudents().remove(student);

        this.studentRepository.save(student);

        return this.classroomRepository.save(classroom);

    };

    @Override
    public Set<Enrollment> findEnrollmentRequestsByClassroomId(Long classroomId){

        Set<Enrollment> enrollmentRequests = new HashSet<>();

        for(Enrollment enrollment:this.classroomRepository.findById(classroomId).get().getEnrollmentRequests()){
            if(enrollment.isActive()){
                enrollmentRequests.add(enrollment);
            }
        }

        return enrollmentRequests;
    }

    @Override
    public Set<Notice> findNoticesByClassroomId(Long classroomId) {
        return this.classroomRepository.findById(classroomId).get().getNotices();
    }

    @Override
    public Set<Student> findStudentsByClassroomId(Long classroomId) throws Exception{
      return this.classroomRepository.findById(classroomId).get().getStudents();
    };

    @Override
    public List<Classroom> findAll() {
        return this.classroomRepository.findAll();
    }

    @Override
    public Set<Quiz> findQuizzesByClassroomId(Long classroomId) {
        return this.classroomRepository.findById(classroomId).get().getQuizzes();
    }

    @Override
    public String deleteById(Long classroomId) throws Exception {
        this.classroomRepository.deleteById(classroomId);
        return "deleted";
    }
}
