package com.marius.leaverequestgestionapp.repository;

import com.marius.leaverequestgestionapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User findByEmailAndPassword(String Email, String Password);

    User findByEmail(String email);
    User findById(long id);

}
