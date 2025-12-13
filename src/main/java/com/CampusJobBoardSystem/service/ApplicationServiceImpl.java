package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.exception.InvalidRoleException;
import com.CampusJobBoardSystem.model.*;
import com.CampusJobBoardSystem.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of ApplicationService for managing job applications.
 * Provides operations for students to apply and employers to review.
 *
 * @author Campus Job Board System Team
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private final ApplicationRepository applicationRepository;

    /**
     * Constructor injection for ApplicationRepository.
     *
     * @param applicationRepository Repository for application data access
     */
    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Application create(Job job, User student) {
        logger.info("Creating application for job {} by student {}", job.getId(), student.getEmail());

        if (student.getRole() != Role.STUDENT) {
            logger.error("Invalid role for application: {}", student.getRole());
            throw new InvalidRoleException("Expected user with role STUDENT, got " + student.getRole());
        }

        Application application = new Application(job, student);
        // Status and timestamp are set in constructor/defaults

        Application savedApp = applicationRepository.save(application);
        logger.info("Application created successfully with id: {}", savedApp.getId());
        return savedApp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> view(User student) {
        logger.debug("Fetching applications for student: {}", student.getEmail());

        if (student.getRole() != Role.STUDENT) {
            throw new InvalidRoleException("Expected user with role STUDENT, got " + student.getRole());
        }
        return applicationRepository.findByStudent(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> view(User student, ApplicationStatus status) {
        logger.debug("Fetching applications for student {} with status {}", student.getEmail(), status);

        if (student.getRole() != Role.STUDENT) {
            throw new InvalidRoleException("Expected user with role STUDENT, got " + student.getRole());
        }
        return applicationRepository.findByStudentAndStatus(student, status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> view(Job job, User employer) {
        logger.debug("Fetching applications for job {} posted by employer {}", job.getId(), employer.getEmail());

        if (employer.getRole() != Role.EMPLOYER) {
            throw new InvalidRoleException("Expected user to have role EMPLOYER, got " + employer.getRole());
        }

        return applicationRepository.findByJobAndStatus(job, ApplicationStatus.SUBMITTED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getAllApplications() {
        logger.debug("Fetching all applications");
        return applicationRepository.findAll();
    }

    @Override
    @Transactional
    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }

    @Override
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}
