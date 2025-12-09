package com.CampusJobBoardSystem.service;
import com.CampusJobBoardSystem.exception.InvalidRoleException;
import com.CampusJobBoardSystem.model.*;
import com.CampusJobBoardSystem.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {


    private final ApplicationRepository applicationRepository;


    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }


    @Override
    public Application create(Job job, User student) {
        if (student.getRole() != Role.STUDENT)
            throw new InvalidRoleException("Expected user with role STUDENT, got " + student.getRole() + ".");


        Application application = new Application(
                job,
                student,
                ApplicationStatus.SUBMITTED,
                Timestamp.from(Instant.now())
        );
        return applicationRepository.save(application);
    }


    @Override
    public List<Application> view(User student) {
        if (student.getRole() != Role.STUDENT)
            throw new InvalidRoleException("Expected user with role STUDENT, got " + student.getRole() + ".");


        return applicationRepository.findByUser(student);
    }


    @Override
    public List<Application> view(User student, ApplicationStatus status) {
        if (student.getRole() != Role.STUDENT)
            throw new InvalidRoleException("Expected user with role STUDENT, got " + student.getRole() + ".");


        return applicationRepository.findByUserAndStatus(student, status);
    }


    @Override
    public List<Application> view(Job job, User employer) {
        if (employer.getRole() != Role.EMPLOYER)
            throw new InvalidRoleException("Expected user with role EMPLOYER, got " + employer.getRole() + ".");


        return applicationRepository.findByJobAndStatus(job, ApplicationStatus.SUBMITTED);
    }


    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }


    @Override
    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }

    @Override
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }


    @Override
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}


