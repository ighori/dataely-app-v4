package com.dataely.app.service;

import com.dataely.app.domain.AnalyzerAdHocRecognizers;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link AnalyzerAdHocRecognizers}.
 */
public interface AnalyzerAdHocRecognizersService {
    /**
     * Save a analyzerAdHocRecognizers.
     *
     * @param analyzerAdHocRecognizers the entity to save.
     * @return the persisted entity.
     */
    AnalyzerAdHocRecognizers save(AnalyzerAdHocRecognizers analyzerAdHocRecognizers);

    /**
     * Partially updates a analyzerAdHocRecognizers.
     *
     * @param analyzerAdHocRecognizers the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AnalyzerAdHocRecognizers> partialUpdate(AnalyzerAdHocRecognizers analyzerAdHocRecognizers);

    /**
     * Get all the analyzerAdHocRecognizers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AnalyzerAdHocRecognizers> findAll(Pageable pageable);

    /**
     * Get the "id" analyzerAdHocRecognizers.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AnalyzerAdHocRecognizers> findOne(Long id);

    /**
     * Delete the "id" analyzerAdHocRecognizers.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
