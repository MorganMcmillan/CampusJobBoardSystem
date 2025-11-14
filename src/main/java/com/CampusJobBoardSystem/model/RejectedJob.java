package com.CampusJobBoardSystem.model;

import jakarta.persistence.Entity;

@Entity
public class RejectedJob extends Job {
    // Why this job was rejected. Written by the admin.
    private String reason;
}
