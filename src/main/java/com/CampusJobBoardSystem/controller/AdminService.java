package com.CampusJobBoardSystem.controller;




import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

@Autowired
private AdminService adminService;
@PostMapping("/approve-job/{id}")
public ResponseEntity<String> approveJob(@PathVariable Long id, @RequestBody Job job) {
adminService.approve(job);
return ResponseEntity.ok("Job approved successfully.");
}

@PostMapping("/reject-job/{id}")
public ResponseEntity<String> rejectJob(@PathVariable Long id, @RequestBody Job job) {
adminService.reject(job);
return ResponseEntity.ok("Job rejected successfully.");
}

@PostMapping("/toggle-activation/{userId}")
public ResponseEntity<String> toggleActivation(@PathVariable Long userId, @RequestParam boolean activated, @RequestBody User user) {
adminService.toggleActivation(user, activated);
return ResponseEntity.ok("User activation status updated.");
}

@GetMapping("/stats")
public ResponseEntity<?> getDashboardStats() {
return ResponseEntity.ok(adminService.getDashboardStats());
}

@GetMapping("/users")
public ResponseEntity<List<User>> getAllUsers() {
return ResponseEntity.ok(adminService.getAllUsers());
}

@DeleteMapping("/delete-user/{id}")
public ResponseEntity<String> deleteUser(@PathVariable Long id) {
adminService.deleteUser(id);
return ResponseEntity.ok("User deleted successfully.");
}

@GetMapping("/jobs")
public ResponseEntity<List<Job>> getAllJobs() {
return ResponseEntity.ok(adminService.getAllJobs());
}


@DeleteMapping("/delete-job/{id}")
public ResponseEntity<String> deleteJob(@PathVariable Long id) {
adminService.deleteJob(id);
return ResponseEntity.ok("Job deleted successfully.");
}
}
