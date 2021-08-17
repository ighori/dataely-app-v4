package com.dataely.app.service;

import com.dataely.app.domain.AnalyzerEntities;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link AnalyzerEntities}.
 */
public interface AnalyzerEntitiesService {
    /**
     * Save a analyzerEntities.
     *
     * @param analyzerEntities the entity to save.
     * @return the persisted entity.
     */
    AnalyzerEntities save(AnalyzerEntities analyzerEntities);

    /**
     * Partially updates a analyzerEntities.
     *
     * @param analyzerEntities the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AnalyzerEntities> partialUpdate(AnalyzerEntities analyzerEntities);

    /**
     * Get all the analyzerEntities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AnalyzerEntities> findAll(Pageable pageable);

    /**
     * Get the "id" analyzerEntities.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AnalyzerEntities> findOne(Long id);

    /**
     * Delete the "id" analyzerEntities.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
