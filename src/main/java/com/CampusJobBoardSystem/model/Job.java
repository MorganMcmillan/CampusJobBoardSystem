package com.CampusJobBoardSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "job_type", discriminatorType = DiscriminatorType.STRING)
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Employer is required.")
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private User employer;

    @NotEmpty(message = "Job title cannot be empty.")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters.")
    private String title;

    @NotEmpty(message = "Description cannot be empty.")
    @Size(min = 10, message = "Description must be at least 10 characters long.")
    private String description;

    @Positive(message = "Salary must be a positive number.")
    private double salary;

    @NotEmpty(message = "Location cannot be empty.")
    @Size(min = 2, max = 50, message = "Location must be between 2 and 50 characters.")
    private String location;

    @NotEmpty(message = "Category is required.")
    @Size(min = 3, max = 50, message = "Category must be between 3 and 50 characters.")
    private String category;

    @NotNull(message = "Deadline date is required.")
    @Future(message = "Deadline must be a future date.")
    @Temporal(TemporalType.DATE)
    private Date deadline;

    @NotNull(message = "Job status cannot be null.")
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
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getEmployer() { return employer; }
    public void setEmployer(User employer) { this.employer = employer; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Date getDeadline() { return deadline; }
    public void setDeadline(Date deadline) { this.deadline = deadline; }

    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }
}
