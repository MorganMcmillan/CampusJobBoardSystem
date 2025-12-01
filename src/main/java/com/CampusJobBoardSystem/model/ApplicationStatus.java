package com.CampusJobBoardSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship to User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relationship to Job
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    // Your enum
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.SUBMITTED;

    // Constructors
    public Application() {}

    public Application(User user, Job job, ApplicationStatus status) {
        this.user = user;
        this.job = job;
        this.status = status;
    }

    // Getters & setters
    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
}
