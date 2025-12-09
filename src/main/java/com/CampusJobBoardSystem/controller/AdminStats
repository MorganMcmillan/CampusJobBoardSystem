package com.CampusJobBoardSystem.controller;

import com.CampusJobBoardSystem.model.AdminStats;
import com.CampusJobBoardSystem.service.AdminStatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminStatsController {

    private final AdminStatsService adminStatsService;

    public AdminStatsController(AdminStatsService adminStatsService) {
        this.adminStatsService = adminStatsService;
    }

    // Display admin dashboard stats in a Thymeleaf page
    @GetMapping("/admin/stats")
    public String getAdminStats(Model model) {
        AdminStats stats = adminStatsService.getStatistics();
        model.addAttribute("stats", stats);
        return "admin/stats";  // Thymeleaf template: src/main/resources/templates/admin/stats.html
    }

    // Endpoint for API (JSON)
    @GetMapping("/api/admin/stats")
    public AdminStats getAdminStatsApi() {
        return adminStatsService.getStatistics();
    }
}
