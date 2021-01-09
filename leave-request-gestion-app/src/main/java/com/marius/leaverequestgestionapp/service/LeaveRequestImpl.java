package com.marius.leaverequestgestionapp.service;

import com.marius.leaverequestgestionapp.model.LeaveRequest;
import com.marius.leaverequestgestionapp.model.dto.LeaveRequestDTO;
import com.marius.leaverequestgestionapp.repository.LeaveRequestRepository;
import com.marius.leaverequestgestionapp.repository.TeamRepository;
import com.marius.leaverequestgestionapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Service
public class LeaveRequestImpl implements LeaveRequestService {

    @Autowired
    LeaveRequestRepository leaveRequestRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean createLeaveRequest(LeaveRequestDTO leaveRequestDTO) throws Exception {

        if (requestLengthIsHigherThanSixMonths(leaveRequestDTO.getStartDate(), leaveRequestDTO.getEndDate())) {
            throw new Exception("You can't create a leave request longer than 6 months.");
        }

        if (startDateIsFiveDaysAhead(leaveRequestDTO.getStartDate())) {
            throw new Exception("You can't create a leave request 5 days before starting date.");
        }

        new LeaveRequest();
        LeaveRequest leaveRequest = LeaveRequest.builder()
                .employee(userRepository.findById(leaveRequestDTO.getUserId()))
                .startDate(leaveRequestDTO.getStartDate())
                .endDate(leaveRequestDTO.getEndDate())
                .status("PENDING")
                .build();

        System.out.println(leaveRequest.toString());
        System.out.println("Intra in service");
        leaveRequestRepository.save(leaveRequest);

        return true;
    }

    @Override
    public ResponseEntity<String> updateLeaveRequest(LeaveRequestDTO leaveRequest) {
        return null;
    }

    private boolean requestLengthIsHigherThanSixMonths(Date startDate, Date endDate) {
        long ms = Math.abs(endDate.getTime() - startDate.getTime());
        long result = TimeUnit.DAYS.convert(ms, TimeUnit.MILLISECONDS);
        return result > 180;
    }

    private boolean startDateIsFiveDaysAhead(Date startDate) {
        Date now = new Date();
        DateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            startDate = sdf.parse(startDate.toString());
            long ms = Math.abs(startDate.getTime() - now.getTime());
            long result = TimeUnit.DAYS.convert(ms, TimeUnit.MILLISECONDS);

            return result < 5;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }
}
