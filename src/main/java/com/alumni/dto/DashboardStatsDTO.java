package com.alumni.dto;

public class DashboardStatsDTO {
    private long resources;
    private long categories;
    private long recordings;
    private long programs;

    public DashboardStatsDTO() {
    }

    public DashboardStatsDTO(long resources, long categories, long recordings, long programs) {
        this.resources = resources;
        this.categories = categories;
        this.recordings = recordings;
        this.programs = programs;
    }

    public long getResources() { return resources; }
    public void setResources(long resources) { this.resources = resources; }

    public long getCategories() { return categories; }
    public void setCategories(long categories) { this.categories = categories; }

    public long getRecordings() { return recordings; }
    public void setRecordings(long recordings) { this.recordings = recordings; }

    public long getPrograms() { return programs; }
    public void setPrograms(long programs) { this.programs = programs; }
}