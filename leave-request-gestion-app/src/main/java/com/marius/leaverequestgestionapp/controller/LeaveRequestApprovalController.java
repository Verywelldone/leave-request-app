package com.marius.leaverequestgestionapp.controller;

import com.marius.leaverequestgestionapp.model.LeaveRequest;
import com.marius.leaverequestgestionapp.model.LeaveRequestApproval;
import com.marius.leaverequestgestionapp.model.User;
import com.marius.leaverequestgestionapp.model.dto.LeaveRequestDTO;
import com.marius.leaverequestgestionapp.repository.LeaveRequestApprovalRepository;
import com.marius.leaverequestgestionapp.repository.UserRepository;
import com.marius.leaverequestgestionapp.service.LeaveRequestService;
import com.marius.leaverequestgestionapp.service.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LeaveRequestApprovalController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LeaveRequestService leaveRequestService;

    @Autowired
    LeaveRequestApprovalRepository leaveRequestApprovalRepository;

    @Autowired
    UserUtils userUtils;

    @GetMapping("/management")
    @Secured("manager")
    public ModelAndView createLeaveRequestModelView() {
        System.out.println("APPROVE");
        ModelAndView model = new ModelAndView("user/leaveRequestApproval");

        List<LeaveRequestApproval> requests = leaveRequestApprovalRepository.findAllByManager(userUtils.getUserFromSession().getId());



        model.addObject("", );
        return model;
    }


}
