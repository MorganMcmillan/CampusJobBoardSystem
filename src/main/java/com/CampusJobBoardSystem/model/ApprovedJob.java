package com.CampusJobBoardSystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Map;

@Entity
@DiscriminatorValue("APPROVED")
public class ApprovedJob extends Job {
    private Map<Student, Application> applications;
}
