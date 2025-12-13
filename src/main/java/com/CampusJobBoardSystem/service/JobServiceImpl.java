package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.exception.JobNotFoundException;
import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.JobStatus;
import com.CampusJobBoardSystem.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of JobService for managing job posting operations.
 * Provides CRUD operations and business logic for job postings.
 *
 * @author Campus Job Board System Team
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class JobServiceImpl implements JobService {

    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    private final JobRepository jobRepository;

    /**
     * Constructor injection for JobRepository.
     *
     * @param jobRepository Repository for job data access
     */
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Job> getAllJobs() {
        logger.debug("Fetching all approved jobs");
        return jobRepository.findByStatus(JobStatus.APPROVED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Job> getByCategory(String category) {
        logger.debug("Fetching approved jobs by category: {}", category);
        return jobRepository.findByStatusAndCategory(JobStatus.APPROVED, category);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Job> searchJobs(String keyword) {
        logger.debug("Searching approved jobs with keyword: {}", keyword);
        return jobRepository.searchJobs(JobStatus.APPROVED, keyword);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveJob(Job job) {
        logger.info("Saving job: {}", job.getTitle());
        jobRepository.save(job);
        logger.info("Job saved successfully with id: {}", job.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Job getJobById(Long id) {
        logger.debug("Fetching job by id: {}", id);
        return jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job with id " + id + " not found"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteJob(Long id) {
        logger.info("Deleting job with id: {}", id);

        if (!jobRepository.existsById(id)) {
            logger.error("Attempt to delete non-existent job with id: {}", id);
            throw new JobNotFoundException("Job with id " + id + " not found");
        }

        jobRepository.deleteById(id);
        logger.info("Job deleted successfully with id: {}", id);
    }
}
