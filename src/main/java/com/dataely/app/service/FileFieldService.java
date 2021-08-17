package com.dataely.app.service;

import com.dataely.app.domain.FileField;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link FileField}.
 */
public interface FileFieldService {
    /**
     * Save a fileField.
     *
     * @param fileField the entity to save.
     * @return the persisted entity.
     */
    FileField save(FileField fileField);

    /**
     * Partially updates a fileField.
     *
     * @param fileField the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FileField> partialUpdate(FileField fileField);

    /**
     * Get all the fileFields.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FileField> findAll(Pageable pageable);

    /**
     * Get the "id" fileField.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileField> findOne(Long id);

    /**
     * Delete the "id" fileField.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
