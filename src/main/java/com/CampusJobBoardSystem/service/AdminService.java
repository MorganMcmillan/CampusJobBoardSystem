package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.AdminStats;
import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.User;

import java.util.List;

public interface AdminService {
    public void approve(Job job);
    public void reject(Job job);
    public void toggleActivation(User user, boolean activated);

    AdminStats getDashboardStats();

    List<User> getAllUsers();

    void deleteUser(Long id);

    List<Job> getAllJobs();

    void deleteJob(Long id);
}
