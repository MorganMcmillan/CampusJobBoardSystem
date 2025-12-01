package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.exception.DuplicateApplicationException;
import com.CampusJobBoardSystem.model.Application;
import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.repository.ApplicationRepository;
import com.CampusJobBoardSystem.repository.JobRepository;
import com.CampusJobBoardSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              UserRepository userRepository,
                              JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    public Application apply(Long userId, Long jobId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Check duplicate application
        boolean alreadyApplied = applicationRepository
                .existsByUserIdAndJobId(userId, jobId);

        if (alreadyApplied) {
            throw new DuplicateApplicationException(
                    "You have already applied for this job."
            );
        }

        // Create new application
        Application application = new Application();
        application.setUser(user);
        application.setJob(job);
        application.setStatus("PENDING");

        return applicationRepository.save(application);
    }
}
