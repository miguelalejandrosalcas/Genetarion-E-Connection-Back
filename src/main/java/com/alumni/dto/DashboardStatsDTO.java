package com.alumni.dto;

public class DashboardStatsDTO {
    private long resources;
    private long categories;
    private long recordings;
    private long programs;
    private long libraryResources;
    private long jobBoards;

    public DashboardStatsDTO() {
    }

    public DashboardStatsDTO(long resources, long categories, long recordings, long programs,
                             long libraryResources, long jobBoards) {
        this.resources = resources;
        this.categories = categories;
        this.recordings = recordings;
        this.programs = programs;
        this.libraryResources = libraryResources;
        this.jobBoards = jobBoards;
    }

    public long getResources() { return resources; }
    public void setResources(long resources) { this.resources = resources; }

    public long getCategories() { return categories; }
    public void setCategories(long categories) { this.categories = categories; }

    public long getRecordings() { return recordings; }
    public void setRecordings(long recordings) { this.recordings = recordings; }

    public long getPrograms() { return programs; }
    public void setPrograms(long programs) { this.programs = programs; }

    public long getLibraryResources() { return libraryResources; }
    public void setLibraryResources(long libraryResources) { this.libraryResources = libraryResources; }

    public long getJobBoards() { return jobBoards; }
    public void setJobBoards(long jobBoards) { this.jobBoards = jobBoards; }
}