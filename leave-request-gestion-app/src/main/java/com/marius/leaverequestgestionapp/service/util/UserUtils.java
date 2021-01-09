package com.marius.leaverequestgestionapp.service.util;

import com.marius.leaverequestgestionapp.model.User;
import com.marius.leaverequestgestionapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserUtils {

    @Autowired
    private UserRepository userRepository;

    public User getUserFromSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName());
    }


}
