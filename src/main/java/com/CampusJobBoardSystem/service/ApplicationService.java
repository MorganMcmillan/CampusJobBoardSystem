package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.*;
import org.springframework.context.ApplicationListener;

import java.util.List;

public interface ApplicationService {
    public Application create(Job job, User student);
    public List<Application> view(User student);
    public List<Application> view(User student, ApplicationStatus status);
    public List<Application> view(Job job, User employer);

    List<Application> getAllApplications();

    void saveApplication(Application application);

    Application getApplicationById(Long id);

    void deleteApplication(Long id);
}
