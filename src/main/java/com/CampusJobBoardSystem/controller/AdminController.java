package com.CampusJobBoardSystem.controller;

import com.CampusJobBoardSystem.model.Role;
import com.CampusJobBoardSystem.model.UserStatus;
import com.CampusJobBoardSystem.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // ----------------------- Dashboard -----------------------
    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("stats", adminService.getDashboardStats());
        return "admin/dashboard";  // service returns job/user/application counts
    }

    // ----------------------- Users ----------------------------
    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("users", adminService.getAllUsers());
        return "admin/users";  // service returns all users
    }

    // Delete user
    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id); // service deletes user
        return "redirect:/admin/users";
    }

    // Change user status (ACTIVE / INACTIVE)
    @PostMapping("/users/{id}/status")
    public String changeUserStatus(@PathVariable Long id,
                                   @RequestParam UserStatus status) {
        adminService.updateUserStatus(id, status); // service updates status
        return "redirect:/admin/users";
    }

    // Change user role (STUDENT / EMPLOYER / ADMIN)
    @PostMapping("/users/{id}/role")
    public String changeUserRole(@PathVariable Long id,
                                 @RequestParam Role role) {
        adminService.updateUserRole(id, role); // service updates role
        return "redirect:/admin/users";
    }

    // ----------------------- Jobs ----------------------------
    @GetMapping("/jobs")
    public String manageJobs(Model model) {
        model.addAttribute("jobs", adminService.getAllJobs());
        return "admin/jobs"; // service returns all job posts
    }

    // Delete job post
    @PostMapping("/jobs/{id}/delete")
    public String deleteJob(@PathVariable Long id) {
        adminService.deleteJob(id); // service deletes job
        return "redirect:/admin/jobs";
    }

    // Approve job post
    @PostMapping("/jobs/{id}/approve")
    public String approveJob(@PathVariable Long id) {
        adminService.approveJob(id); // service sets status = APPROVED
        return "redirect:/admin/jobs";
    }

    // Reject job post
    @PostMapping("/jobs/{id}/reject")
    public String rejectJob(@PathVariable Long id) {
        adminService.rejectJob(id); // service sets status = REJECTED
        return "redirect:/admin/jobs";
    }

    // ----------------------- Applications ----------------------------
    @GetMapping("/applications")
    public String manageApplications(Model model) {
        model.addAttribute("applications", adminService.getAllApplications());
        return "admin/applications"; // service returns all job applications
    }

    // Delete application
    @PostMapping("/applications/{id}/delete")
    public String deleteApplication(@PathVariable Long id) {
        adminService.deleteApplication(id); // service removes application
        return "redirect:/admin/applications";
    }
}
