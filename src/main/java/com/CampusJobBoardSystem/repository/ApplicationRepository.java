package com.CampusJobBoardSystem.repository;

import com.CampusJobBoardSystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStatus(JobStatus status);
    List<Application> findByJob(Job job);
    List<Application> findByJobAndStatus(Job job, ApplicationStatus status);
    List<Application> findByUser(User student);
    List<Application> findByUserAndStatus(User student, ApplicationStatus status);
}
