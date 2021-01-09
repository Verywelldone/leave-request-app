package com.marius.leaverequestgestionapp.controller;

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

        User user = getUserFromSession();
        leaveRequestDTO.setUserId(user.getId());

        System.out.println(leaveRequestDTO);
        System.out.println("Intra in controller");

        try {
            leaveRequestService.createLeaveRequest(leaveRequestDTO);
        } catch (Exception e) {
            modelAndView.setViewName("user/errors");
        }

        modelAndView.setViewName("user/home");
        return modelAndView;
    }


    private User getUserFromSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName());
    }


}
