package com.CampusJobBoardSystem.controller;

import com.CampusJobBoardSystem.model.Application;
import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.service.ApplicationService;
import com.CampusJobBoardSystem.service.AuthenticationService;
import com.CampusJobBoardSystem.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.CampusJobBoardSystem.controller.ControllerConstants.*;

/**
 * Controller for handling job application operations.
 *
 * @author Campus Job Board System Team
 * @version 1.0
 */
@Controller
@RequestMapping("/applications")
public class ApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    private final ApplicationService applicationService;
    private final JobService jobService;
    private final AuthenticationService authenticationService;

    public ApplicationController(ApplicationService applicationService,
                                JobService jobService,
                                AuthenticationService authenticationService) {
        this.applicationService = applicationService;
        this.jobService = jobService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String listApplications(Model model) {
        model.addAttribute(Attributes.APPLICATIONS, applicationService.getAllApplications());
        return Views.APPLICATIONS_LIST;
    }

    @GetMapping("/apply/{jobId}")
    public String applyForm(@PathVariable Long jobId, Model model) {
        model.addAttribute(Attributes.APPLICATION, new Application());
        model.addAttribute(Attributes.JOB_ID, jobId);
        return Views.APPLICATIONS_NEW;
    }

    @PostMapping
    public String submitApplication(@ModelAttribute(Attributes.APPLICATION) Application application) {
        applicationService.saveApplication(application);
        return Redirects.APPLICATIONS;
    }

    @PostMapping("/apply")
    public String applyForJob(@RequestParam Long jobId, RedirectAttributes redirectAttributes) {
        logger.info("Processing job application for jobId: {}", jobId);

        if (!authenticationService.isAuthenticated()) {
            redirectAttributes.addFlashAttribute(Attributes.ERROR_MESSAGE, Messages.MUST_LOGIN);
            return Redirects.USERS_LOGIN;
        }

        try {
            User student = authenticationService.getCurrentUser()
                    .orElseThrow(() -> new IllegalStateException("Authenticated user not found"));

            Job job = jobService.getJobById(jobId);

            Application application = new Application(job, student);
            applicationService.saveApplication(application);

            logger.info("Application submitted successfully for job: {} by student: {}",
                job.getTitle(), student.getEmail());
            redirectAttributes.addFlashAttribute(Attributes.SUCCESS_MESSAGE, Messages.APPLICATION_SUBMITTED);

            return "redirect:/jobs/" + jobId;

        } catch (Exception e) {
            logger.error("Error submitting application for jobId: {}", jobId, e);
            redirectAttributes.addFlashAttribute(Attributes.ERROR_MESSAGE,
                String.format(Messages.APPLICATION_FAILED, e.getMessage()));
            return "redirect:/jobs/" + jobId;
        }
    }

    @GetMapping("/{id}")
    public String viewApplication(@PathVariable Long id, Model model) {
        model.addAttribute(Attributes.APPLICATION, applicationService.getApplicationById(id));
        return Views.APPLICATIONS_VIEW;
    }

    @PostMapping("/{id}/delete")
    public String deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return Redirects.APPLICATIONS;
    }
}
