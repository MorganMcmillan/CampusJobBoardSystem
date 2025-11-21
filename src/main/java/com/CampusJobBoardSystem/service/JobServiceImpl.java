package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.exception.InvalidRoleException;
import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.JobStatus;
import com.CampusJobBoardSystem.model.Role;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.repository.JobRepository;

import java.util.List;

public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> view(User user) {
        return jobRepository.findByStatus(JobStatus.APPROVED);
    }

    @Override
    public List<Job> view(User user, String category) {
        return jobRepository.findByStatusAndCategory(JobStatus.APPROVED, category);
    }

    @Override
    public void create(User employer, Job job) {
        if (employer.getRole() != Role.EMPLOYER)
            throw new InvalidRoleException("Expected user to have role EMPLOYER, got " + employer.getRole() + ".");
        TODO!
    }
}
