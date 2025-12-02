package com.CampusJobBoardSystem.controller;

import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.JobStatus;
import com.CampusJobBoardSystem.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // LIST ALL JOBS

    @GetMapping
    public String listJobs(Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "jobs/list";
    }


    // CREATE JOB FORM

    @GetMapping("/new")
    public String newJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobs/new";
    }

    // SAVE NEW JOB
    @PostMapping
    public String saveJob(@ModelAttribute Job job) {
        jobService.saveJob(job);
        return "redirect:/jobs";
    }

 
    // VIEW JOB DETAILS
 
    @GetMapping("/{id}")
    public String viewJob(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getJobById(id));
        return "jobs/view";
    }

    
    // DELETE JOB
 
    @PostMapping("/{id}/delete")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "redirect:/jobs";
    }

   
    // EDIT JOB FORM
  
    @GetMapping("/{id}/edit")
    public String editJob(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getJobById(id));
        // service: fetch job or throw exception
        return "jobs/edit";
    }

    // UPDATE JOB
    @PostMapping("/{id}/update")
    public String updateJob(@PathVariable Long id, @ModelAttribute Job job) {
        jobService.updateJob(id, job);
        // service: update fields and save
        return "redirect:/jobs/" + id;
    }


    // FILTER JOBS BY CATEGORY
 
    @GetMapping("/category/{category}")
    public String jobsByCategory(@PathVariable String category, Model model) {
        model.addAttribute("jobs", jobService.getJobsByCategory(category));
        model.addAttribute("category", category);
        // service: find jobs by category
        return "jobs/list-category";
    }

 
    // FILTER JOBS BY STATUS

    @GetMapping("/status/{status}")
    public String jobsByStatus(@PathVariable JobStatus status, Model model) {
        model.addAttribute("jobs", jobService.getJobsByStatus(status));
        model.addAttribute("status", status);
        return "jobs/list-status";
    }

   
    // ADMIN — APPROVE JOB
   
    @PostMapping("/{id}/approve")
    public String approveJob(@PathVariable Long id) {
        jobService.approveJob(id);
        // service: set status = APPROVED
        return "redirect:/jobs";
    }

  
    // ADMIN — REJECT JOB

    @PostMapping("/{id}/reject")
    public String rejectJob(@PathVariable Long id) {
        jobService.rejectJob(id);
        // service: set status = REJECTED
        return "redirect:/jobs";
    }
}
