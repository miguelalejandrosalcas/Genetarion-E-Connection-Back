package com.alumni.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;

@Entity
@Table(name = "stories")
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "published_by", nullable = false)
    private Administrator administrator;

    @Column(name = "alumni_name", nullable = false)
    private String alumniName;

    @Column(nullable = false)
    private String program;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @Column(columnDefinition = "TEXT")
    private String testimonial;

    @Column(columnDefinition = "TEXT")
    private String trajectory;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @Column(nullable = false)
    private boolean featured;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    public Story() {

    }

    public Story(Administrator administrator, String alumniName, String program,
                 String photoUrl, String testimonial, String trajectory, String videoUrl,
                 boolean featured, boolean active, LocalDate publicationDate) {
        this.administrator = administrator;
        this.alumniName = alumniName;
        this.program = program;
        this.photoUrl = photoUrl;
        this.testimonial = testimonial;
        this.trajectory = trajectory;
        this.videoUrl = videoUrl;
        this.featured = featured;
        this.active = active;
        this.publicationDate = publicationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
