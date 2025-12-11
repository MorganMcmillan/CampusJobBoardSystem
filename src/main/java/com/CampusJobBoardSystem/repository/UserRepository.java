package com.CampusJobBoardSystem.repository;

import com.CampusJobBoardSystem.model.Role;
import com.CampusJobBoardSystem.model.User;
import com.CampusJobBoardSystem.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByStatus(UserStatus status);
    List<User> findByRole(Role role);
    Optional<User> findByEmail(String email);
}
