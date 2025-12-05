package com.CampusJobBoardSystem.controller;
import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/jobs")
public class JobController {


    @Autowired
    private JobService jobService;


    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<Job>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(jobService.getByCategory(category));
    }


    @PostMapping
    public ResponseEntity<String> saveJob(@RequestBody Job job) {
        jobService.saveJob(job);
        return ResponseEntity.ok("Job saved successfully.");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@




JobServiceImpl.java added controllers to JobServiceImpl.java


package com.CampusJobBoardSystem.service;


import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.JobStatus;
import com.CampusJobBoardSystem.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@Transactional
public class JobServiceImpl implements JobService {


    private final JobRepository jobRepository;


    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findByStatus(JobStatus.APPROVED);
    }


    @Override
    public List<Job> getByCategory(String category) {
        return jobRepository.findByStatusAndCategory(JobStatus.APPROVED, category);
    }


    @Override
    public void saveJob(Job job) {
        jobRepository.save(job);
    }


    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }


    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
