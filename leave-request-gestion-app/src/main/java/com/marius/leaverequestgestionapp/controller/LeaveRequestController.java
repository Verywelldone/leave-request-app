package com.marius.leaverequestgestionapp.controller;

import com.marius.leaverequestgestionapp.model.LeaveRequest;
import com.marius.leaverequestgestionapp.model.User;
import com.marius.leaverequestgestionapp.model.dto.LeaveRequestDTO;
import com.marius.leaverequestgestionapp.repository.UserRepository;
import com.marius.leaverequestgestionapp.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LeaveRequestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LeaveRequestService leaveRequestService;

    @ModelAttribute("leaveRequestDTO")
    @RequestMapping(value = {"/home", "/create-leave-request"}, method = RequestMethod.GET)
    public ModelAndView createLeaveRequestModelView() {
        ModelAndView model = new ModelAndView("user/home");

        User user = getUserFromSession();

        model.addObject("name", user.getName());
        model.addObject("leaveRequestDTO", new LeaveRequestDTO());

        return model;
    }

    @PostMapping("/home")
    public ModelAndView createLeaveRequestAction(@ModelAttribute("leaveRequestDTO") LeaveRequestDTO leaveRequestDTO) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(leaveRequestDTO);
        modelAndView.setViewName("user/home");

        User user = getUserFromSession();
        leaveRequestDTO.setUserId(user.getId());

        System.out.println(leaveRequestDTO);
        System.out.println("Intra in controller");

        try {
            leaveRequestService.createLeaveRequest(leaveRequestDTO);
        } catch (Exception e) {
//            TODO: Add error object + message in html
            modelAndView.setViewName("user/errors");
        }

        return modelAndView;
    }

    @GetMapping("/my-leave-requests")
    public ModelAndView listLeaveRequestsView() {
        ModelAndView modelAndView = new ModelAndView();
        LeaveRequest leaveRequest = leaveRequestService.getLeaveRequest(getUserFromSession());
        modelAndView.setViewName("user/userLeaveRequests");
        modelAndView.addObject("leaveRequest", leaveRequest);

        return modelAndView;
    }

    @GetMapping("/error")
    public ModelAndView errorHandling() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/errors");

        return modelAndView;
    }


    private User getUserFromSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName());
    }


}
