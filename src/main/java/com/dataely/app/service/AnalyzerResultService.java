package com.dataely.app.service;

import com.dataely.app.domain.AnalyzerResult;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link AnalyzerResult}.
 */
public interface AnalyzerResultService {
    /**
     * Save a analyzerResult.
     *
     * @param analyzerResult the entity to save.
     * @return the persisted entity.
     */
    AnalyzerResult save(AnalyzerResult analyzerResult);

    /**
     * Partially updates a analyzerResult.
     *
     * @param analyzerResult the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AnalyzerResult> partialUpdate(AnalyzerResult analyzerResult);

    /**
     * Get all the analyzerResults.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AnalyzerResult> findAll(Pageable pageable);

    /**
     * Get the "id" analyzerResult.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AnalyzerResult> findOne(Long id);

    /**
     * Delete the "id" analyzerResult.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
