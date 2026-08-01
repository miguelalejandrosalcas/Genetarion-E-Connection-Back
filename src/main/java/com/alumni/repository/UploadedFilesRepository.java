package com.alumni.repository;

import com.alumni.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UploadedFilesRepository extends JpaRepository<UploadedFile, Long> {
    List<UploadedFile> findByStorageUrl();
}
