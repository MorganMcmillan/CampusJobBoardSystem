package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.JobStatus;
import com.CampusJobBoardSystem.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findByStatus(JobStatus.APPROVED);
    }

    @Override
    public List<Job> getByCategory(String category) {
        return jobRepository.findByStatusAndCategory(JobStatus.APPROVED, category);
    }

    @Override
    public void saveJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
