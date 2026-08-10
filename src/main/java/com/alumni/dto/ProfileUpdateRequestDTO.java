package com.alumni.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ProfileUpdateRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es válido")
    private String email;

    private MentorProfileRequestDTO profile;

    public ProfileUpdateRequestDTO() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public MentorProfileRequestDTO getProfile() { return profile; }
    public void setProfile(MentorProfileRequestDTO profile) { this.profile = profile; }
}