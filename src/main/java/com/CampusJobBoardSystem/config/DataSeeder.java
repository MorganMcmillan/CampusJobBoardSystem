package com.CampusJobBoardSystem.config;

import com.CampusJobBoardSystem.model.Job;
import com.CampusJobBoardSystem.model.JobStatus;
import com.CampusJobBoardSystem.model.Role;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.model.UserStatus;
import com.CampusJobBoardSystem.repository.JobRepository;
import com.CampusJobBoardSystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(UserRepository userRepository, JobRepository jobRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Only seed if database is empty
            if (userRepository.count() > 0) return;

            System.out.println("Seeding database with sample data...");

            // 1. Create Users
            User admin = new User("System Admin", "admin@campus.edu", passwordEncoder.encode("password"), Role.ADMIN, UserStatus.ACTIVE);

            User employer1 = new User("Tech Corp Recruiter", "recruiter@techcorp.com", passwordEncoder.encode("password"), Role.EMPLOYER, UserStatus.ACTIVE);
            User employer2 = new User("Campus Dining Manager", "manager@dining.edu", passwordEncoder.encode("password"), Role.EMPLOYER, UserStatus.ACTIVE);

            User student1 = new User("John Student", "john@student.edu", passwordEncoder.encode("password"), Role.STUDENT, UserStatus.ACTIVE);
            User student2 = new User("Jane Scholar", "jane@student.edu", passwordEncoder.encode("password"), Role.STUDENT, UserStatus.ACTIVE);

            userRepository.saveAll(Arrays.asList(admin, employer1, employer2, student1, student2));

            // 2. Create Jobs
            List<String> titles = Arrays.asList(
                "Junior Java Developer", "Frontend React Engineer", "Data Analyst Intern", "Campus Barista",
                "Library Assistant", "Research Assistant", "IT Support Specialist", "Marketing Intern",
                "Event Coordinator", "Tutor - Computer Science", "Gym Attendant", "Social Media Manager",
                "Graphic Designer", "Content Writer", "QA Tester", "DevOps Intern",
                "Mobile App Developer", "UX/UI Designer", "Project Manager Assistant", "Administrative Assistant"
            );

            List<String> locations = Arrays.asList("Remote", "Campus Center", "Library", "Tech Park", "Downtown Office");
            List<String> categories = Arrays.asList("Technology", "Food Service", "Education", "Marketing", "Administration");

            Random rand = new Random();

            for (int i = 0; i < 20; i++) {
                User employer = (i % 2 == 0) ? employer1 : employer2;
                String title = titles.get(i);
                String location = locations.get(rand.nextInt(locations.size()));
                String category = categories.get(rand.nextInt(categories.size()));
                double salary = 15.0 + (rand.nextDouble() * 35.0); // Salary between 15 and 50

                Job job = new Job(
                    employer,
                    title,
                    "We are looking for a " + title + " to join our team. Great opportunity for students!",
                    Math.round(salary * 100.0) / 100.0,
                    location,
                    category,
                    LocalDate.now().plusDays(rand.nextInt(30) + 1), // Deadline 1-30 days in future
                    JobStatus.APPROVED
                );
                jobRepository.save(job);
            }

            System.out.println("Database seeded successfully!");
        };
    }
}

