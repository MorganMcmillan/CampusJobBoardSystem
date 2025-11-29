package com.CampusJobBoardSystem.controller;

import com.CampusJobBoardSystem.model.Application;
import com.CampusJobBoardSystem.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // list all applications
    @GetMapping
    public String listApplications(Model model) {
        model.addAttribute("applications", applicationService.getAllApplications());
        // service: return every application
        return "applications/list";
    }

    // show form to apply for a job
    @GetMapping("/apply/{jobId}")
    public String applyForm(@PathVariable Long jobId, Model model) {
        model.addAttribute("application", new Application());
        model.addAttribute("jobId", jobId);
        return "applications/new";
    }

    // save an application
    @PostMapping
    public String submitApplication(@ModelAttribute Application application) {
        applicationService.saveApplication(application);
        return "redirect:/applications";
    }

    // view application details
    @GetMapping("/{id}")
    public String viewApplication(@PathVariable Long id, Model model) {
        model.addAttribute("application", applicationService.getApplicationById(id));
        return "applications/view";
    }

    // delete an application
    @PostMapping("/{id}/delete")
    public String deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return "redirect:/applications";
    }
}
