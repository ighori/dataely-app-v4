package com.dataely.app.service;

import com.dataely.app.domain.FileSource;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link FileSource}.
 */
public interface FileSourceService {
    /**
     * Save a fileSource.
     *
     * @param fileSource the entity to save.
     * @return the persisted entity.
     */
    FileSource save(FileSource fileSource);

    /**
     * Partially updates a fileSource.
     *
     * @param fileSource the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FileSource> partialUpdate(FileSource fileSource);

    /**
     * Get all the fileSources.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FileSource> findAll(Pageable pageable);

    /**
     * Get the "id" fileSource.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileSource> findOne(Long id);

    /**
     * Delete the "id" fileSource.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
