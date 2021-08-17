package com.dataely.app.service;

import com.dataely.app.domain.AnalyzerRecognizers;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link AnalyzerRecognizers}.
 */
public interface AnalyzerRecognizersService {
    /**
     * Save a analyzerRecognizers.
     *
     * @param analyzerRecognizers the entity to save.
     * @return the persisted entity.
     */
    AnalyzerRecognizers save(AnalyzerRecognizers analyzerRecognizers);

    /**
     * Partially updates a analyzerRecognizers.
     *
     * @param analyzerRecognizers the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AnalyzerRecognizers> partialUpdate(AnalyzerRecognizers analyzerRecognizers);

    /**
     * Get all the analyzerRecognizers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AnalyzerRecognizers> findAll(Pageable pageable);

    /**
     * Get the "id" analyzerRecognizers.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AnalyzerRecognizers> findOne(Long id);

    /**
     * Delete the "id" analyzerRecognizers.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
