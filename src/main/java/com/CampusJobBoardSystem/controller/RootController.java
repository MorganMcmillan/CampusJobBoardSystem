package com.CampusJobBoardSystem.controller;

import com.CampusJobBoardSystem.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    private final JobService jobService;

    public RootController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public String index(Model model) {
        try {
            model.addAttribute("jobs", jobService.getAllJobs());
            model.addAttribute("isAdmin", false);
            model.addAttribute("q", "");
            model.addAttribute("totalPages", 1);
        } catch (Exception e) {
            model.addAttribute("jobs", java.util.Collections.emptyList());
            model.addAttribute("isAdmin", false);
            model.addAttribute("q", "");
            model.addAttribute("totalPages", 1);
        }
        return "index";
    }
}
