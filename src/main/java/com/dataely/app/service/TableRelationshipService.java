package com.dataely.app.service;

import com.dataely.app.domain.TableRelationship;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link TableRelationship}.
 */
public interface TableRelationshipService {
    /**
     * Save a tableRelationship.
     *
     * @param tableRelationship the entity to save.
     * @return the persisted entity.
     */
    TableRelationship save(TableRelationship tableRelationship);

    /**
     * Partially updates a tableRelationship.
     *
     * @param tableRelationship the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TableRelationship> partialUpdate(TableRelationship tableRelationship);

    /**
     * Get all the tableRelationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TableRelationship> findAll(Pageable pageable);

    /**
     * Get the "id" tableRelationship.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TableRelationship> findOne(Long id);

    /**
     * Delete the "id" tableRelationship.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
