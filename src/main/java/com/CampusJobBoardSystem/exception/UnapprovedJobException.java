package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.exception.JobNotFoundException;
import com.CampusJobBoardSystem.exception.UnapprovedJobException;
import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.repository.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class JobApprovalService {

    private final JobRepository jobRepository;

    public JobApprovalService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // Fetch job and verify approval
    public Job getApprovedJob(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException("Job with ID " + jobId + " not found."));

        if (!job.isApproved()) {
            throw new UnapprovedJobException(
                    "This job is not approved yet and cannot be viewed."
            );
        }

        return job;
    }

    // Approve job (Admin action)
    public Job approveJob(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException("Cannot approve. Job with ID " + jobId + " does not exist."));

        job.setApproved(true);
        return jobRepository.save(job);
    }

    // Reject job (Admin action)
    public Job rejectJob(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException("Cannot reject. Job with ID " + jobId + " does not exist."));

        job.setApproved(false);
        return jobRepository.save(job);
    }
}
