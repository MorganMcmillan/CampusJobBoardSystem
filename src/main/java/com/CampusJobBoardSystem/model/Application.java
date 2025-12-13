package com.CampusJobBoardSystem.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity representing a job application in the Campus Job Board System.
 * Tracks student applications to job postings.
 *
 * @author Campus Job Board System Team
 * @version 1.0
 */
@Entity
@Table(name = "application", indexes = {
    @Index(name = "idx_application_student", columnList = "student_id"),
    @Index(name = "idx_application_job", columnList = "job_id"),
    @Index(name = "idx_application_status", columnList = "status")
})
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false, foreignKey = @ForeignKey(name = "fk_application_job"))
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false, foreignKey = @ForeignKey(name = "fk_application_student"))
    private User student;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ApplicationStatus status;

    @CreationTimestamp
    @Column(name = "applied_at", nullable = false, updatable = false)
    private LocalDateTime appliedAt;

    // Constructors

    /**
     * Default constructor required by JPA.
     */
    public Application() {
        this.status = ApplicationStatus.SUBMITTED;
        this.appliedAt = LocalDateTime.now();
    }

    /**
     * Constructor with required fields.
     *
     * @param job The job being applied to
     * @param student The student applying
     */
    public Application(Job job, User student) {
        this();
        this.job = job;
        this.student = student;
    }

    /**
     * Full constructor with all fields.
     *
     * @param job The job being applied to
     * @param student The student applying
     * @param status Application status
     * @param appliedAt Timestamp of application
     */
    public Application(Job job, User student, ApplicationStatus status, LocalDateTime appliedAt) {
        this.job = job;
        this.student = student;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    // Business methods

    /**
     * Accepts this application.
     */
    public void accept() {
        this.status = ApplicationStatus.ACCEPTED;
    }

    /**
     * Rejects this application.
     */
    public void reject() {
        this.status = ApplicationStatus.REJECTED;
    }

    /**
     * Checks if the application is pending review.
     *
     * @return true if status is SUBMITTED
     */
    public boolean isPending() {
        return this.status == ApplicationStatus.SUBMITTED;
    }

    /**
     * Checks if the application has been accepted.
     *
     * @return true if status is ACCEPTED
     */
    public boolean isAccepted() {
        return this.status == ApplicationStatus.ACCEPTED;
    }

    /**
     * Checks if the application has been rejected.
     *
     * @return true if status is REJECTED
     */
    public boolean isRejected() {
        return this.status == ApplicationStatus.REJECTED;
    }

    // equals, hashCode, and toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", jobId=" + (job != null ? job.getId() : null) +
                ", studentId=" + (student != null ? student.getId() : null) +
                ", status=" + status +
                ", appliedAt=" + appliedAt +
                '}';
    }
}
