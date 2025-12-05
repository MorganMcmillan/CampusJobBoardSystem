package com.CampusJobBoardSystem.controller;
import com.CampusJobBoardSystem.model.*;
import com.CampusJobBoardSystem.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/applications")
public class ApplicationController {


    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/create")
    public ResponseEntity<Application> createApplication(@RequestBody ApplicationRequest request) {
        Application created = applicationService.create(request.getJob(), request.getStudent());
        return ResponseEntity.ok(created);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Application>> viewByStudent(@PathVariable Long studentId, @RequestParam(required = false) ApplicationStatus status) {
        User student = new User();
        student.setId(studentId);


        if (status == null) {
            return ResponseEntity.ok(applicationService.view(student));
        }
        return ResponseEntity.ok(applicationService.view(student, status));
    }


    @GetMapping("/employer/{employerId}/job/{jobId}")
    public ResponseEntity<List<Application>> viewByEmployer(@PathVariable Long employerId, @PathVariable Long jobId) {
        User employer = new User();
        employer.setId(employerId);


        Job job = new Job();
        job.setId(jobId);


        return ResponseEntity.ok(applicationService.view(job, employer));
    }


    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplication(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }


    @PostMapping("/save")
    public ResponseEntity<String> saveApplication(@RequestBody Application application) {
        applicationService.saveApplication(application);
        return ResponseEntity.ok("Application saved successfully.");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.ok("Application deleted successfully.");
    }
}

// Helper DTO for application creation
class ApplicationRequest {
    private Job job;
    private User student;


    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }


    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }
}
