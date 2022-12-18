package com.classroom.LMS.security.Repository;

import com.classroom.LMS.security.UserCustomDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCustomDetailsRepository extends JpaRepository<UserCustomDetails,Long> {

    public UserCustomDetails findByUsername(String username);

}
