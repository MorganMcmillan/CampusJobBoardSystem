package com.CampusJobBoardSystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PENDING")
public class PendingJob extends Job {
}
