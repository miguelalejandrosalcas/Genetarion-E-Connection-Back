package com.alumni.model;

import jakarta.persistence.*;


@Entity
@Table(name = "job_boards")
public class JobBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    @Column(name = "active", nullable = false)
    private boolean active;

    public JobBoard(){

    }

    public JobBoard(String name, String url, String description,
                    String logoUrl, Boolean active){
        this.name = name;
        this.url = url;
        this.description = description;
        this.logoUrl = logoUrl;
        this.active = active;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}


