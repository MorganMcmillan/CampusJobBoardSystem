package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.exception.JobNotFoundException;
import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.JobStatus;
import com.CampusJobBoardSystem.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    // GET JOB BY ID
    
    public Job getJob(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job with ID " + id + " not found"));
    }
    // GET ALL JOBS
   
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    // GET JOBS BY STATUS
    
    public List<Job> getJobsByStatus(JobStatus status) {
        return jobRepository.findByStatus(status);
    }
    // GET JOBS BY CATEGORY
    // ------------------------------------------------------------
    public List<Job> getJobsByCategory(String category) {
        return jobRepository.findByCategory(category);
    }
    // GET JOBS BY STATUS AND CATEGORY
   
    public List<Job> getJobsByStatusAndCategory(JobStatus status, String category) {
        return jobRepository.findByStatusAndCategory(status, category);
    }
    // CREATE JOB
    
    public Job createJob(Job job) {
        job.setStatus(JobStatus.PENDING);  // default
        return jobRepository.save(job);
    }
    // UPDATE JOB
   
    public Job updateJob(Long id, Job updatedJob) {
        Job job = getJob(id);

        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setSalary(updatedJob.getSalary());
        job.setLocation(updatedJob.getLocation());
        job.setCategory(updatedJob.getCategory());
        job.setDeadline(updatedJob.getDeadline());
        job.setEmployer(updatedJob.getEmployer());

        return jobRepository.save(job);
    }
    // CHANGE JOB STATUS
   
    public Job setStatus(Long id, JobStatus status) {
        Job job = getJob(id);
        job.setStatus(status);
        return jobRepository.save(job);
    }
    public Job approveJob(Long id) {
        return setStatus(id, JobStatus.APPROVED);
    }
    public Job rejectJob(Long id) {
        return setStatus(id, JobStatus.REJECTED);
    }
    public Job setPending(Long id) {
        return setStatus(id, JobStatus.PENDING);
    }
    // DELETE JOB
        Job job = getJob(id);
        jobRepository.delete(job);
    }
}
