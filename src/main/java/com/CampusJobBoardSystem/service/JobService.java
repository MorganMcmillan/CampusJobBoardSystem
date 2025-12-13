package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.Job;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();
    List<Job> getByCategory(String category);
    List<Job> searchJobs(String keyword);
    void saveJob(Job job);

    Job getJobById(Long id);

    void deleteJob(Long id);
}
