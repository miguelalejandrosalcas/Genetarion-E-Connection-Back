package com.alumni.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;

@Entity
@Table(name = "stories")
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "published_by", nullable = true)
    private Administrator administrator;

    @Column(name = "alumni_name", nullable = false)
    private String alumniName;

    @Column(nullable = false)
    private String program;

    @Column(name = "company")
    private String company;

    @Column(name = "role")
    private String role;

    @Column(name = "time_to_hire")
    private String timeToHire;

    @Column(name = "photo_url",columnDefinition = "TEXT")
    private String photoUrl;

    @Column(columnDefinition = "TEXT")
    private String testimonial;

    @Column(columnDefinition = "TEXT")
    private String trajectory;

    @Column(name = "video_url", columnDefinition = "TEXT")
    private String videoUrl;

    @Column(nullable = false)
    private Boolean featured;

    @Column(nullable = false)
    private Boolean active;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    public Story() {

    }

    public Story(Administrator administrator, String alumniName, String program,
                 String company, String role, String timeToHire,
                 String photoUrl, String testimonial, String trajectory, String videoUrl,
                 Boolean featured, Boolean active, LocalDate publicationDate) {
        this.administrator = administrator;
        this.alumniName = alumniName;
        this.program = program;
        this.company = company;
        this.role = role;
        this.timeToHire = timeToHire;
        this.photoUrl = photoUrl;
        this.testimonial = testimonial;
        this.trajectory = trajectory;
        this.videoUrl = videoUrl;
        this.featured = featured;
        this.active = active;
        this.publicationDate = publicationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public String getAlumniName() {
        return alumniName;
    }

    public void setAlumniName(String alumniName) {
        this.alumniName = alumniName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTimeToHire() {
        return timeToHire;
    }

    public void setTimeToHire(String timeToHire) {
        this.timeToHire = timeToHire;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTestimonial() {
        return testimonial;
    }

    public void setTestimonial(String testimonial) {
        this.testimonial = testimonial;
    }

    public String getTrajectory() {
        return trajectory;
    }

    public void setTrajectory(String trajectory) {
        this.trajectory = trajectory;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Boolean isFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}