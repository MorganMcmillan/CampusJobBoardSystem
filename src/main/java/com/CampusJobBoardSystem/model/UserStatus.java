package com.CampusJobBoardSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_statuses")
public class UserStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UserStatus status;

    public UserStatusEntity() {}

    public UserStatusEntity(UserStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
