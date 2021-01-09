package com.marius.leaverequestgestionapp.controller;

import com.marius.leaverequestgestionapp.model.User;
import com.marius.leaverequestgestionapp.model.dto.LeaveRequestDTO;
import com.marius.leaverequestgestionapp.model.dto.LoginDTO;
import com.marius.leaverequestgestionapp.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public ModelAndView loginView() {
        encryptPassword();
        ModelAndView model = new ModelAndView();
        model.addObject("loginDTO", new LoginDTO());
        model.setViewName("user/login");
        return model;
    }

    void encryptPassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("mihai"));
    }
}
