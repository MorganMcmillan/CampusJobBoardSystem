package com.CampusJobBoardSystem.model;

public class AdminStats {
    public int jobs;
    public int pendingJobs;
    public int approvedJobs;
    public int rejectedJobs;
    public int users;
    public int students;
    public int employers;
    public int applications;
    public int submittedApplications;
    public int acceptedApplications;
    public int rejectedApplications;

    public AdminStats(int pendingJobs, int approvedJobs, int rejectedJobs, int students, int employers, int submittedApplications, int acceptedApplications, int rejectedApplications) {
        this.pendingJobs = pendingJobs;
        this.approvedJobs = approvedJobs;
        this.rejectedJobs = rejectedJobs;
        this.jobs = pendingJobs + approvedJobs + rejectedJobs;
        this.students = students;
        this.employers = employers;
        this.users = students + employers;
        this.submittedApplications = submittedApplications;
        this.acceptedApplications = acceptedApplications;
        this.rejectedApplications = rejectedApplications;
        this.applications = submittedApplications + acceptedApplications + rejectedApplications;
    }
}
