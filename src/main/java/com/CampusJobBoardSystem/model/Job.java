package com.CampusJobBoardSystem.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "job_type", discriminatorType = DiscriminatorType.STRING)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private double salary;
    private String location;
    // TODO: replace with category entity
    private String category;
    private Date deadline;
}
