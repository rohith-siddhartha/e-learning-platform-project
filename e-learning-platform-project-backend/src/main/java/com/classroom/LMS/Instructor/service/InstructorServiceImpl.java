package com.classroom.LMS.Instructor.service;

import com.classroom.LMS.Instructor.Instructor;
import com.classroom.LMS.Instructor.InstructorRepository;
import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.security.UserCustomDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Instructor create(Instructor instructor) throws Exception {

        // checking empty fields
        if(instructor.getUsername() == null || instructor.getUsername().trim() ==""){
            throw new Exception("Username is empty");
        }

        if(instructor.getPassword() == null || instructor.getPassword().trim() ==""){
            throw new Exception("password is empty");
        }

        // checking if user already exists
        if(this.instructorRepository.findByUsername(instructor.getUsername()) != null){
            throw new Exception("Instructor already exists");
        }

        // setting the default properties
        instructor.setRole("instructor");
        instructor.setStatus(true);

        instructor.setPassword(bCryptPasswordEncoder.encode(instructor.getPassword()));

        // student login details
        UserCustomDetails userCustomDetails = new UserCustomDetails(instructor.getUsername(),instructor.getPassword(),instructor.getRole(),instructor.getStatus(),instructor);
        userCustomDetails.setUserId(instructor.getId());
        instructor.setUserCustomDetails(userCustomDetails);

        Instructor savedInstructor = this.instructorRepository.save(instructor);

        return savedInstructor;
    }

    @Override
    public Instructor update(Instructor instructor) throws Exception {

        if(instructor.getId()==null){
            throw new Exception("Id is empty");
        }

        Instructor existingInstructor = this.instructorRepository.findById(instructor.getId()).get();

        if(existingInstructor == null){
            throw new Exception("no existing instructor");
        }

        // updating fields
        existingInstructor.setAbout(instructor.getAbout());
        existingInstructor.setFirstName(instructor.getFirstName());
        existingInstructor.setLastName(instructor.getLastName());
        existingInstructor.setProfilePicture(instructor.getProfilePicture());
        existingInstructor.setEmail(instructor.getEmail());

        // updating username
        existingInstructor.setUsername(instructor.getUsername());
        existingInstructor.getUserCustomDetails().setUsername(instructor.getUsername());

        return this.instructorRepository.save(existingInstructor);

    }

    @Override
    public Optional<Instructor> findById(Long instructorId) throws Exception {
        return this.instructorRepository.findById(instructorId);
    }

    @Override
    public Set<Classroom> getClassroomsById(Long id) {

        Optional<Instructor> instructor = this.instructorRepository.findById(id);

        if(instructor != null){
            return instructor.get().getClassrooms();
        }

        return null;

    }

    @Override
    public Instructor findByUsername(String username) throws Exception {
        return this.instructorRepository.findByUsername(username);
    }

    @Override
    public String deleteById(Long instructorId) throws Exception {
        // checking if user doesn't exists
        if(this.instructorRepository.findById(instructorId) == null){
            throw new Exception("instructor not found");
        }

        this.instructorRepository.deleteById(instructorId);
        return "deleted";
    }
}
