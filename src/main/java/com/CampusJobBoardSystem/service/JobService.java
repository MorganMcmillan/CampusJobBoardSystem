package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.Application;
import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.User;

import java.util.List;

public interface JobService {
    List<Job> view();
    List<Job> view(String category);
    void create(Job job);
}
