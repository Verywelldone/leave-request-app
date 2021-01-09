package com.marius.leaverequestgestionapp.service;

import com.marius.leaverequestgestionapp.model.LeaveRequest;
import com.marius.leaverequestgestionapp.model.User;
import com.marius.leaverequestgestionapp.model.dto.LeaveRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LeaveRequestService {

    boolean createLeaveRequest(LeaveRequestDTO leaveRequest) throws Exception;

    ResponseEntity<String> updateLeaveRequest(LeaveRequestDTO leaveRequest);

    LeaveRequest getLeaveRequest(User user);
}
