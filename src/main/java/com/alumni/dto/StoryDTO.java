package com.alumni.dto;

import com.alumni.model.Story;

import java.time.LocalDate;

public class StoryDTO {
    private long id;
    private Long administratorId;
    private String alumniName;
    private String program;
    private String photoUrl;
    private String testimonial;
    private String trajectory;
    private String videoUrl;
    private boolean featured;
    private boolean active;
    private LocalDate publicationDate;

    public StoryDTO() {
    }

    public StoryDTO(long id, Long administratorId, String alumniName, String program,
                    String photoUrl, String testimonial, String trajectory, String videoUrl,
                    boolean featured, boolean active, LocalDate publicationDate) {
        this.id = id;
        this.administratorId = administratorId;
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

    public Long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Long administratorId) {
        this.administratorId = administratorId;
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

    public static StoryDTO fromEntity(Story story) {
        return new StoryDTO(
                story.getId(),
                story.getAdministrator() != null ? story.getAdministrator().getId() : null,
                story.getAlumniName(),
                story.getProgram(),
                story.getPhotoUrl(),
                story.getTestimonial(),
                story.getTrajectory(),
                story.getVideoUrl(),
                story.isFeatured(),
                story.isActive(),
                story.getPublicationDate()
        );
    }

    public Story toEntity() {
        Story story = new Story();
        story.setAlumniName(this.alumniName);
        story.setProgram(this.program);
        story.setPhotoUrl(this.photoUrl);
        story.setTestimonial(this.testimonial);
        story.setTrajectory(this.trajectory);
        story.setVideoUrl(this.videoUrl);
        story.setFeatured(this.featured);
        story.setActive(this.active);
        story.setPublicationDate(this.publicationDate);
        return story;
    }
}