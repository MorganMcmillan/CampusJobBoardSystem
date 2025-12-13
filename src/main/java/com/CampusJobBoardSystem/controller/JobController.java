package com.CampusJobBoardSystem.controller;

import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.service.AuthenticationService;
import com.CampusJobBoardSystem.service.JobService;
import com.CampusJobBoardSystem.service.PermissionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.CampusJobBoardSystem.controller.ControllerConstants.*;

/**
 * Controller for handling job posting operations including listing,
 * creating, viewing, and deleting job postings.
 *
 * @author Campus Job Board System Team
 * @version 1.0
 */
@Controller
@RequestMapping("/jobs")
public class JobController {

    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    private final JobService jobService;
    private final AuthenticationService authenticationService;
    private final PermissionService permissionService;

    public JobController(JobService jobService,
                        AuthenticationService authenticationService,
                        PermissionService permissionService) {
        this.jobService = jobService;
        this.authenticationService = authenticationService;
        this.permissionService = permissionService;
    }

    @GetMapping
    public String listJobs(@RequestParam(required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            logger.debug("Searching jobs with keyword: {}", keyword);
            model.addAttribute(Attributes.JOBS, jobService.searchJobs(keyword));
            model.addAttribute(Attributes.KEYWORD, keyword);
        } else {
            logger.debug("Displaying all approved jobs");
            model.addAttribute(Attributes.JOBS, jobService.getAllJobs());
        }
        return Views.JOBS_LIST;
    }

    @GetMapping("/new")
    public String showNewJobForm(Model model) {
        logger.debug("Displaying new job form");
        model.addAttribute(Attributes.JOB, new Job());
        return Views.JOBS_NEW;
    }

    @PostMapping
    public String saveJob(@Valid @ModelAttribute(Attributes.JOB) Job job,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        logger.info("Processing new job posting: {}", job.getTitle());

        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors in job creation form");
            return Views.JOBS_NEW;
        }

        try {
            setJobEmployer(job);
            jobService.saveJob(job);
            redirectAttributes.addFlashAttribute(Attributes.SUCCESS_MESSAGE, Messages.JOB_CREATED);
            logger.info("Job created successfully with id: {}", job.getId());
        } catch (Exception e) {
            logger.error("Error creating job posting", e);
            redirectAttributes.addFlashAttribute(Attributes.ERROR_MESSAGE,
                String.format(Messages.JOB_CREATE_FAILED, e.getMessage()));
        }

        return Redirects.JOBS;
    }

    @GetMapping("/{id}")
    public String viewJob(@PathVariable Long id, Model model) {
        logger.debug("Displaying job details for id: {}", id);
        Job job = jobService.getJobById(id);
        model.addAttribute(Attributes.JOB, job);
        model.addAttribute(Attributes.CAN_EDIT, permissionService.canModifyJob(job));
        return Views.JOBS_VIEW;
    }

    @GetMapping("/{id}/edit")
    public String showEditJobForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Job job = jobService.getJobById(id);

        if (!permissionService.canModifyJob(job)) {
            redirectAttributes.addFlashAttribute(Attributes.ERROR_MESSAGE,
                String.format(Messages.NO_PERMISSION, "edit"));
            return Redirects.JOBS;
        }

        model.addAttribute(Attributes.JOB, job);
        return Views.JOBS_NEW;
    }

    @PostMapping("/{id}/edit")
    public String updateJob(@PathVariable Long id,
                           @Valid @ModelAttribute(Attributes.JOB) Job jobUpdated,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        Job existingJob = jobService.getJobById(id);

        if (!permissionService.canModifyJob(existingJob)) {
            redirectAttributes.addFlashAttribute(Attributes.ERROR_MESSAGE,
                String.format(Messages.NO_PERMISSION, "edit"));
            return Redirects.JOBS;
        }

        if (bindingResult.hasErrors()) {
            return Views.JOBS_NEW;
        }

        try {
            updateJobFields(existingJob, jobUpdated);
            jobService.saveJob(existingJob);
            redirectAttributes.addFlashAttribute(Attributes.SUCCESS_MESSAGE, Messages.JOB_UPDATED);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Attributes.ERROR_MESSAGE,
                String.format(Messages.JOB_UPDATE_FAILED, e.getMessage()));
        }

        return Redirects.JOBS;
    }

    @GetMapping("/{id}/delete")
    public String deleteJob(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        logger.info("Deleting job with id: {}", id);

        Job job = jobService.getJobById(id);

        if (!permissionService.canModifyJob(job)) {
            redirectAttributes.addFlashAttribute(Attributes.ERROR_MESSAGE,
                String.format(Messages.NO_PERMISSION, "delete"));
            return Redirects.JOBS;
        }

        try {
            jobService.deleteJob(id);
            redirectAttributes.addFlashAttribute(Attributes.SUCCESS_MESSAGE, Messages.JOB_DELETED);
            logger.info("Job deleted successfully: {}", id);
        } catch (Exception e) {
            logger.error("Error deleting job", e);
            redirectAttributes.addFlashAttribute(Attributes.ERROR_MESSAGE,
                String.format(Messages.JOB_DELETE_FAILED, e.getMessage()));
        }

        return Redirects.JOBS;
    }

    private void setJobEmployer(Job job) {
        authenticationService.getCurrentUser()
                .ifPresent(job::setEmployer);
    }

    private void updateJobFields(Job existingJob, Job updatedJob) {
        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setCategory(updatedJob.getCategory());
        existingJob.setDeadline(updatedJob.getDeadline());
    }
}
