package com.CampusJobBoardSystem.service;
import com.CampusJobBoardSystem.model.*;
import com.CampusJobBoardSystem.repository.ApplicationRepository;
import com.CampusJobBoardSystem.repository.JobRepository;
import com.CampusJobBoardSystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {


    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;


    public AdminServiceImpl(JobRepository jobRepository, UserRepository userRepository, ApplicationRepository applicationRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
    }


    @Override
    public void approve(Job job) {
        job.setStatus(JobStatus.APPROVED);
        jobRepository.save(job);
    }


    @Override
    public void reject(Job job) {
        job.setStatus(JobStatus.REJECTED);
        jobRepository.save(job);
    }


    @Override
    public void toggleActivation(User user, boolean activated) {
        user.setStatus(activated ? UserStatus.ACTIVE : UserStatus.INACTIVE);
        userRepository.save(user);
    }


    @Override
    public AdminStats getDashboardStats() {
        return new AdminStats(
                jobRepository.findByStatus(JobStatus.PENDING).size(),
                jobRepository.findByStatus(JobStatus.APPROVED).size(),
                jobRepository.findByStatus(JobStatus.REJECTED).size(),
                userRepository.findByRole(Role.STUDENT).size(),
                userRepository.findByRole(Role.EMPLOYER).size(),
                applicationRepository.findByStatus(ApplicationStatus.SUBMITTED).size(),
                applicationRepository.findByStatus(ApplicationStatus.ACCEPTED).size(),
                applicationRepository.findByStatus(ApplicationStatus.REJECTED).size()
        );
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }


    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
