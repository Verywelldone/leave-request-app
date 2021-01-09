package com.marius.leaverequestgestionapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Date startDate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User employee;

}
