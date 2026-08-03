package com.alumni.service;

import com.alumni.model.Administrator;

import java.util.List;

public interface AdministratorService {
    List<Administrator> findAll();
    Administrator findById(Long id);
    Administrator save(Administrator administrator);
    Administrator update(Long id, Administrator administrator);
    void deleteById(Long id);
    List<Administrator> findByActive(boolean active);
}
