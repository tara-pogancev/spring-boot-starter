package com.springboot.project.repository;

import com.springboot.project.model.ApplicationUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends EntityRepository<ApplicationUser> {

    @Query("SELECT u FROM ApplicationUser u WHERE u.email=:email")
    ApplicationUser findUserByEmail(@Param("email") String email);

}

