package com.alumni.service;

import com.alumni.dto.StoryDTO;
import com.alumni.model.Administrator;
import com.alumni.model.Story;
import com.alumni.repository.AdministratorRepository;
import com.alumni.repository.StoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;
    private final AdministratorRepository administratorRepository;

    public StoryServiceImpl(StoryRepository storyRepository, AdministratorRepository administratorRepository) {
        this.storyRepository = storyRepository;
        this.administratorRepository = administratorRepository;
    }

    @Override
    public List<StoryDTO> getAll() {
        return toDtoList(storyRepository.findAll());
    }

    @Override
    public List<StoryDTO> getAllActive() {
        return toDtoList(storyRepository.findByActiveTrue());
    }

    @Override
    public List<StoryDTO> getFeatured() {
        return toDtoList(storyRepository.findByFeaturedTrueAndActiveTrue());
    }

    @Override
    public List<StoryDTO> getRecent() {
        return toDtoList(storyRepository.findTop2ByActiveTrueOrderByPublicationDateDesc());
    }

    @Override
    public StoryDTO getById(Long id) {
        return StoryDTO.fromEntity(findEntityOrThrow(id));
    }

    @Override
    public StoryDTO create(StoryDTO dto) {
        if (dto.getAlumniName() == null || dto.getAlumniName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del alumni es obligatorio");
        }
        if (dto.getProgram() == null || dto.getProgram().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El programa es obligatorio");
        }
        if (dto.getTestimonial() == null || dto.getTestimonial().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El testimonio es obligatorio");
        }

        Administrator administrator = dto.getAdministratorId() != null
                ? findAdministratorOrThrow(dto.getAdministratorId())
                : null;

        Story story = new Story();
        story.setAdministrator(administrator);
        story.setAlumniName(dto.getAlumniName());
        story.setProgram(dto.getProgram());
        story.setCompany(dto.getCompany());
        story.setRole(dto.getRole());
        story.setTimeToHire(dto.getTimeToHire());
        story.setPhotoUrl(dto.getPhotoUrl());
        story.setTestimonial(dto.getTestimonial());
        story.setTrajectory(dto.getTrajectory());
        story.setVideoUrl(dto.getVideoUrl());
        story.setFeatured(dto.isFeatured());
        story.setActive(true);
        story.setPublicationDate(dto.getPublicationDate() != null ? dto.getPublicationDate() : LocalDate.now());

        return StoryDTO.fromEntity(storyRepository.save(story));
    }

    @Override
    public StoryDTO update(Long id, StoryDTO dto) {
        Story story = findEntityOrThrow(id);

        if (dto.getAdministratorId() != null) {
            story.setAdministrator(findAdministratorOrThrow(dto.getAdministratorId()));
        }
        if (dto.getAlumniName() != null) story.setAlumniName(dto.getAlumniName());
        if (dto.getProgram() != null) story.setProgram(dto.getProgram());
        if (dto.getCompany() != null) story.setCompany(dto.getCompany());
        if (dto.getRole() != null) story.setRole(dto.getRole());
        if (dto.getTimeToHire() != null) story.setTimeToHire(dto.getTimeToHire());
        if (dto.getPhotoUrl() != null) story.setPhotoUrl(dto.getPhotoUrl());
        if (dto.getTestimonial() != null) story.setTestimonial(dto.getTestimonial());
        if (dto.getTrajectory() != null) story.setTrajectory(dto.getTrajectory());
        if (dto.getVideoUrl() != null) story.setVideoUrl(dto.getVideoUrl());
        if (dto.getPublicationDate() != null) story.setPublicationDate(dto.getPublicationDate());
        story.setFeatured(dto.isFeatured());
        story.setActive(dto.isActive());

        return StoryDTO.fromEntity(storyRepository.save(story));
    }

    @Override
    public void delete(Long id) {
        storyRepository.delete(findEntityOrThrow(id));
    }

    private Administrator findAdministratorOrThrow(Long administratorId) {
        return administratorRepository.findById(administratorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "No existe un administrador con id " + administratorId));
    }

    private Story findEntityOrThrow(Long id) {
        return storyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe una historia con id " + id));
    }

    private List<StoryDTO> toDtoList(List<Story> stories) {
        return stories.stream()
                .map(StoryDTO::fromEntity)
                .collect(Collectors.toList());
    }
}