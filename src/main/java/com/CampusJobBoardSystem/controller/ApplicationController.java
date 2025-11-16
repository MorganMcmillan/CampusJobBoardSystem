package com.CampusJobBoardSystem.controller;

import com.CampusJobBoardSystem.model.Application;
import com.CampusJobBoardSystem.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        Optional<Application> application = applicationRepository.findById(id);
        return application.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestBody Application application) {
        Application savedApplication = applicationRepository.save(application);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedApplication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long id, @RequestBody Application applicationDetails) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        if (applicationOptional.isPresent()) {
            Application application = applicationOptional.get();
            application.setJob(applicationDetails.getJob());
            applicationRepository.save(application);
            return ResponseEntity.ok(application);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        if (applicationRepository.existsById(id)) {
            applicationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
