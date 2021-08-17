package com.dataely.app.service;

import com.dataely.app.domain.AnalyzerJob;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link AnalyzerJob}.
 */
public interface AnalyzerJobService {
    /**
     * Save a analyzerJob.
     *
     * @param analyzerJob the entity to save.
     * @return the persisted entity.
     */
    AnalyzerJob save(AnalyzerJob analyzerJob);

    /**
     * Partially updates a analyzerJob.
     *
     * @param analyzerJob the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AnalyzerJob> partialUpdate(AnalyzerJob analyzerJob);

    /**
     * Get all the analyzerJobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AnalyzerJob> findAll(Pageable pageable);

    /**
     * Get the "id" analyzerJob.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AnalyzerJob> findOne(Long id);

    /**
     * Delete the "id" analyzerJob.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
