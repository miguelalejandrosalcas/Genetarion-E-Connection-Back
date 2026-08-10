package com.alumni.dto;

import com.alumni.model.Administrator;
import com.alumni.model.MentorProfile;

import java.util.Collections;
import java.util.List;

public class ProfileDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String profileImageUrl;
    private String linkedin;
    private String about;
    private String generationProgram;
    private List<String> mentorAreas;
    private List<String> skills;
    private List<String> mentorType;
    private String token;

    public ProfileDTO() {}

    public static ProfileDTO fromEntities(Administrator administrator, MentorProfile profile, String token) {
        ProfileDTO dto = new ProfileDTO();
        dto.id = administrator.getId();
        dto.name = administrator.getName();
        dto.email = administrator.getEmail();
        dto.role = administrator.getRole();
        dto.token = token;

        if (profile != null) {
            dto.profileImageUrl = profile.getProfileImageUrl();
            dto.linkedin = profile.getLinkedin();
            dto.about = profile.getAbout();
            dto.generationProgram = profile.getGenerationProgram();
            dto.mentorAreas = profile.getMentorAreas();
            dto.skills = profile.getSkills();
            dto.mentorType = profile.getMentorType();
        } else {
            dto.mentorAreas = Collections.emptyList();
            dto.skills = Collections.emptyList();
            dto.mentorType = Collections.emptyList();
        }
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }
    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }
    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
    public String getGenerationProgram() { return generationProgram; }
    public void setGenerationProgram(String generationProgram) { this.generationProgram = generationProgram; }
    public List<String> getMentorAreas() { return mentorAreas; }
    public void setMentorAreas(List<String> mentorAreas) { this.mentorAreas = mentorAreas; }
    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
    public List<String> getMentorType() { return mentorType; }
    public void setMentorType(List<String> mentorType) { this.mentorType = mentorType; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}