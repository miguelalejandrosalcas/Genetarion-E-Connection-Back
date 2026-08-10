package com.alumni.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mentor_profiles")
public class MentorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "administrator_id", nullable = false, unique = true)
    private Administrator administrator;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    private String linkedin;

    @Column(columnDefinition = "TEXT")
    private String about;

    @Column(name = "generation_program")
    private String generationProgram;

    @ElementCollection
    @CollectionTable(name = "mentor_profile_areas", joinColumns = @JoinColumn(name = "mentor_profile_id"))
    @Column(name = "area")
    private List<String> mentorAreas = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "mentor_profile_skills", joinColumns = @JoinColumn(name = "mentor_profile_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "mentor_profile_types", joinColumns = @JoinColumn(name = "mentor_profile_id"))
    @Column(name = "mentor_type")
    private List<String> mentorType = new ArrayList<>();

    public MentorProfile() {
    }

    public MentorProfile(Administrator administrator, String profileImageUrl, String linkedin, String about,
                         String generationProgram, List<String> mentorAreas, List<String> skills, List<String> mentorType) {
        this.administrator = administrator;
        this.profileImageUrl = profileImageUrl;
        this.linkedin = linkedin;
        this.about = about;
        this.generationProgram = generationProgram;
        this.mentorAreas = mentorAreas != null ? mentorAreas : new ArrayList<>();
        this.skills = skills != null ? skills : new ArrayList<>();
        this.mentorType = mentorType != null ? mentorType : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Administrator getAdministrator() { return administrator; }
    public void setAdministrator(Administrator administrator) { this.administrator = administrator; }

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