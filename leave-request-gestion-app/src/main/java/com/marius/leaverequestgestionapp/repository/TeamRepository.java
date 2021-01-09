package com.marius.leaverequestgestionapp.repository;

import com.marius.leaverequestgestionapp.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
