package com.alumni.dto;

import com.alumni.enums.EventType;
import com.alumni.model.Interaction;

import java.time.LocalDateTime;

public class InteractionDTO {
    private Long id;
    private Long resourceId;
    private EventType eventType;
    private String sessionId;
    private LocalDateTime createdAt;

    public InteractionDTO() {
    }

    public InteractionDTO(Long id, Long resourceId, EventType eventType,
                          String sessionId, LocalDateTime createdAt) {
        this.id = id;
        this.resourceId = resourceId;
        this.eventType = eventType;
        this.sessionId = sessionId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static InteractionDTO fromEntity(Interaction interaction) {
        return new InteractionDTO(
                interaction.getId(),
                interaction.getResource() != null ? interaction.getResource().getId() : null,
                interaction.getEventType(),
                interaction.getSessionId(),
                interaction.getCreatedAt()
        );
    }

    public Interaction toEntity() {
        Interaction interaction = new Interaction();
        interaction.setEventType(this.eventType);
        interaction.setSessionId(this.sessionId);
        interaction.setCreatedAt(this.createdAt);
        return interaction;
    }
}