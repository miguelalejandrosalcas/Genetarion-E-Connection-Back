package com.alumni.service;

import com.alumni.dto.InteractionDTO;
import com.alumni.model.Interaction;
import com.alumni.model.Resource;
import com.alumni.repository.InteractionRepository;
import com.alumni.repository.ResourceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InteractionServiceImpl implements InteractionService {

    private final InteractionRepository interactionRepository;
    private final ResourceRepository resourceRepository;

    public InteractionServiceImpl(InteractionRepository interactionRepository, ResourceRepository resourceRepository) {
        this.interactionRepository = interactionRepository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public List<InteractionDTO> getAll() {
        return toDtoList(interactionRepository.findAll());
    }

    @Override
    public List<InteractionDTO> getByResource(Long resourceId) {
        return toDtoList(interactionRepository.findByResource_IdOrderByCreatedAtDesc(resourceId));
    }

    @Override
    public InteractionDTO getById(Long id) {
        return InteractionDTO.fromEntity(findEntityOrThrow(id));
    }

    @Override
    public InteractionDTO create(InteractionDTO dto) {
        if (dto.getResourceId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "resourceId es obligatorio");
        }
        if (dto.getEventType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "eventType es obligatorio (view o download)");
        }
        if (dto.getSessionId() == null || dto.getSessionId().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sessionId es obligatorio");
        }

        Resource resource = findResourceOrThrow(dto.getResourceId());

        Interaction interaction = new Interaction();
        interaction.setResource(resource);
        interaction.setEventType(dto.getEventType());
        interaction.setSessionId(dto.getSessionId());
        interaction.setCreatedAt(LocalDateTime.now());

        return InteractionDTO.fromEntity(interactionRepository.save(interaction));
    }

    @Override
    public InteractionDTO update(Long id, InteractionDTO dto) {
        Interaction interaction = findEntityOrThrow(id);

        if (dto.getResourceId() != null) {
            interaction.setResource(findResourceOrThrow(dto.getResourceId()));
        }
        if (dto.getEventType() != null) interaction.setEventType(dto.getEventType());
        if (dto.getSessionId() != null) interaction.setSessionId(dto.getSessionId());
        // createdAt no se toca en el update: es el timestamp original del evento

        return InteractionDTO.fromEntity(interactionRepository.save(interaction));
    }

    @Override
    public void delete(Long id) {
        interactionRepository.delete(findEntityOrThrow(id));
    }

    private Resource findResourceOrThrow(Long resourceId) {
        return resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "No existe un recurso con id " + resourceId));
    }

    private Interaction findEntityOrThrow(Long id) {
        return interactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe una interacción con id " + id));
    }

    private List<InteractionDTO> toDtoList(List<Interaction> interactions) {
        return interactions.stream()
                .map(InteractionDTO::fromEntity)
                .collect(Collectors.toList());
    }
}