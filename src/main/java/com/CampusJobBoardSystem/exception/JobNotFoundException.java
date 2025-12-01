package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.exception.JobNotFoundException;
import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // Get job by ID
    public Job getJobById(Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException("Job with ID " + jobId + " not found."));
    }

    // Get all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Update job
    public Job updateJob(Long jobId, Job updatedJob) {

        Job existingJob = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException("Cannot update. Job with ID " + jobId + " not found."));

        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setEmployer(updatedJob.getEmployer());

        return jobRepository.save(existingJob);
    }

    // Delete job
    public void deleteJob(Long jobId) {
        if (!jobRepository.existsById(jobId)) {
            throw new JobNotFoundException("Cannot delete. Job with ID " + jobId + " does not exist.");
        }

        jobRepository.deleteById(jobId);
    }
}
