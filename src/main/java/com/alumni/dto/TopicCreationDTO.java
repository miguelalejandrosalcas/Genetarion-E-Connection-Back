package com.alumni.dto;

import java.util.List;

public class TopicCreationDTO {
    private String title;
    private List<String> links;

    public TopicCreationDTO() {
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getLinks() { return links; }
    public void setLinks(List<String> links) { this.links = links; }
}