package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.AdminStats;
import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
    void approve(Job job);
    void reject(Job job);
    void toggleActivation(User user, boolean activated);

    AdminStats getDashboardStats();

    List<User> getAllUsers();

    void deleteUser(Long id);

    List<Job> getAllJobs();

    void deleteJob(Long id);
}
