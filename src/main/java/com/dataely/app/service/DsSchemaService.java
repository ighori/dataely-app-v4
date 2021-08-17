package com.dataely.app.service;

import com.dataely.app.domain.DsSchema;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link DsSchema}.
 */
public interface DsSchemaService {
    /**
     * Save a dsSchema.
     *
     * @param dsSchema the entity to save.
     * @return the persisted entity.
     */
    DsSchema save(DsSchema dsSchema);

    /**
     * Partially updates a dsSchema.
     *
     * @param dsSchema the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DsSchema> partialUpdate(DsSchema dsSchema);

    /**
     * Get all the dsSchemas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DsSchema> findAll(Pageable pageable);

    /**
     * Get the "id" dsSchema.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DsSchema> findOne(Long id);

    /**
     * Delete the "id" dsSchema.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
