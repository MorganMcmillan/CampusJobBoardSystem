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

    // -------------------------
    // LIST ALL APPLICATIONS
    // -------------------------
    @GetMapping
    public String listApplications(Model model) {
        // Service should return a List<Application>
        model.addAttribute("applications", applicationService.getAllApplications());
        return "applications/list"; // matches your Thymeleaf file
    }

    // -------------------------
    // VIEW A SINGLE APPLICATION
    // -------------------------
    @GetMapping("/{id}")
    public String viewApplication(@PathVariable Long id, Model model) {
        // Service should find application or throw exception
        Application app = applicationService.getApplicationById(id);
        model.addAttribute("application", app);
        return "applications/view";
    }

    // -------------------------
    // DELETE APPLICATION
    // -------------------------
    @PostMapping("/{id}/delete")
    public String deleteApplication(@PathVariable Long id) {
        // Service should delete by id
        applicationService.deleteApplication(id);
        return "redirect:/applications";
    }

}
