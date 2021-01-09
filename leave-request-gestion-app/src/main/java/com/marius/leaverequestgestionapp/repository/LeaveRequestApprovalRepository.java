package com.marius.leaverequestgestionapp.repository;

import com.marius.leaverequestgestionapp.model.LeaveRequest;
import com.marius.leaverequestgestionapp.model.LeaveRequestApproval;
import com.marius.leaverequestgestionapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestApprovalRepository extends JpaRepository<LeaveRequestApproval, Long> {

    LeaveRequestApproval get(long leaveRequestId);

}
