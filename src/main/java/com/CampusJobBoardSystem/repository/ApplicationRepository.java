package com.CampusJobBoardSystem.repository;

import com.CampusJobBoardSystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("SELECT a FROM Application a LEFT JOIN FETCH a.job LEFT JOIN FETCH a.student WHERE a.status = :status")
    List<Application> findByStatus(@Param("status") ApplicationStatus status);

    @Query("SELECT a FROM Application a LEFT JOIN FETCH a.student WHERE a.job = :job")
    List<Application> findByJob(@Param("job") Job job);

    @Query("SELECT a FROM Application a LEFT JOIN FETCH a.student WHERE a.job = :job AND a.status = :status")
    List<Application> findByJobAndStatus(@Param("job") Job job, @Param("status") ApplicationStatus status);

    @Query("SELECT a FROM Application a LEFT JOIN FETCH a.job LEFT JOIN FETCH a.job.employer WHERE a.student = :student")
    List<Application> findByStudent(@Param("student") User student);

    @Query("SELECT a FROM Application a LEFT JOIN FETCH a.job LEFT JOIN FETCH a.job.employer WHERE a.student = :student AND a.status = :status")
    List<Application> findByStudentAndStatus(@Param("student") User student, @Param("status") ApplicationStatus status);
}
