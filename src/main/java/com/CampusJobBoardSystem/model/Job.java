package com.CampusJobBoardSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "job_type", discriminatorType = DiscriminatorType.STRING)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private User employer;

    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @Positive
    private double salary;
    @NotEmpty
    private String location;
    @NotEmpty
    private String category;

    @Temporal(TemporalType.DATE)
    @Future
    private Date deadline;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    // Constructors
    public Job() {}

    public Job(User employer, String title, String description, double salary,
               String location, String category, Date deadline, JobStatus status) {
        this.employer = employer;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.location = location;
        this.category = category;
        this.deadline = deadline;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getEmployer() {
        return employer;
    }

    public void setEmployer(User employer) {
        this.employer = employer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }
}
