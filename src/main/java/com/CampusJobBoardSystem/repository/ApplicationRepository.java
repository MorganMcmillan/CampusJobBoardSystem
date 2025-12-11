package com.CampusJobBoardSystem.repository;

import com.CampusJobBoardSystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStatus(ApplicationStatus status);
    List<Application> findByJob(Job job);
    List<Application> findByJobAndStatus(Job job, ApplicationStatus status);
    List<Application> findByStudent(User student);
    List<Application> findByStudentAndStatus(User student, ApplicationStatus status);
}
