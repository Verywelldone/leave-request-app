package com.marius.leaverequestgestionapp.controller;

import com.marius.leaverequestgestionapp.model.LeaveRequest;
import com.marius.leaverequestgestionapp.model.LeaveRequestApproval;
import com.marius.leaverequestgestionapp.model.User;
import com.marius.leaverequestgestionapp.model.dto.LeaveRequestApprovalDTO;
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

import java.util.ArrayList;
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
        ModelAndView model = new ModelAndView("user/leaveRequestApproval");
        List<LeaveRequestApproval> pendingRequests = leaveRequestApprovalRepository.findAllByManagerId(userUtils.getUserFromSession().getId());
        List<LeaveRequestApprovalDTO> actualRequests = new ArrayList<>();
        pendingRequests.forEach(leaveRequestApproval -> {
            actualRequests.add(new LeaveRequestApprovalDTO().builder()
                    .name(userRepository.findById(leaveRequestApproval.getUserId()).getName())
                    .startDate(leaveRequestService.getLeaveRequest(userRepository.findById(leaveRequestApproval.getUserId())).getStartDate())
                    .endDate(leaveRequestService.getLeaveRequest(userRepository.findById(leaveRequestApproval.getUserId())).getEndDate())
                    .status(leaveRequestApproval.getStatus())
                    .build());
        });
        actualRequests.forEach(result -> {
            System.out.println(result.toString());
        });

        model.addObject("leaveRequests", actualRequests);
        return model;
    }


}
