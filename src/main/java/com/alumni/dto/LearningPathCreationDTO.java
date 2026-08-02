package com.alumni.dto;

import java.util.List;

public class LearningPathCreationDTO {
    private String title;
    private List<String> topics;

    public LearningPathCreationDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}