package com.alumni.service;

import com.alumni.enums.Section;
import com.alumni.model.Administrator;
import com.alumni.model.Category;
import com.alumni.model.Resource;
import com.alumni.model.ResourceType;
import com.alumni.repository.AdministratorRepository;
import com.alumni.repository.CategoryRepository;
import com.alumni.repository.ResourceTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository administratorRepository;


    @Autowired
    public AdministratorServiceImpl (
            AdministratorRepository administratorRepository
    ) {
        this.administratorRepository = administratorRepository;
    }


    @Override
    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }

    @Override
    public Administrator findById(Long id) {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);

        if (optionalAdministrator.isPresent()) {
            return optionalAdministrator.get();
        } else {
            throw new RuntimeException("Administrador no encontrado con ID: " + id);
        }
    }

    @Override
    public Administrator save(Administrator administrator) {
        return null;
    }

    @Override
    public Administrator update(Long id, Administrator administrator) {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);
        Administrator administratorFound = null;

        if (optionalAdministrator.isPresent()) {
            administratorFound = optionalAdministrator.get();
        } else {
            throw new RuntimeException("Administrador no encontrado con ID: " + id);
        }
        administratorRepository.save(administratorFound);
        return administratorFound;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Administrator> optionalAdministrator = administratorRepository.findById(id);

        if (optionalAdministrator.isPresent()) {
            administratorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Administrador no encontrado con ID: " + id);
        }

    }

    @Override
    public List<Administrator> findByActive(boolean active) {
        return administratorRepository.findByActive(active);
    }
}
