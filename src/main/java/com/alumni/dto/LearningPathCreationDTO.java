package com.alumni.dto;

import java.util.List;

public class LearningPathCreationDTO {
    private Long id;
    private String title;
    private List<TopicCreationDTO> topics;

    public LearningPathCreationDTO() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<TopicCreationDTO> getTopics() { return topics; }
    public void setTopics(List<TopicCreationDTO> topics) { this.topics = topics; }
}