package com.marius.leaverequestgestionapp.model;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User manager;

}
