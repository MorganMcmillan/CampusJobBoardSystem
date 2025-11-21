package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.JobStatus;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.model.UserStatus;
import com.CampusJobBoardSystem.repository.JobRepository;
import com.CampusJobBoardSystem.repository.UserRepository;

public class AdminServiceImpl implements AdminService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void approve(Job job) {
        job.setStatus(JobStatus.APPROVED);
        jobRepository.save(job);
    }

    @Override
    public void reject(Job job) {
        job.setStatus(JobStatus.REJECTED);
        jobRepository.save(job);
    }

    @Override
    public void toggleActivation(User user, boolean activated) {
        user.setStatus(activated ? UserStatus.ACTIVE : UserStatus.INACTIVE);
        userRepository.save(user);
    }
}
