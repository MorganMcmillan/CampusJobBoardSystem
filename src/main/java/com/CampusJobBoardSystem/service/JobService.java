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
