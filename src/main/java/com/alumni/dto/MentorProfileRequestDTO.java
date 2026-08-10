package com.alumni.dto;

import java.util.List;

public class MentorProfileRequestDTO {

    private String profileImageUrl;
    private String linkedin;
    private String about;
    private String generationProgram;
    private List<String> mentorAreas;
    private List<String> skills;
    private List<String> mentorType;

    public MentorProfileRequestDTO() {}

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
}