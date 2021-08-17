package com.dataely.app.service;

import com.dataely.app.domain.FileInfo;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link FileInfo}.
 */
public interface FileInfoService {
    /**
     * Save a fileInfo.
     *
     * @param fileInfo the entity to save.
     * @return the persisted entity.
     */
    FileInfo save(FileInfo fileInfo);

    /**
     * Partially updates a fileInfo.
     *
     * @param fileInfo the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FileInfo> partialUpdate(FileInfo fileInfo);

    /**
     * Get all the fileInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FileInfo> findAll(Pageable pageable);

    /**
     * Get the "id" fileInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileInfo> findOne(Long id);

    /**
     * Delete the "id" fileInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
