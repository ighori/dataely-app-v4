package com.dataely.app.service;

import com.dataely.app.domain.FileConfig;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link FileConfig}.
 */
public interface FileConfigService {
    /**
     * Save a fileConfig.
     *
     * @param fileConfig the entity to save.
     * @return the persisted entity.
     */
    FileConfig save(FileConfig fileConfig);

    /**
     * Partially updates a fileConfig.
     *
     * @param fileConfig the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FileConfig> partialUpdate(FileConfig fileConfig);

    /**
     * Get all the fileConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FileConfig> findAll(Pageable pageable);

    /**
     * Get the "id" fileConfig.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileConfig> findOne(Long id);

    /**
     * Delete the "id" fileConfig.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
