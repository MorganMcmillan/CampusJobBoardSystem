package com.CampusJobBoardSystem.repository;

import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT j FROM Job j LEFT JOIN FETCH j.employer WHERE j.status = :status")
    List<Job> findByStatus(@Param("status") JobStatus status);

    @Query("SELECT j FROM Job j LEFT JOIN FETCH j.employer WHERE j.status = :status AND j.category = :category")
    List<Job> findByStatusAndCategory(@Param("status") JobStatus status, @Param("category") String category);

    List<Job> findByCategory(String category);

    @Query("SELECT j FROM Job j LEFT JOIN FETCH j.employer WHERE j.status = :status AND " +
           "(LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(j.location) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(j.category) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Job> searchJobs(@Param("status") JobStatus status, @Param("keyword") String keyword);
}

