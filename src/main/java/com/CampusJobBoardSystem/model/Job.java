package com.CampusJobBoardSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity representing a job posting in the Campus Job Board System.
 * Base class for different types of jobs (using JOINED inheritance strategy).
 *
 * @author Campus Job Board System Team
 * @version 1.0
 */
@Entity
@Table(name = "job", indexes = {
    @Index(name = "idx_job_employer", columnList = "employer_id"),
    @Index(name = "idx_job_status", columnList = "status"),
    @Index(name = "idx_job_category", columnList = "category")
})
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "job_type", discriminatorType = DiscriminatorType.STRING)
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_job_employer"))
    private User employer;

    @NotBlank(message = "Job title is required")
    @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @NotBlank(message = "Job description is required")
    @Size(min = 10, max = 2000, message = "Description must be between 10 and 2000 characters")
    @Column(name = "description", nullable = false, length = 2000)
    private String description;

    @Positive(message = "Salary must be positive")
    @Column(name = "salary", nullable = false)
    private double salary;

    @NotBlank(message = "Location is required")
    @Size(max = 255, message = "Location must not exceed 255 characters")
    @Column(name = "location", nullable = false, length = 255)
    private String location;

    @NotBlank(message = "Category is required")
    @Size(max = 100, message = "Category must not exceed 100 characters")
    @Column(name = "category", nullable = false, length = 100)
    private String category;

    @Future(message = "Deadline must be in the future")
    @Column(name = "deadline")
    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private JobStatus status;

    // Constructors

    /**
     * Default constructor required by JPA.
     */
    public Job() {
        this.status = JobStatus.PENDING;
    }

    /**
     * Constructor with required fields.
     *
     * @param employer The employer posting the job
     * @param title Job title
     * @param description Job description
     * @param salary Offered salary
     * @param location Job location
     * @param category Job category
     */
    public Job(User employer, String title, String description, double salary,
               String location, String category) {
        this();
        this.employer = employer;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.location = location;
        this.category = category;
    }

    /**
     * Full constructor with all fields.
     *
     * @param employer The employer posting the job
     * @param title Job title
     * @param description Job description
     * @param salary Offered salary
     * @param location Job location
     * @param category Job category
     * @param deadline Application deadline
     * @param status Job status
     */
    public Job(User employer, String title, String description, double salary,
               String location, String category, LocalDate deadline, JobStatus status) {
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    // Business methods

    /**
     * Approves the job posting.
     */
    public void approve() {
        this.status = JobStatus.APPROVED;
    }

    /**
     * Rejects the job posting.
     */
    public void reject() {
        this.status = JobStatus.REJECTED;
    }

    /**
     * Checks if the job is approved and visible to students.
     *
     * @return true if status is APPROVED
     */
    public boolean isApproved() {
        return this.status == JobStatus.APPROVED;
    }

    /**
     * Checks if the application deadline has passed.
     *
     * @return true if deadline is in the past
     */
    public boolean isExpired() {
        return deadline != null && deadline.isBefore(LocalDate.now());
    }

    /**
     * Checks if the job is still accepting applications.
     *
     * @return true if approved and not expired
     */
    public boolean isAcceptingApplications() {
        return isApproved() && !isExpired();
    }

    // equals, hashCode, and toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(id, job.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", employerId=" + (employer != null ? employer.getId() : null) +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", status=" + status +
                ", deadline=" + deadline +
                '}';
    }
}
