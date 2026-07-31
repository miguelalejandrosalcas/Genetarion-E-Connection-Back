package com.alumni.dto;

import com.alumni.model.Administrator;

import java.time.LocalDateTime;

public class AdministratorDTO {
    private long id;
    private String name;
    private String email;
    private String passwordHash;
    private String role;
    private int active;
    private LocalDateTime createdAt;

    public AdministratorDTO () {

    }

    public AdministratorDTO (long id, String name, String email, String passwordHash, String role, int active, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.active = active;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public static AdministratorDTO fromEntity(Administrator administrator) {
        return new AdministratorDTO(
                administrator.getId(),
                administrator.getName(),
                administrator.getEmail(),
                administrator.getPasswordHash(),
                administrator.getRole(),
                administrator.getActive(),
                administrator.getCreatedAt()
        );
    }

    public Administrator toEntity() {
        return new Administrator(this.name, this.email, this.passwordHash,
                this.role, this.active, this.createdAt);
    }
}
