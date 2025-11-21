package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.Application;
import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.User;

import java.util.List;

public interface JobService {
    public List<Job> view(User user);
    public List<Job> view(User user, String category);
    public void create(User employer, Job job);
}
