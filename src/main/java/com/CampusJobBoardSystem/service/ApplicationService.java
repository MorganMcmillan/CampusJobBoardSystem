package com.CampusJobBoardSystem.service;

import com.CampusJobBoardSystem.model.*;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ApplicationService {
    Application create(Job job, User student);
    List<Application> view(User student);
    List<Application> view(User student, ApplicationStatus status);
    List<Application> view(Job job, User employer);

    List<Application> getAllApplications();

    void saveApplication(Application application);

    Application getApplicationById(Long id);

    void deleteApplication(Long id);
}
