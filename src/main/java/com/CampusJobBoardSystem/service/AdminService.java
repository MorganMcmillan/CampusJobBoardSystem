package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.User;

public interface AdminService {
    public void approve(Job job);
    public void reject(Job job);
    public void toggleActivation(User user, boolean activated);
}
