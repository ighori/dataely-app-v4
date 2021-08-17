package com.dataely.app.service;

import com.dataely.app.domain.DsSchemaRelationship;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link DsSchemaRelationship}.
 */
public interface DsSchemaRelationshipService {
    /**
     * Save a dsSchemaRelationship.
     *
     * @param dsSchemaRelationship the entity to save.
     * @return the persisted entity.
     */
    DsSchemaRelationship save(DsSchemaRelationship dsSchemaRelationship);

    /**
     * Partially updates a dsSchemaRelationship.
     *
     * @param dsSchemaRelationship the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DsSchemaRelationship> partialUpdate(DsSchemaRelationship dsSchemaRelationship);

    /**
     * Get all the dsSchemaRelationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DsSchemaRelationship> findAll(Pageable pageable);

    /**
     * Get the "id" dsSchemaRelationship.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DsSchemaRelationship> findOne(Long id);

    /**
     * Delete the "id" dsSchemaRelationship.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
