package com.CampusJobBoardSystem.repository;

import com.CampusJobBoardSystem.model.Application;
import com.CampusJobBoardSystem.model.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStatus(JobStatus status);
}
